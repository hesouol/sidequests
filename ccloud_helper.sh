#!/bin/bash
nome_do_service="rafael.souza"
jira_ticket="DPH-241"

#out_old=$(ccloud service-account create "${nome_do_service}" --description "${nome_do_service} user > ${jira_ticket}")
#out=$(echo $out_old)
#echo $out

#service_account=$(cut -d "|" -f 3 <<< $out)
service_account=99972
echo "SERVICE_ACCOUNT: ${service_account}" >> "api-key-${nome_do_service}.txt"

#echo "DEV / STAGING - KAFKA" >> "api-key-${nome_do_service}.txt"
#ccloud api-key create --resource lkc-pgpm5 --service-account $service_account --description "${nome_do_service} staging kafka > ${jira_ticket}" >> "api-key-${nome_do_service}.txt"
#echo "DEV / STAGING - SCHEMA-REGISTRY" >> "api-key-${nome_do_service}.txt"
#ccloud api-key create --resource lsrc-zgnm7 --service-account $service_account --description "${nome_do_service} staging schema-registry > ${jira_ticket}" >> "api-key-${nome_do_service}.txt"

echo "PROD - KAFKA"  >> "api-key-${nome_do_service}.txt"
ccloud api-key create --resource lkc-k81mv --service-account $service_account --description "${nome_do_service} prod kafka > ${jira_ticket}"  >> "api-key-${nome_do_service}.txt"
echo "PROD - SCHEMA-REGISTRY"  >> "api-key-${nome_do_service}.txt"
ccloud api-key create --resource lsrc-nwxoz --service-account $service_account --description "${nome_do_service} prod schema-registry > ${jira_ticket}"  >> "api-key-${nome_do_service}.txt"

