FROM openjdk:11.0.4-jdk
EXPOSE 8085
ADD build/libs/facadservice.war facadservice.war
ENTRYPOINT ["java", "-jar","facadservice.war"]
#ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=0.0.0.0:9095", "-jar","facadservice.war"]