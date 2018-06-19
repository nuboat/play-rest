#
# Alpine Java 8
#
# https://hub.docker.com/r/anapsix/alpine-java/
# https://github.com/anapsix/docker-alpine-java
#
# Pull base image.
FROM anapsix/alpine-java:8_server-jre

ENV JAVA_HOME /opt/jdk

RUN apk add --update unzip

COPY target/universal/*.zip /root/build.zip

RUN unzip /root/build.zip -d /root/release

# Define working directory.
WORKDIR /root

# Define default command.
CMD exec /root/release/bin/play-rest \
    -Dhttp.address=0.0.0.0 \
    -J-Xms128M -J-Xmx512m \
    -Dconfig.file=/root/release/conf/production.conf \
    -Dlogger.resource=/root/release/conf/production.xml
