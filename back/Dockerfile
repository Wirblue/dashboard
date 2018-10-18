FROM openjdk:8-jdk-alpine as builder

RUN mkdir /app
WORKDIR /app

COPY . /app/

RUN ./gradlew build

FROM openjdk:8-jdk-alpine

RUN mkdir /app
WORKDIR /app

COPY --from=builder /app/build/libs/gs-spring-boot-docker-0.1.0.jar /app/ApiApplication.jar

CMD java -jar ApiApplication.jar