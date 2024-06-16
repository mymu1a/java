FROM maven:3.9.2-eclipse-temurin-17-alpine as builder

COPY ./src src/
COPY ./pom.xml pom.xml

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

COPY --from=builder target/*.jar app.jar
COPY ./init.sql /docker-entrypoint-initdb.d/

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]