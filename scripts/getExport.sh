docker cp configIncludes.txt jenkins2_master_1:/tmp/configIncludes.txt
docker exec jenkins2_master_1 bash -c "tar -czvf /tmp/jenkinsexport.tar.gz -T /tmp/configIncludes.txt /var/jenkins_home/jobs/*/config.xml"
docker cp jenkins2_master_1:/tmp/jenkinsexport.tar.gz ../jenkins-data/jenkinsexport.tar.gz
