FROM openjdk:8-jdk-alpine
ARG jar_path=./target/gs-rest-service-0.1.0.jar
COPY $jar_path app.jar
ENTRYPOINT ["java","-jar","/app.jar"]