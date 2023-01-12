FROM gcr.io/distroless/java11
ARG APP_TARGET=target/jasper-*.jar
COPY APP_TARGET /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]