FROM eclipse-temurin:21 AS build

WORKDIR /app

COPY gradle gradle
COPY gradlew build.gradle settings.gradle ./
RUN chmod +x gradlew
RUN ./gradlew build -x test || return 0
COPY . .
RUN chmod +x gradlew
RUN ./gradlew bootJar -x test

FROM gcr.io/distroless/java21-debian12

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
