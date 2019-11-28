Steps:
1. Run docker compose with following command: "docker-compose -f docker-compose.yml up"
2. Following containers will launched: 
	postgresge, cassandrage, zookeeperge, kafkage, fileprocservicege, tsservicege, eventservicege, statservicege, facadservicege
3. put file gedata.csv to folder \root\files-ge in fileprocservicege container.
4. Run http://localhost:8085/login as Administrator or Engineer with next credentials:
	Administrator: 
		login: Admin pwd: admin
    Engineer:
    	login: Engineer pwd: engineer