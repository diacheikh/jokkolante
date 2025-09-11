# Étape 1 : Build Maven
FROM eclipse-temurin:21-jdk-alpine AS build
RUN apk add --no-cache maven bash git

WORKDIR /app
COPY pom.xml . 
COPY .mvn/ .mvn/
COPY mvnw . 

# Pré-télécharger les dépendances
RUN ./mvnw dependency:go-offline -B

COPY src ./src
RUN ./mvnw clean package -DskipTests

# Étape 2 : Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copier le JAR et le script
COPY --from=build /app/target/jokkolante-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-db.sh /wait-for-db.sh
RUN chmod +x /wait-for-db.sh

EXPOSE 8081

# Lancer le script qui attend la DB puis l'appli
ENTRYPOINT ["/wait-for-db.sh"]
