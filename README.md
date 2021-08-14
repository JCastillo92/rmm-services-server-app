# rmm-services-server-app
App that enables REST API's for CRUD tasks

<b>Versions:</b></br>
Spring Boot 2.5.3 </br>
Maven Project </br>
Java 8 </br>
Postgresql 13 </br>

<b>Backup Database</b> </br>

1) The backup file is located under src/main/resources/databaseBackup/rmmDatabase.backup </br>
2) In PgAdmin4 create an empty database named "rmm" </br>
3) Right click on the database and select "restore" option </br>
4) Apply these parameters </br>

![postgresRestoreV1](https://user-images.githubusercontent.com/20358517/129460002-8339803f-869a-45ca-84b7-b446296839ce.JPG)

![postgresRestoreV2](https://user-images.githubusercontent.com/20358517/129460005-fe33e8d7-127b-4526-81ba-c472bbd6e66b.JPG)

5) Click on restore </br>

<b>Project Setup Eclipse</b> </br>
1) Clone the repo to your pc </br>
2) In your workspace, import the project as a Maven Project </br>

![importMaven](https://user-images.githubusercontent.com/20358517/129460176-db1d1fa8-5cd4-48b7-bfa2-39b442634fff.jpg)

3) Click on finish </br>
4) Project Exported </br>

![projectExported](https://user-images.githubusercontent.com/20358517/129460408-e4cee40c-9419-472f-b9a9-a4f4c417d346.JPG)

5) Modify application.properties under src/main/resources according to your database credentials or spring credentials </br>
6) Finally run the Application , right click on src/main/java/com.alex.rmmservicesserverapp/RmmServicesServerAppAplication.java </br>
7) Click on Run As -> Java Application </br>

![runApp](https://user-images.githubusercontent.com/20358517/129460523-bdb65307-61ad-4993-a0be-5d0184f6b019.jpg)

8) The application is deployed in localhost:8080. </br>

<b>EndPoints :</b> </br>
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

![endpointsV1](https://user-images.githubusercontent.com/20358517/129458966-6aca4671-e1c8-468f-a76c-940511414a85.JPG)

