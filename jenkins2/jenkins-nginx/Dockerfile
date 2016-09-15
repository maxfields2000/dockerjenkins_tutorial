FROM centos:centos7
MAINTAINER Maxfield Stewart

# Yum workaround to stalled mirror
RUN sed -i -e 's/enabled=1/enabled=0/g' /etc/yum/pluginconf.d/fastestmirror.conf

RUN rm -f /var/lib/rpm/__*
RUN rpm --rebuilddb -v -v
RUN yum clean all

# Install NGINX
RUN yum -y -v install http://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm; 
RUN yum -y -v install nginx-1.8.0

# Remove default files we don't need
RUN rm /etc/nginx/conf.d/default.conf
RUN rm /etc/nginx/conf.d/example_ssl.conf

# Add default configuration
COPY conf/jenkins.conf /etc/nginx/conf.d/jenkins.conf
COPY conf/nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

CMD ["nginx"]