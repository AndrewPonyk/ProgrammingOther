FROM ubuntu:latest
MAINTAINER Andrew  "andrew@ukr.net"

RUN apt-get update
RUN apt-get install -y python python-pip wget
RUN pip install Flask

ADD hello.py /home/hello.py

WORKDIR /home




#https://docs.docker.com/docker-cloud/builds/push-images/