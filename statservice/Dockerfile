FROM openjdk:11.0.4-jdk
EXPOSE 8084
ADD build/libs/statservice.jar statservice.jar
ENTRYPOINT ["java","-jar","statservice.jar"]