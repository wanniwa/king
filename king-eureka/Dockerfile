FROM java:8
MAINTAINER wanniwa 734933034@qq.com
VOLUME /tmp
ADD king-eureka*.jar king-eureka.jar
EXPOSE 8761
RUN bash -c 'touch /king-eureka.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/king-eureka.jar"]