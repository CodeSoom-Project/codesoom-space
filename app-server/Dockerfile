FROM openjdk:11-jdk AS builder

ARG JAR_FILE=/app/build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENV USE_PROFILE mariadb

ENTRYPOINT ["java", "-Dspring.profiles.active=${USE_PROFILE}", "-jar","/app.jar"]
