FROM maven:3.8-openjdk-17 AS BUILD
#
#COPY entrypoint.sh /usr/local/bin/entrypoint.sh
#RUN #apt-get update && apt-get install dos2unix && dos2unix /usr/local/bin/entrypoint.sh && chmod +x /usr/local/bin/entrypoint.sh

WORKDIR /usr/app/
COPY . .
RUN mvn verify

#Start application

FROM openjdk:latest
ENV JAR_NAME=enrolment-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME .
ENTRYPOINT exec java -jar $APP_HOME/target/$JAR_NAME