FROM gradle:6.7-jdk15 AS BUILD
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM adoptopenjdk/openjdk15:alpine-jre
WORKDIR /app
COPY --from=BUILD /build/target/*.jar ./tsuu-api.jar
EXPOSE 12003
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar", "tsuu-api.jar"]