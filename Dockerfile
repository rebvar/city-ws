FROM java:8
FROM maven:alpine
RUN mkdir -p /app
RUN cd /app
WORKDIR /app
ADD . /app
RUN mvn clean install -DskipTests
CMD ["java","-Dspring.profiles.active=docker","-jar","/app/target/location-ws-backend-0.0.1-SNAPSHOT.jar"]
