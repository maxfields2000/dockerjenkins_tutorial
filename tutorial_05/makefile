build:
	@docker-compose build
run:
	@docker-compose up -d
stop:
	@docker-compose stop
clean:	stop
	@docker-compose rm jenkinsmaster jenkinsnginx
clean-data: clean
	@docker-compose rm -v jenkinsdata
clean-images:
	@docker rmi $(docker images -q --filter="dangling=true")

