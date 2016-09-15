This setup is based on the tutorial you can find here:

http://engineering.riotgames.com/news/building-jenkins-inside-ephemeral-docker-container

There several key changes however that won't match that blog. Namely:

1. This uses Jenkins 2.7
2. This uses the updated "Yet Another Docker Plugin" not the older "Docker Plugin"
  1. In particular how you configure ssl certs to talk to your local docker toolbox has changed
  2. You no longer need to generate an ssh-key pair, the YADP uses JNLP to connect slaves, not SSH

# Ephemeral Build Slaves using Jenkins and Docker

This tutorial contains all the files necessary to create your own local Docker-Toolbox version of a fully ephemeral jenkins environment.  Build slaves are designed to
be docker containers in this configuration. The only thing missing to make it work is your Docker Machine certs (see directions below on how to add these).

# Quick Start

Ideally you'll follow along with the entire blog series. However if you'd like to get this up and running as quickly as possible here's the steps you need to do:

1. Make sure you have all the pre-reqs installed (i.e. Docker-toolbox) this has been tested up to Docker 1.11
2. Clone this repository to your local drive
3. Find the docker-machine folder container your 4 client pem keys (typically this is ~/.docker/machine/certs)
  1. You will need access to your ca.pem, key.pem and your cert.pem files
  2. Save them for later
4. Inside the root folder of your local clone execute the following two commands:
  1. make build
  2. make run
5. Point your browser to http://yourdockermachineip (you can get this by running "docker-machine ip default")
6. In jenkins add a new credential (global) for your Docker host by choosing: "Docker Host Certificate Authentication"
  1. For Client Key, cut and paste the text from your "key.pem" file
  2. For Client Certification, cut and paste the text from your "cert.pem" file
  3. For Server CA Certification, cut and paste the text from your ca.pem file
7. In jenkins configuration, add a new Docker cloud provider
  1. Set host to https://yourdockermachineip:2376
  2. Select the docker certificates directory you made
  3. Set read timeout to 5
  4. set connection timeout to 15
  5. Click on "Test COnnection" and make sure you get a valid response
11. In jenkins config on your new Docker cloud add a Docker template
  1. Set the image name to: jenkins\_slave
  2. Create a label "testslave"
  3. Make sure credentials are your new ssh key pair you made above
  4. Click "Save"
12. Create a new free-style jenkins job
  1. Restrict the job to the label "testslave"
  2. Add a build step, like execute shell "echo 'Hello World!'"
  3. Save job
  4. Run job
  
If everything is configured correctly your job should dynamically allocate a slave, run itself and then de-allocate the slave.
