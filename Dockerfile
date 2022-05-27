FROM openjdk:14
ADD target/ci-value.jar ci-value.jar
ENTRYPOINT ["java", "-jar","ci-value.jar"]
EXPOSE 8080
