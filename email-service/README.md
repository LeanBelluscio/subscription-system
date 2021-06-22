Upgrade Backend Test

Candidate: Leandro Belluscio
Contact: leanbelluscio@gmail.com

Tech Stack
- Java 11
- SpringBoot 2
- Swagger 2
- H2 Data base
- TestNG

To run the project you should execute `./run` 
To run the test project you should execute `./test` 


Api Documentation
- http://localhost:8080/swagger-ui.html

H2 Console
-  http://localhost:8080/h2-console

H2 DB Connection parameters
- JDBC URL: jdbc:h2:./BD/campsite
- User Name: sa

Login  without password

Notes: The database files will be created into DB folder. Make sure that the project folder has read and write permission.
	   The database will be created without data. While the API is being used, the data will be stored into the database

Test Concurrency
- To test the concurrency we should look the "maven.test.skip" property in pom.xml file and change set as false
- To modify the concurrent user we should modify the following parameters in "ConcurrentBookingTest" class:
	threadPoolSize and invocationCount
	- threadPoolSize: amount of threads
	- invocationCount: amount of invocations
	Example: threadPoolSize=3 and invocationCount=6. Means that the test will use 3 threads to execute 6 times

To run the project you should execute `./run` 
To run the test project you should execute `./test` 
