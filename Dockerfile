FROM openjdk:11-jre-slim
WORKDIR /usr/local
COPY ./target/beer-0.0.1-SNAPSHOT.jar beer-0.0.1.jar
ENTRYPOINT ["java", "-jar", "beer-0.0.1.jar"]