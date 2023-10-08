# We need to build the project via maven first to create an executable jar file
# This copies the jar to the image and runs the backend
# Possibly the building of the jar can be handled here following this guide:
# https://stackoverflow.com/questions/27767264/how-to-dockerize-maven-project-and-how-many-ways-to-accomplish-it
# Maybe Frontend & Backend can be combined into one image using above technique?

#
# Build stage
#
FROM maven:latest AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:17
COPY --from=build /home/app/target/openapi-spring-1.0.jar /usr/local/lib/openapi-spring-1.0.jar
EXPOSE 8080
WORKDIR /usr/local/lib
CMD ["java", "-jar", "openapi-spring-1.0.jar"]

##FROM openjdk:17
##COPY . ./
##WORKDIR /target
##CMD ["java", "-jar", "openapi-spring-1.0.jar"]