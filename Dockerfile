FROM adoptopenjdk:17-jre-hotspot
EXPOSE 8080
ADD build/libs/EcomUserApi.jar EcomUserApi.jar
ENTRYPOINT ["java", "-jar", "EcomUserApi.jar"]
