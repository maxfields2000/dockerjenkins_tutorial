build:
	@docker-compose build
run:
	@docker-compose up -d
stop:
	@docker-compose stop
clean:	stop
	@docker-compose rm jenkinsmaster jenkinsnginx
clean-data:
	@docker-compose rm jenkinsdata
clean-images:
	@docker rmi $(docker images -q --filter="dangling=true")

