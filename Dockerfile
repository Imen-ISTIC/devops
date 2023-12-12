FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/eventsProject-1.0.jar eventsProject-1.0.jar
ENTRYPOINT ["java","-jar","/eventsProject-1.0.jar"]