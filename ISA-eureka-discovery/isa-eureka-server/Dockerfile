FROM openjdk:17
EXPOSE 8761
ADD ISA-eureka-discovery/isa-eureka-server/target/eureka-server-docker.jar eureka-server-docker.jar
ENTRYPOINT ["java", "-jar", "/eureka-server-docker.jar"]