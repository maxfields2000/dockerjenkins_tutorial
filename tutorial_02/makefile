build:
	@docker build -t myjenkins .
run:
	@docker run -p 8080:8080 -p 50000:50000 --name=jenkins-master -d myjenkins
start:
	@docker start jenkins-master
stop:
	@docker stop jenkins-master
clean:	stop
	@docker rm -v jenkins-master
