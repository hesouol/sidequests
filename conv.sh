#!/bin/bash

BASE_PATH=/home/henrique/Vídeos/f
for mkv in $(find $BASE_PATH -name "*.mkv"); do 
    echo $mkv
    avi=$(sed "s/".mkv"/".avi"/g" <<< $mkv)
    echo $avi
    ffmpeg -i $mkv -f avi -c:v mpeg4 -b:v 4000k -c:a libmp3lame -b:a 320k $avi
done
