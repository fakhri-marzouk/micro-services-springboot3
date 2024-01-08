FROM openjdk:17-jdk-alpine
EXPOSE 8070
VOLUME /tmp
COPY target/*.jar  app.jar
ENTRYPOINT ["java","-jar", "app.jar"]