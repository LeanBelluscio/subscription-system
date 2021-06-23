FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} subscription-service.jar
ENTRYPOINT ["java","-jar","/subscription-service.jar"]