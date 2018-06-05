#!/bin/bash
docker cp jenkinssecrets.tar.gz jenkins_master_1:/tmp/jenkinssecrets.tar.gz
docker exec jenkins_master_1 tar -xvf /tmp/jenkinssecrets.tar.gz