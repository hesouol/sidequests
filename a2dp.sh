#!/bin/bash

TAKE_NEXT=0
INDEX=0
CARD="JBL"

for i in $(pacmd list-cards); do
    if [ "$i" = "index:" ]; then
        TAKE_NEXT=1
        continue
    fi

    if [ $TAKE_NEXT == 1 ]; then
        INDEX=$i
        TAKE_NEXT=0
        continue
    fi

    if [[ $i == *$CARD* ]]; then
        break #found the index
    fi
done

if [ "$INDEX" = "0" ]; then
    echo "$CARD card not found."
else
    echo "$CARD card found on index: $INDEX"

	if [ "$1" = "listen" ]; then
    	    pacmd set-card-profile $INDEX off && sleep 0.1  && pacmd set-card-profile $INDEX a2dp_sink
	else
            pacmd set-card-profile $INDEX off && sleep 0.1  && pacmd set-card-profile $INDEX headset_head_unit
        fi
fi
