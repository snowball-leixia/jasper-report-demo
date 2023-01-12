FROM gcr.io/distroless/java11
COPY target/jasper-*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]