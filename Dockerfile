# Etapa 1: Construcción con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Puerto expuesto (el mismo que usa tu Spring Boot)
EXPOSE 8080
# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]