FROM openjdk:latest
EXPOSE 8081
ADD target/ConditionalApp-0.0.1-SNAPSHOT.jar ConditionalApp.jar
ENTRYPOINT ["java","-jar","/ConditionalApp.jar"]