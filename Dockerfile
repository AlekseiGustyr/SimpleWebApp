FROM openjdk:8
COPY target/simplewebapp-1.0.jar simplewebapp-1.0.jar
ENTRYPOINT ["java","-jar","/simplewebapp-1.0.jar"]