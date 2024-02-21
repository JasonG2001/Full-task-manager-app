FROM amazoncorretto:21
COPY . .
RUN ./gradlew build --refresh-dependencies --stacktrace
CMD ["java", "-jar", "build/libs/assignment5-0.0.1-SNAPSHOT.jar"]

