FROM openjdk:17
ADD target/api-poow-II.jar api-poow-II.jar
ENTRYPOINT ["java", "-jar","api-poow-II.jar"]

EXPOSE 8080
