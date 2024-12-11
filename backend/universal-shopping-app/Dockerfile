#FROM openjdk:21
#ADD ./docker-spring-boot.jar docker-spring-boot.jar
#
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","docker-spring-boot.jar"]

FROM maven:3.9.4-eclipse-temurin-21 as build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and other necessary files to the container
COPY pom.xml .
COPY src ./src

# Build the application (this will generate the .jar file in target/)
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR file with Java 21
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file generated from the build stage into this image
COPY --from=build /app/target/docker-spring-boot.jar docker-spring-boot.jar

# Expose the port the app will run on (default Spring Boot port is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "docker-spring-boot.jar"]