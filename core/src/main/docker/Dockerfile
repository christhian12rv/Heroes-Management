FROM openjdk:17.0.1-jdk-slim

WORKDIR /usr/src/app

LABEL maintainer="GuBee"
LABEL version="1.0"

ENV DB_NAME="heroesmanagement"
ENV DB_HOSTNAME="db"
ENV DB_PORT="5432"
ENV DB_USERNAME="postgres"
ENV DB_PWD="postgres"

EXPOSE 8080

COPY /target/*.jar interview.jar

ENTRYPOINT [ "java" , "-Xms128m",  "-Xmx400m", "-jar", "-DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector", "interview.jar", "--jdbc.url=jdbc:postgresql://${DB_HOSTNAME}:${DB_PORT}/${DB_NAME}", "--jdbc.password=${DB_PWD}", "--jdbc.username=${DB_USERNAME}"]