FROM openjdk:8-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#CMD ["java","-jar","/app.jar"]

#FROM maven:3-jdk-8
#ENV HOME=/home/usr/app
#RUN mkdir -p $HOME
#WORKDIR $HOME
#
## 1. add pom.xml
#
#ADD pom.xml $HOME"
#
## 2. start downloading dependencies

#FROM maven:3.6.3-jdk-8 AS MAVEN_BUILD
#
#COPY pom.xml /build/
#COPY src /build/src/
#WORKDIR /build/
#RUN mvn package
#
#FROM openjdk:8-oracle
#
#WORKDIR /app
#COPY --from=MAVEN_BUILD /build/target/*.jar /app/app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]