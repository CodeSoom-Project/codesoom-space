FROM openjdk:11-jdk AS builder

ARG JAR_FILE=/app/build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
