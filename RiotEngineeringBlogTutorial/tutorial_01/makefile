build:
	@docker pull jenkins:1.609.1
run:
	@docker run -p 8080:8080 --name=jenkins-master -d --env JAVA_OPTS="-Xmx8192m" --env JENKINS_OPTS="--handlerCountStartup=100 --handlerCountMax=300" jenkins:1.609.1
start:
	@docker start jenkins-master
stop:
	@docker stop jenkins-master
clean:	stop
	@docker rm -v jenkins-master
