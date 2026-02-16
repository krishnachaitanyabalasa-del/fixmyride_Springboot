FROM eclipse-temurin:21-jdk-alpine


WORKDIR /app

COPY . .

RUN apk add --no-cache maven
RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/fixmyride-0.0.1-SNAPSHOT.jar"]
