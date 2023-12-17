FROM maven:3.8-openjdk-17 AS build
COPY . .
RUN mvn clean package -Dskiptests
FROM openjdk:17-ea-28-jdk-slim
COPY --from=build /target/techBuddy-0.0.1-SNAPSHOT.jar techBuddy.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "techBuddy.jar"]