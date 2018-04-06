build:
	@docker-compose -p jenkins build
run:
	@docker-compose -p jenkins up -d
stop:
	@docker-compose -p jenkins down
clean-data: 
	@docker-compose -p jenkins down -v
clean-images:
	@docker rmi `docker images -q -f "dangling=true"`

