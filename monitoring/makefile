build:
	@docker-compose -p monitoring build
	@docker-compose -p monitoring pull grafana graphite
run:
	@docker-compose -p monitoring up -d grafana graphite data
stop:
	@docker-compose -p monitoring stop
clean:	stop
	@docker-compose -p monitoring rm grafana graphite
clean-data: clean
	@docker-compose -p monitoring rm -v data
clean-images:
	@docker rmi `docker images -q -f "dangling=true"`