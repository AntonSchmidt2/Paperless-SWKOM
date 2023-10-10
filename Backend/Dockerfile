# Build stage
## builds the whole project and generates openapi-spring-1.0.jar file
FROM maven:latest AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


# Package stage
## builds a docker container from the openapi-spring-1.0.jar file
FROM openjdk:17
COPY --from=build /home/app/target/openapi-spring-1.0.jar /usr/local/lib/openapi-spring-1.0.jar
EXPOSE 8080
WORKDIR /usr/local/lib
CMD ["java", "-jar", "openapi-spring-1.0.jar"]
