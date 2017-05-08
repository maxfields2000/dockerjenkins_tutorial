FROM centos:7
MAINTAINER Maxfield Stewart

RUN mkdir /var/lib/grafana
RUN mkdir /opt/graphite
RUN mkdir /opt/graphite/storage

VOLUME ["/var/lib/grafana","/opt/graphite/storage"]

CMD ["echo", "Data container for Grafana"]