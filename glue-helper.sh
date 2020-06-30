#!/bin/bash
#$1 modes  c- copy, w- wait,r- run

IN_PATH="/home/henrique/repos/datalake-glue-scripts/notebooks/changelog_inference"
FILE_NAME="changelog_inference.ipynb"

if [[ $1 == *"c"* ]]; then
    IN_FILE="${IN_PATH}/${FILE_NAME}"
    ZIP_FILE="changelog_inference.zip"
    S3_PATH="s3://bankfacil-creditas-de-glue-script/redshift_changelog_inference"

    PY_FILE=$(sed "s/".ipynb"/".py"/g" <<< $IN_FILE)

    echo "Cleansing ${IN_FILE}."
    jupyter nbconvert $IN_FILE --to python
    sed -i '/^#REMOVE/,/^#END_REMOVE/d' $PY_FILE
    sed -i '/^#UNCOMMENT/,/^#END_UNCOMMENT/s/^#\s//g' $PY_FILE
    sed -i '/UNCOMMENT/d' $PY_FILE 
    sed -i '/^#\sIn\[/d' $PY_FILE
    sed -i 's/print(/logging.warning(/g' $PY_FILE
    black $PY_FILE
    echo "${PY_FILE} Generated."

    cp $IN_PATH/*.py .
    zip $ZIP_FILE *.py
    S3_FILE_PATH=$S3_PATH/${PY_FILE##*/}
    
    out_cp=$(aws s3 cp $PY_FILE $S3_FILE_PATH)
    echo "OUT_CP: ${out_cp}"
    
    out_cp=$(aws s3 cp $ZIP_FILE $S3_PATH/$ZIP_FILE)
    echo "OUT_CP: ${out_cp}"

#    rm -rf $PY_FILE
fi

JOB_NAME="redshift_changelog_inference"
counter=0

if [[ $1 == *"r"* ]]; then
    out_run=$(aws glue start-job-run --job-name "${JOB_NAME}")
    echo "OUT_RUN: ${out_run}"
fi

if [[ $1 == *"w"* ]]; then
    while true; do
        out=$(aws glue get-job-runs --job-name "${JOB_NAME}" | python -m json.tool)
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

