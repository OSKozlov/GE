FROM openjdk:11.0.4-jdk
EXPOSE 8082
ADD build/libs/tseriesservice.jar tseriesservice.jar
ENTRYPOINT ["java","-jar","tseriesservice.jar"]