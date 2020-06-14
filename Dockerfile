FROM openjdk:13-alpine
COPY ./build/libs/photoapp-0.0.1-SNAPSHOT.jar .
ENTRYPOINT java -jar photoapp-0.0.1-SNAPSHOT.jar

