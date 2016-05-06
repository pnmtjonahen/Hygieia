
FROM docker.io/java:openjdk-8-jdk

MAINTAINER Hygieia@capitalone.com

RUN \
  mkdir /hygieia

COPY *.jar /hygieia/
COPY gogs-properties-builder.sh /hygieia/

WORKDIR /hygieia

VOLUME ["/hygieia/logs"]

CMD ./gogs-properties-builder.sh && \
  java -jar gogs-scm-collector*.jar --spring.config.location=/hygieia/hygieia-gogs-scm-collector.properties

