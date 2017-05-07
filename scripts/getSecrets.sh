docker cp secretsIncludes.txt jenkins2_master_1:/tmp/secretsIncludes.txt
docker exec --user root jenkins2_master_1 bash -c "tar -czvf /tmp/jenkinssecrets.tar.gz -T /tmp/secretsIncludes.txt"
docker cp jenkins2_master_1:/tmp/jenkinssecrets.tar.gz jenkinssecrets.tar.gz