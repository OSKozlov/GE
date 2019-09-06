FROM openjdk:8
EXPOSE 8083
ADD build/libs/eventservice.jar eventservice.jar
ENTRYPOINT ["java","-jar","eventservice.jar"]