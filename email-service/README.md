Upgrade Backend Test

Candidate: Leandro Belluscio
Contact: leanbelluscio@gmail.com

Tech Stack
- Java 11
- SpringBoot 2
- Swagger 2

To run the project locally you should execute `mvn spring-boot:run -P local` 
To run the test project you should execute `./test` 


Api Documentation
- http://localhost:8083/swagger-ui.html

As Email service is a secure service, you should call publication service auth method (http://localhost:8082/publication/api/devops/auth)
in order to get the access token
