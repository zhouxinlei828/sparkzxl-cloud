FROM openjdk:8-jre
MAINTAINER zhouxinlei
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone
VOLUME /tmp
EXPOSE 8902
ADD sparksys-authorization-server-1.0.jar app.jar
ENTRYPOINT ["java","-Xms360m","-Xmx360m","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=dev"]
