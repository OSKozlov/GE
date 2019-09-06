FROM openjdk:8
EXPOSE 8084
ADD build/libs/statservice.jar statservice.jar
ENTRYPOINT ["java","-jar","statservice.jar"]