FROM openjdk:11
ARG JAR_FILE=target/*.jar
ARG DATABASE=src/main/resources/database/subscription.mv.db
COPY ${JAR_FILE} subscription-service.jar
COPY ${DATABASE} subscription.mv.db
ENTRYPOINT ["java","-jar","/subscription-service.jar"]