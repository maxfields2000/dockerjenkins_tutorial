build:
	@docker-compose -p jenkins2 build
	@docker-compose -p jenkins2 pull proxy
run:
	@./setlocation.sh
	@docker-compose -p jenkins2 up -d nginx data master proxy
stop:
	@docker-compose -p jenkins2 stop
clean:	stop
	@docker-compose -p jenkins2 rm master nginx proxy
clean-data: clean
	@docker-compose -p jenkins2 rm -v data
clean-images:
	@docker rmi `docker images -q -f "dangling=true"`

