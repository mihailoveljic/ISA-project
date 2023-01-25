FROM openjdk:17
EXPOSE 8080
ADD ISA-api-gateway/isa-api-gateway/target/api-gateway-docker.jar api-gateway-docker.jar
ENTRYPOINT ["java", "-jar", "/api-gateway-docker.jar"]