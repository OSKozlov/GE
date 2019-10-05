FROM openjdk:11.0.4-jdk
EXPOSE 8081
ADD build/libs/fileprocservice.war fileprocservice.war
ENTRYPOINT ["java","-jar","fileprocservice.war"]