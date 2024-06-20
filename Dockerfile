FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

FROM eclipse-temurin:21-jre-jammy AS runner
WORKDIR /app
COPY --from=build /app/target/**-with-deps.jar /app/ratingus-backend.jar

ENV LANG ru_RU.UTF-8
ENV LANGUAGE ru_RU:ru
ENV LC_ALL ru_RU.UTF-8

CMD ["java", "-jar", "ratingus-backend.jar"]
