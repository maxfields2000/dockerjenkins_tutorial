build:
	@docker-compose build
run:
	@docker-compose -p jenkins up -d
stop:
	@docker-compose -p jenkins down
clean-data: 
	@docker-compose -p jenkins down -v
clean-images:
	@docker rmi $(docker images -q --filter="dangling=true")

