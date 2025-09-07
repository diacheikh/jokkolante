FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copie du pom.xml et installation des dépendances
COPY pom.xml .
COPY src ./src

# Télécharger les dépendances first (cache layer)
RUN mvn dependency:go-offline


# Installation de Maven
RUN apk add --no-cache maven

# Construction de l'application
RUN mvn clean package -DskipTests

# Port exposé
EXPOSE 8081

# Commande de démarrage
ENTRYPOINT ["java", "-jar", "target/jokkolante-0.0.1-SNAPSHOT.jar"]

