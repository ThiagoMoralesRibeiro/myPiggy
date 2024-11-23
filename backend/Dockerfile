# Use Maven to build the Java application
FROM maven:3.8.7-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy the source code and pom.xml to the container
COPY pom.xml .
COPY src ./src

# Package the application as a WAR file
RUN mvn clean package

# Use a Tomcat image to deploy the WAR
FROM tomcat:10.1.7-jdk17-temurin-jammy

# Remove the default ROOT webapp and deploy the new WAR
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose Tomcat's default port
EXPOSE 8080
