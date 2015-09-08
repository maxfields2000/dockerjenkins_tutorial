build:
	@docker build -t myjenkins jenkins-master/.
	@docker build -t myjenkinsdata jenkins-data/.
run-data:
	@docker run --name=jenkins-data myjenkinsdata
run:
	@docker run -p 8080:8080 -p 50000:50000 --name=jenkins-master --volumes-from=jenkins-data -d myjenkins
stop:
	@docker stop jenkins-master
clean:	stop
	@docker rm jenkins-master
clean-data:  clean
	@docker rm -v jenkins-data
