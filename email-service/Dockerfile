FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} email-service.jar
ENTRYPOINT ["java","-jar","/email-service.jar"]