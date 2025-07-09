# Dockerfile
FROM openjdk:17
WORKDIR /app
COPY target/ramli.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
