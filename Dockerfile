FROM openjdk:11.0.5-stretch
VOLUME /tmp
ARG JAR_FILE=build/lib/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]