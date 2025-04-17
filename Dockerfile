FROM maven:3.8.4-openjdk-17 As build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/employeeManagement-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]
