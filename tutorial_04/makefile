build:
	@docker build -t myjenkins jenkins-master/.
	@docker build -t myjenkinsdata jenkins-data/.
	@docker build -t myjenkinsnginx jenkins-nginx/.
run-data:
	@docker run --name=jenkins-data myjenkinsdata
run:
	@docker run --name=jenkins-master --volumes-from=jenkins-data -d myjenkins
	@docker run -p 80:80 --name=jenkins-nginx --link jenkins-master:jenkins-master -d myjenkinsnginx
stop:
	@docker stop jenkins-master
	@docker stop jenkins-nginx
clean:	stop
	@docker rm -v jenkins-master
	@docker rm -v jenkins-nginx
clean-data:
	@docker rm -v jenkins-data
clean-images:
	@docker rmi $(docker images -q --filter="dangling=true")

