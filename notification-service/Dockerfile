# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build

# Copy the entire project
COPY . .

# Build the project
RUN mvn clean package -DskipTests -pl notification-service -am

# Run stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /build/notification-service/target/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]
