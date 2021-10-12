FROM openjdk:8-jdk-alpine
ARG jar_path
COPY $jar_path app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
