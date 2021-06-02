FROM openjdk:8-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
CMD ["java","-jar","/app.jar"]

#ENTRYPOINT ["java","-jar","app.jar"]