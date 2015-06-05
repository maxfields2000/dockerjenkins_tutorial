build:
	@docker pull jenkins
run:
	@docker run -p 8080:8080 --name=jenkins-master -d --env JAVA_OPTS="-Xmx8192m -XX:PermSize=256m -XX:MaxPermSize=1024m" --env JENKINS_OPTS="--handlerCountStartup=100 --handlerCountMax=300" jenkins
start:
	@docker start jenkins-master
stop:
	@docker stop jenkins-master
clean:	stop
	@docker rm jenkins-master
