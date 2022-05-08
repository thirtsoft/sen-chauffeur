FROM openjdk:8
MAINTAINER Tairou
VOLUME /tmp
COPY target/dpshop-backend-0.0.1-SNAPSHOT.jar backendDriver.jar
COPY src/main/resources/application.properties application.properties
EXPOSE 8200
ENTRYPOINT ["java","-Xms500m","-Xmx1000m","-jar","/backendDriver.jar"]

