FROM  openjdk:11.0.7
EXPOSE 9206
VOLUME /tmp
ADD hm-order-0.0.1-SNAPSHOT.jar order.jar
RUN sh -c 'touch /order.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /order.jar" ]