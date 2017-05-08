FROM centos:7
MAINTAINER Maxfield Stewart

# Create the jenkins user
RUN useradd -d "/var/jenkins_home" -u 1000 -m -s /bin/bash jenkins

# Create the folders and volume mount points
RUN mkdir -p /var/log/jenkins
RUN chown -R jenkins:jenkins /var/log/jenkins
COPY jenkinsexport.tar.gz /tmp/jenkinsexport.tar.gz
COPY jenkinsdefaultsecrets.tar.gz /tmp/jenkinsdefaultsecrets.tar.gz
RUN tar -xvf /tmp/jenkinsexport.tar.gz 
RUN tar -xvf /tmp/jenkinsdefaultsecrets.tar.gz 
RUN rm /tmp/jenkinsexport.tar.gz 
RUN rm /tmp/jenkinsdefaultsecrets.tar.gz
RUN chown -R jenkins:jenkins /var/jenkins_home

VOLUME ["/var/log/jenkins", "/var/jenkins_home"]

USER jenkins

CMD ["echo", "Data container for Jenkins"]