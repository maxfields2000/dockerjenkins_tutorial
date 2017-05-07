docker cp jenkinssecrets.tar.gz jenkins2_master_1:/tmp/jenkinssecrets.tar.gz
docker exec jenkins2_master_1 tar -xvf /tmp/jenkinssecrets.tar.gz
