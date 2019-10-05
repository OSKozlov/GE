FROM openjdk:11.0.4-jdk
EXPOSE 8085
ADD build/libs/facadservice.war facadservice.war
ENTRYPOINT ["java","-jar","facadservice.war"]