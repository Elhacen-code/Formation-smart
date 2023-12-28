FROM openjdk:21-ea-9-jdk-slim 
EXPOSE 8081
WORKDIR /usr/local/bin/bestteamproject-0.0.1-SNAPSHOT.jar
ADD target/bestteamproject-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","bestteamproject-0.0.1-SNAPSHOT.jar"]