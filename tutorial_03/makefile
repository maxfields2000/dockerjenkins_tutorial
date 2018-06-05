build:
	@docker build -t myjenkins .
create-data:
	@docker volume create jenkins-log
	@docker volume create jenkins-data
run: create-data
	@docker run -p 8080:8080 -p 50000:50000 --name=jenkins-master --mount source=jenkins-log,target=/var/log/jenkins --mount source=jenkins-data,target=/var/jenkins_home -d myjenkins
stop:
	-docker stop jenkins-master
clean:	stop
	-docker rm jenkins-master
clean-data:  clean
	-docker volume rm jenkins-log
	-docker volume rm jenkins-data
