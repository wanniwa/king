FROM java:8
MAINTAINER wanniwa 734933034@qq.com
VOLUME /tmp
ADD king-eureka-1.0-SNAPSHOT.jar king-eureka.jar
RUN bash -c 'touch /king-eureka.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/king-eureka.jar"]