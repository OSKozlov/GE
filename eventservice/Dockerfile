FROM openjdk:11.0.4-jdk
EXPOSE 8083
ADD build/libs/eventservice.jar eventservice.jar
ENTRYPOINT ["java","-jar","eventservice.jar"]