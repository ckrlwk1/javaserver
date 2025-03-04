# Step 1: Use a base image with Java installed
FROM openjdk:17-jdk-slim as build

# Step 2: Set working directory inside the container
WORKDIR /app

# Step 3: Copy the project files into the container
COPY . .

# Step 4: Build the Spring Boot application (use Gradle or Maven)
RUN ./gradlew build --no-daemon

# Step 5: Expose the port that Spring Boot runs on (default 8080)
EXPOSE 8080

# Step 6: Run the Spring Boot application using the jar file
CMD ["java", "-jar", "build/libs/javaserver-0.0.1-SNAPSHOT.jar"]