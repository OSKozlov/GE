# Steps (Workflow 1):
1. Run docker compose with following command: "docker-compose -f docker-compose.yml up"
2. Following containers will launched: 
	postgresge, cassandrage, zookeeperge, kafkage, fileprocservicege, tsservicege, eventservicege, statservicege, facadservicege
3. put file gedata.csv to folder \root\files-ge in fileprocservicege container.
4. Run http://localhost:8085/login as Administrator or Engineer with next credentials:
	Administrator: 
		login: Admin pwd: admin
    Engineer:
    	login: Engineer pwd: engineer
5. Choose required option from menu
	Menu options:
	Display list of events
	Display list of messages
	Display statistic    	
    	
# Steps (Workflow 2):
1. Run docker compose with following command: "docker-compose -f docker-compose.yml up"
2. Following containers will launched: 
	postgresge, cassandrage, zookeeperge, kafkage, fileprocservicege, tsservicege, eventservicege, statservicege, facadservicege
3. Put file gedata.csv to folder "GE\my-vol".
4. Perform Get request http://localhost:8081/test?fileName=gedata.csv
5. Run http://localhost:8085/login as Administrator or Engineer with next credentials:
	Administrator: 
		login: Admin pwd: admin
    Engineer:
    	login: Engineer pwd: engineer
6. Choose required option from menu
	Menu options:
	Display list of events
	Display list of messages
	Display statistic    	
