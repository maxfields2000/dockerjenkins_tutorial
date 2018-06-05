build:
	@docker pull jenkins/jenkins:2.112
run:
	@docker run -p 8080:8080 --name=jenkins-master -d --env JAVA_OPTS="-Xmx8192m" --env JENKINS_OPTS="--handlerCountMax=300" jenkins/jenkins:2.112
start:
	@docker start jenkins-master
stop:
	@docker stop jenkins-master
clean:	stop
	@docker rm -v jenkins-master
