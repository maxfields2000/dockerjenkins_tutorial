This setup is based on the tutorial you can find here:

http://engineering.riotgames.com/news/building-jenkins-inside-ephemeral-docker-container

There several key changes however that won't match that blog. Namely:

1. This uses Jenkins 2.58
2. This uses the updated "Yet Another Docker Plugin" not the older "Docker Plugin"
  1. In particular how you configure ssl certs to talk to your local docker toolbox has changed
  2. You no longer need to generate an ssh-key pair, the YADP uses JNLP to connect slaves, not SSH
3. The default build slave no longer starts and exposes SSH as it's not needed (it uses JNLP settings)
4. You no longer need to configure Docker TLS keys, this setup runs a "Proxy" container against your docker host that works around the TLS security. PLEASE NOTE: DO NOT USE IN PRODUCTION. This is a convenience proxy to make sure things work out of the box on Docker-toolbox, Docker for Mac or Docker for Windows without fuss!
5. On startup this setup will configure several options in Jenkins with your Docker Host IP that you enter during your first "make run" to make plugins happy with your Jenkins root.

# Ephemeral Build Slaves using Jenkins and Docker

This tutorial contains all the files necessary to create your own local Docker for Mac/Windows or Docker-Toolbox version of a fully ephemeral jenkins environment.  Build slaves are designed to be docker containers in this configuration. 

# Quick Start Jenkins

This will get you upa nd running with some basic jobs and default config. But no credentials. 

1. Make sure you have all the pre-reqs installed (i.e. Docker-toolbox or Docker for Mac or Docker for Windows) this has been tested up to Docker 1.13
2. Clone this repository to your local drive
3. Inside the **jenkins2** folder of your local clone execute the following two commands:
  1. make build
  2. make run (if this is your first time it will ask for your docker host IP)
4. Point your browser to http://yourdockermachineip (you can get this by running "docker-machine ip default")

# Testing Everything Works

You should just be able to run the job "BasicEnvTest" which will run a simple pipeline job that spins up the test slave and executes a shell command in it.

PLEASE NOTE: It can take up to a minute to provision the slave the first time you run this. Have patience. Subsequent runs will be faster.

If you think your docker setup isn't working do the following to check:

1. Got to http://yourdockermachineip/configure (the jenkins config page)
2. Scroll down to "Yet Another Docker" configuration
3. Find the "TEst Connection" button and press it
4. You should get a blob of info back and no errors.

If you get an error something is wrong with your local Docker setup. This deployment uses a Docker-proxy container from Evan Hazleet to "talk-through" to your docker host. It's meant to be used on default Docker for Mac/Windows or Docker-Toolbox installations.

Other things to try:

1. Check the file "jenkinslocation.txt" in your root folder, this file is created the first time you do a "make run"
2. Make sure the IP address in that file matches your Docker Host IP
3. If it doesn't, edit it to match your DOcker Host IP, stop your jenkins instance and run "make run" again.

# Rebuilding

If you edit your dockerfiles because you want to make changes. You will need to rebuild.

## Rebuild without blowing away local data

1. make stop
2. make clean
3. make build
4. make run

## Rebuild AND blow away local data

1. make stop
2. make clean-data
3. make build
4. make run

# Taking a New Configuration Export

If you want to capture your local environment as "the template" for development. Do the following:

1. go the "scripts" folder (cd scripts)
2. Run: ./getExport.sh
3. Clean your current setup (make stop, make clean-data)
4. Rebuild (make build, make run)
5. Test. 
6. If everything looks good, push up a commit!


# Exporting your credentials

If you're going to be doing a lot of blowing away your data you can export your credentials and secrets and re-import them after each build so you don't ahve to keep editing them

With your environment still running do:

1. cd /scripts
2. ./getSecrets.sh
3. Rebuild your environment (and blow away local data per above)
4. ./setSecrets.sh
5. make stop
6. make run

# Changing your Docker Host IP

1. Stop your jenkins instance (make stop)
2. Delete "jenkinslocation.txt"
3. Restart (make run) and it will ask you to enter it again.

Conversly... edit "jenkinslocation.txt" directly and restart (make stop, make run)

# Credits

This makes use of the "ehazzlet/docker-proxy" image. Source for that image can be found here: https://github.com/ehazlett/docker-proxy

Thanks to Evan Hazlett https://evanhazlett.com/















