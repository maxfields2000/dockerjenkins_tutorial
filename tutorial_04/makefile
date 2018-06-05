build:
	@docker build -t myjenkins jenkins-master/.
	@docker build -t myjenkinsnginx jenkins-nginx/.
create-network:
	-docker network create --driver bridge jenkins-net
create-data:
	@docker volume create jenkins-data
	@docker volume create jenkins-log	
run: create-network create-data 
	@docker run -p 50000:50000 --name=jenkins-master --network jenkins-net --mount source=jenkins-log,target=/var/log/jenkins --mount source=jenkins-data,target=/var/jenkins_home -d myjenkins
	@docker run -p 80:80 --name=jenkins-nginx --network jenkins-net -d myjenkinsnginx
stop:
	-docker stop jenkins-master
	-docker stop jenkins-nginx
clean:	stop
	-docker rm jenkins-master
	-docker rm jenkins-nginx
clean-data:  clean
	-docker volume rm jenkins-data
	-docker volume rm jenkins-log	
clean-network: clean
	-docker network rm jenkins-net	
clean-images:
	@docker rmi `docker images -q -f "dangling=true"`

