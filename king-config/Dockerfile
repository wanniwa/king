FROM java:8
MAINTAINER wanniwa 734933034@qq.com
VOLUME /tmp
ADD king-config-1.0-SNAPSHOT.jar king-config.jar
RUN bash -c 'touch /king-config.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/king-config.jar"]