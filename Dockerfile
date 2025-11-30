FROM eclipse-temurin:21 AS runner

ARG VERSION=0.0.1-SNAPSHOT

WORKDIR /app

COPY /target/coding-exercise-$VERSION.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]