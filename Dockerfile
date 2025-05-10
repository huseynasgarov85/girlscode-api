FROM gradle:8.5-jdk17 AS build

COPY . .

RUN gradle build -x test

FROM eclipse-temurin:17-jdk

ENV MONGODB_URI=mongodb+srv://girlscode:Kc4ycgqFGM2LRXDS@cluster0.7pmhsdk.mongodb.net/girlscode?retryWrites=true&w=majority
ENV MONGODB_DATABASE=girlscode
ENV MONGODB_USERNAME=girlscode
ENV MONGODB_PASSWORD=Kc4ycgqFGM2LRXDS

ENV GOOGLE_APPLICATION_CREDENTIALS=/app/resources/credential.json

# credentials dosyasını konteynerin içine kopyalayın
COPY src/main/resources/credential.json /app/resources/credential.json

COPY --from=build /home/gradle/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
