#FROM eclipse-temurin:17 AS builder
#WORKDIR /app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
##COPY ./src ./src
#CMD ["./mvnw", "spring-boot:run"]

FROM eclipse-temurin:17
RUN apt-get update && apt-get -y upgrade
RUN apt-get install -y inotify-tools dos2unix
ENV HOME=/app
RUN mkdir -p $HOME
WORKDIR $HOME