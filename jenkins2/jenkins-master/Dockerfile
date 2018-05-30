FROM centos:7
LABEL maintainer="mstewart@riotgames.com"

# Yum workaround to stalled mirror
RUN sed -i -e 's/enabled=1/enabled=0/g' /etc/yum/pluginconf.d/fastestmirror.conf

RUN rm -f /var/lib/rpm/__*
RUN rpm --rebuilddb -v -v
RUN yum clean all


# see https://bugs.debian.org/775775
# and https://github.com/docker-library/java/issues/19#issuecomment-70546872
ENV CA_CERTIFICATES_JAVA_VERSION 20140324

RUN yum -v install -y \
    wget \
    zip \
    openssh-client \
    unzip \
    java-1.8.0-openjdk \
    git \
    dos2unix \
    && yum clean all

#RUN /var/lib/dpkg/info/ca-certificates-java.postinst configure

# Install Tini
ENV TINI_VERSION 0.9.0
ENV TINI_SHA fa23d1e20732501c3bb8eeeca423c89ac80ed452

# Use tini as subreaper in Docker container to adopt zombie processes
RUN curl -fsSL https://github.com/krallin/tini/releases/download/v${TINI_VERSION}/tini-static -o /bin/tini && chmod +x /bin/tini \
  && echo "$TINI_SHA  /bin/tini" | sha1sum -c -

# SET Jenkins Environment Variables
ENV JENKINS_HOME /var/jenkins_home
ENV JENKINS_SLAVE_AGENT_PORT 50000
ENV JENKINS_VERSION 2.125
ENV JENKINS_UC https://updates.jenkins-ci.org
ENV COPY_REFERENCE_FILE_LOG $JENKINS_HOME/copy_reference_file.log

# Jenkins is run with user `jenkins`, uid = 1000
# If you bind mount a volume from the host or a data container,
# ensure you use the same uid
RUN useradd -d "$JENKINS_HOME" -u 1000 -m -s /bin/bash jenkins

# `/usr/share/jenkins/ref/` contains all reference configuration we want
# to set on a fresh new installation. Use it to bundle additional plugins
# or config file with your custom jenkins Docker image.
RUN mkdir -p /usr/share/jenkins/ref/init.groovy.d

# Install Jenkins
RUN curl -fL http://repo.jenkins-ci.org/public/org/jenkins-ci/main/jenkins-war/${JENKINS_VERSION}/jenkins-war-${JENKINS_VERSION}.war -o /usr/share/jenkins/jenkins.war

ENV JAVA_OPTS="-Xmx8192m"
ENV JENKINS_OPTS="--logfile=/var/log/jenkins/jenkins.log  --webroot=/var/cache/jenkins/war"

# Prep Jenkins Directories
RUN chown -R jenkins "$JENKINS_HOME" /usr/share/jenkins/ref
RUN mkdir /var/log/jenkins
RUN mkdir /var/cache/jenkins
RUN chown -R jenkins:jenkins /var/log/jenkins
RUN chown -R jenkins:jenkins /var/cache/jenkins

# Expose Ports for web and slave agents
EXPOSE 8080
EXPOSE 50000

COPY plugins.sh /usr/local/bin/plugins.sh
RUN chmod +x /usr/local/bin/plugins.sh
RUN dos2unix /usr/local/bin/plugins.sh

# Install default plugins
COPY plugins.txt /tmp/plugins.txt
RUN dos2unix /tmp/plugins.txt

RUN /usr/local/bin/plugins.sh /tmp/plugins.txt

# Copy in local config files
COPY initagent.groovy /usr/share/jenkins/ref/init.groovy.d/tcp-slave-agent-port.groovy

# Copy startup script
COPY jenkins.sh /usr/local/bin/jenkins.sh
RUN chmod +x /usr/local/bin/jenkins.sh
RUN dos2unix /usr/local/bin/jenkins.sh

# Setup Jenkins data and folders
COPY jenkinsexport.tar.gz /tmp/jenkinsexport.tar.gz
COPY jenkinsdefaultsecrets.tar.gz /tmp/jenkinsdefaultsecrets.tar.gz
COPY jobs /var/jenkins_home/jobs
RUN tar -xvf /tmp/jenkinsexport.tar.gz && tar -xvf /tmp/jenkinsdefaultsecrets.tar.gz
RUN rm /tmp/jenkinsexport.tar.gz && rm /tmp/jenkinsdefaultsecrets.tar.gz
RUN chown -R jenkins:jenkins /var/jenkins_home

# Switch to the jenkins user
USER jenkins

# Tini as the entry point to manage zombie processes
ENTRYPOINT ["/bin/tini", "--", "/usr/local/bin/jenkins.sh"]