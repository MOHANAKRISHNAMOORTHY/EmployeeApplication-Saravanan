# Use a base image with Java and Docker installed
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR = C:\Users\DPRAVE11\Desktop\Git\praveen\EMPLOYEE-APPLICATION-SPRINGBOOT
WORKDIR /app

# Copy the jar file from the host to the container
COPY Employee-0.0.1-SNAPSHOT-plain.jar app.jar

# Expose the port on which your Spring Boot application listens
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]