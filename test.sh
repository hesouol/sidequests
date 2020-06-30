#!/bin/bash

#ON JOB CREATION
#script path = full s3 script path (.py file)
#python library path = full s3 zip file path (.zip file)
#dependent jars path = ?
#referenced files path = full s3 folder path without slash. Ex Oregon. s3://aws-glue-scripts-644153193116-us-west-2/scripts/changelog_inference


#TODO: fazer com que os modos estejam relacionados aos parametros, ex não precisa de in_path para run e wait
#grep running time do job

BASE_PATH=/home/henrique/repos/datalake-glue-scripts

while getopts p:y:z:s:j:m: flag
do
    case "${flag}" in
        p) IN_PATH=${OPTARG};; #source path of the python and notebook files
        y) PY_FILE=${OPTARG};;
        z) ZIP_FILE=${OPTARG};; #zip file with the libs
        s) S3_PATH=${OPTARG};; #s3 path where we should put the scripts
        j) JOB_NAME=${OPTARG};; #glue job name
	m) MODE=${OPTARG};; #operational mode c (copy), r (run) and w (wait) - options f (force) h (help)
    esac
done


if [[ $mode == *"h"* ]]; then
  echo "-p: in_path"
  echo "-y: py_file"
  echo "-z: zip_file"
  echo "-s: s3_path"
  echo "-j: job_name"
  echo "-m: mode (crwhf)"
fi


if [ -z "$MODE" ]
then
  echo "-m argument cannot be empty!\n available modes: c (copy), r (run) and w (wait)\n Ex. -m crw, -m c, -m rw..."
  exit 1
fi
mode=$MODE

if [ -z "$IN_PATH" ]
then
  echo "-p argument cannot be empty!\n Usage: glue_helper.sh -p <IN_PATH> -s: <S3_PATH>, <dev>, <staging>, <prod>  optionals (-y: python file name, -z: zip file name, -j:job_name)"
fi
in_path=$IN_PATH

if [ -z "$JOB_NAME" ]
then
  job_name="$(basename -- $IN_PATH)"
else 
  job_name=$JOB_NAME
fi

suffix="/scripts/$job_name"
if [ -z "$S3_PATH" ]
then
  echo "-s argument cannot be empty!\n Available values: s3://bucket-name..., dev, staging, prod"
  exit 1
else
  case $S3_PATH in

    dev)
      s3_path="s3://dev-creditas-de-glue-script$suffix"
      s3_lib_path="s3://dev-creditas-de-glue-script/lib"
      ;;

    staging | stg)
      s3_path="s3://staging-creditas-de-glue-script$suffix"
      s3_lib_path="s3://staging-creditas-de-glue-script/lib"
      ;;

    bkf | bankfacil | prod | prd | production)
      s3_path="s3://bankfacil-creditas-de-glue-script$suffix"
      s3_lib_path="s3://bankfacil-creditas-de-glue-script/lib"
      ;;

    *)
      s3_path=$S3_PATH
      ;;
  esac
fi

if [ -z "$ZIP_FILE" ]
then
  zip_file="$job_name.zip"
else 
  zip_file=$ZIP_FILE
fi

echo "- PARAMETERS"
echo "in_path..........: $in_path"
echo "job_name.........: $job_name"
echo "s3_path..........: $s3_path"
echo "zip_file.........: $zip_file"
echo "mode.............: $mode"


##########DONE TILL HERE
#Features missing - test lib upload

if [[ $mode == *"c"* ]]; then

  notebook_file=$(ls $in_path/*.ipynb)

  if [ -z "$PY_FILE" ]
  then
    py_file=$(sed "s/".ipynb"/".py"/g" <<< $notebook_file)
    py_file_basename="$(basename -- $py_file)"
  else 
    py_file_basename=$PY_FILE
    py_file=$in_path$py_file_basename
  fi
  echo "py_file_basename.: $py_file_basename"
  echo "py_file..........: $py_file"

  echo "********START PROCESSING********"
  echo "...Converting ${notebook_file}."
  jupyter nbconvert $notebook_file --to python --output $py_file_basename
  echo "....Cleansing ${py_file_basename}."
  sed -i '/^#REMOVE/,/^#END_REMOVE/d' $py_file
  echo "........#REMOVE removed."
  sed -i '/^#UNCOMMENT/,/^#END_UNCOMMENT/s/^#\s//g' $py_file
  echo "........UNCOMMENT uncommented."
  sed -i '/UNCOMMENT/d' $py_file
  sed -i '/^#\sIn\[/d' $py_file
  echo "........In[*] removed."
  sed -i 's/print(/logging.warning(/g' $py_file
  echo "........Changed print() to logging.warning()"
  black $py_file
  echo "........Black applied"
  echo "....${py_file_basename} Cleansed."

  echo "....Zipping $zip_file."
  find . -name "*.py" -print | zip $zip_file -@
  echo "....Zipping OK."

  echo "....Starting copies."
  echo "........Copying script file $py_file_basename."
  out_cp=$(aws s3 cp $py_file $s3_path/$py_file_basename)
  echo "........Copied : ${out_cp}"
  echo "........Copying zip file $zip_file."
  out_cp=$(aws s3 cp $zip_file $s3_lib_path/$zip_file)
  echo "........Copied ${out_cp}."
  echo "....Copies finished."
  rm -rf $zip_file

fi

if [[ $mode == *"f"* ]]; then
  glue_job_name="${job_name}"
else
  glue_job_name="de-dp-${job_name}"
fi


if [[ $mode == *"r"* ]]; then
    out_run=$(aws glue start-job-run --job-name "${glue_job_name}")
    echo "OUT_RUN: ${out_run}"
fi

counter=0
if [[ $mode == *"w"* ]]; then
    while true; do
        out=$(aws glue get-job-runs --job-name "${glue_job_name}" | python -m json.tool)
        if [[ $out == *"RUNNING"* ]]; then
            ((counter=counter+5))
            echo "Running for ${counter} seconds."
            sleep 5
        else
            echo "Done."
            paplay "/usr/share/sounds/ubuntu/stereo/desktop-login.ogg"
            break
        fi
    done
fi

