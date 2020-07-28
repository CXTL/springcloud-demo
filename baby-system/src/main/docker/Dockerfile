FROM openjdk:11.0.3-jdk-stretch
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
VOLUME /tmp
ADD baby-system-0.0.1-SNAPSHOT.jar system.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/system.jar"]
