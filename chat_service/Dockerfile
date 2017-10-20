FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/ChatService-1.0.jar app.jar
ENV JAVA_OPTS=""
ENV MQTTHOST=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar --mqttbroker.url="tcp://${MQTTHOST}"