FROM maven:3.8.7-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app

# Copy Maven configuration and source code
COPY pom.xml .
COPY src ./src

# Package the application using Maven
RUN mvn clean package -DskipTests

# Lightweight JDK runtime to run the app
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /appp

# Copy built JAR from builder stage
COPY --from=builder /app/target/server-1.0-SNAPSHOT.jar app.jar

# Set the port Dropwizard will listen on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar", "server", "config.yml"]