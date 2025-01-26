# Step 1: Build
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy project files into the container
COPY pom.xml .
COPY src ./src

# Build the Maven project
RUN mvn clean package -DskipTests

# Step 2: Final
FROM eclipse-temurin:17-jre

# Set the working directory in the final container
WORKDIR /app

# Copy the JAR built in the previous step to the final container
COPY --from=build /app/target/*.jar app.jar

# Expose the port used by the Spring Boot application
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
