# Etapa de construção usando Maven
FROM maven:3.8.7-eclipse-temurin-17 AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

# Tomcat com Maven para Desenvolvimento
FROM tomcat:10.1.7-jdk17-temurin-jammy

# Remover a aplicação ROOT padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copiar o WAR gerado para o Tomcat
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Adicionar a opção --add-opens para permitir acesso ao pacote java.time
ENV CATALINA_OPTS="-Djava.security.egd=file:/dev/./urandom --add-opens=java.base/java.time=ALL-UNNAMED"

# Expor a porta 8080
EXPOSE 8080

# Comando para rodar o Tomcat
CMD ["catalina.sh", "run"]

