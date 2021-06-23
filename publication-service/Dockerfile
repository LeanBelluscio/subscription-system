FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} publication-service.jar
ENTRYPOINT ["java","-jar","/publication-service.jar"]