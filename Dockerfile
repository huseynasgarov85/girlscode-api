# Build mərhələsi: Gradle ilə tətbiqinizi hazırlayın
FROM gradle:8.5-jdk17 AS build

# Kodları konteynerə kopyalayırıq
COPY . .

# Gradle ilə tətbiqi build edirik (testləri keçirik)
RUN gradle build -x test

# İkinci mərhələ: Tətbiqi işə salmaq üçün lazım olan Java image-ı
FROM eclipse-temurin:17-jdk

# Docker konteynerində mühit dəyişənlərini təyin edirik
ENV MONGODB_URI=mongodb+srv://girlscode:Kc4ycgqFGM2LRXDS@cluster0.7pmhsdk.mongodb.net/girlscode?retryWrites=true&w=majority
ENV MONGODB_DATABASE=girlscode
ENV MONGODB_USERNAME=girlscode
ENV MONGODB_PASSWORD=Kc4ycgqFGM2LRXDS

# Build edilmiş jar faylını kopyalayırıq
COPY --from=build /home/gradle/build/libs/*.jar app.jar

# Tətbiqi işə salırıq
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
