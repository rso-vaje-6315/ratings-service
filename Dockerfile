FROM openjdk:11-jre-slim

ENV JAVA_ENV=PRODUCTION
ENV KUMULUZEE_ENV_NAME=prod
ENV KUMULUZEE_ENV_PROD=true

RUN mkdir /app
WORKDIR /app

ADD ./api/v1/target/ratings-service.jar /app

EXPOSE 8080

CMD ["java", "-jar", "ratings-service.jar"]
