FROM openjdk:8
COPY target/simplewebapp-0.2.jar simplewebapp-0.2.jar
ENTRYPOINT ["java","-jar","/simplewebapp-0.2.jar"]