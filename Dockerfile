FROM gradle:6.7-jdk15 AS BUILD
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon --full-stacktrace

FROM adoptopenjdk/openjdk15:alpine-jre
WORKDIR /app
COPY --from=BUILD /home/gradle/src/build/libs/*.jar ./tsuu-api.jar
EXPOSE 12003
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "tsuu-api.jar"]