#!/bin/bash
docker cp configIncludes.txt jenkins_master_1:/tmp/configIncludes.txt
docker exec jenkins_master_1 bash -c "tar -czvf /tmp/jenkinsexport.tar.gz -T /tmp/configIncludes.txt"
docker cp jenkins_master_1:/tmp/jenkinsexport.tar.gz ../jenkins/jenkins-master/jenkinsexport.tar.gz

# docker cp can't do pattern matching, so we grab everything and then delete 
# the stuff we don't want
rm -rf ../jenkins/jenkins-master/jobs
docker cp jenkins_master_1:/var/jenkins_home/jobs/ ../jenkins/jenkins-master/
find ../jenkins/jenkins-master/jobs ! -type directory ! -name 'config.xml' -exec rm {} \;
