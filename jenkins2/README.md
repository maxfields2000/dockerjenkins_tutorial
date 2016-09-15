This setup is based on the tutorial you can find here:

http://engineering.riotgames.com/news/building-jenkins-inside-ephemeral-docker-container

There several key changes however that won't match that blog. Namely:

1. This uses Jenkins 2.7
2. This uses the updated "Yet Another Docker Plugin" not the older "Docker Plugin"
  1. In particular how you configure ssl certs to talk to your local docker toolbox has changed
  2. You no longer need to generate an ssh-key pair, the YADP uses JNLP to connect slaves, not SSH
3. The default build slave no longer starts and exposes SSH as it's not needed.

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
6. In jenkins add a new credential (credentials->system->global credentials) for your Docker host by choosing: "Docker Host Certificate Authentication"
  1. For Client Key, cut and paste the text from your "key.pem" file
  2. For Client Certification, cut and paste the text from your "cert.pem" file
  3. For Server CA Certification, cut and paste the text from your ca.pem file
7. In jenkins configuration, add a new Yet Another Docker cloud provider
  1. Set Docker URL to https://yourdockermachineip:2376  (it's probably http://192.168.99.100:2376)
  2. For Host Credentials select the Docker host credential you made you made
  3. Click on "Test COnnection" and make sure you get a valid response
8. In jenkins config on your new Docker cloud add a Docker template
  1. Under "Docker Container Lifecycle" set the image name to: jenkins\_slave
  2. Under "Pull Image Settings" set it to "Pull Never" (THIS IS IMPORANT. Your image is local, you don't need to pull it.)
  2. Under "Remove Container Settings" check "remove volume"
  3. Under "Jenkins Slave Config" set "Labels" to "testslave"
  4. Under "Launch Method" make sure JNLP is the selected option
  5. Click "Save"
12. Create a new pipeline jenkins job
  1. Enter the following for the pipeline script:
```
node ('testslave') {

  stage 'Stage 1'
  sh 'echo "Hello World!"'

}
```

  3. Save job
  4. Run job
  
If everything is configured correctly your job should dynamically allocate a slave, run itself and then de-allocate the slave.
