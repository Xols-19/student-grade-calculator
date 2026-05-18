# Use Maven + Java build image
FROM maven:3.9.6-eclipse-temurin-11 AS build

# Set working directory inside container
WORKDIR /app

# Copy project files
COPY . .

# Build the WAR file
RUN mvn clean package -DskipTests


# Runtime image (lightweight Tomcat)
FROM tomcat:9.0-jdk11

# Remove default Tomcat apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR from build stage
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]