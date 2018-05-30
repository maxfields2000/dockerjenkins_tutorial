This setup is based on the tutorial you can find here:

http://engineering.riotgames.com/news/building-jenkins-inside-ephemeral-docker-container

There several key changes however that won't match that blog. Namely:

1. There are utility scripts available to assist you in the root level /scripts directory, these will export config changes and secrets and have directions for how to re-use those if you want to make lasting changes. (They also double as a cheap backup option)

# Ephemeral Build Slaves using Jenkins and Docker

This tutorial contains all the files necessary to create your own local Docker for Mac/Windows or Docker-Toolbox version of a fully ephemeral jenkins environment.  Build slaves are designed to be docker containers in this configuration. 

# Quick Start Jenkins

This will get you upa nd running with some basic jobs and default config. But no credentials. 

1. Make sure you have all the pre-reqs installed (Docker for Mac or Windows)
2. Clone this repository to your local drive
3. Inside the **jenkins** folder of your local clone execute the following two commands:
  1. make build
  2. make run (if this is your first time it will ask for your docker host IP)
4. Point your browser to http://localhost (you can get this by running "docker-machine ip default")
5. If using Docker for Windows, enable "Listen on Localhost:2375"
  1. Go to jenkins (http://localhost), go to configure, under the docker cloud change the cloud endpoint from "proxy1:2375" to "localhost:2375")

# Testing Everything Works

You should just be able to run the job "BasicEnvTest" which will run a simple pipeline job that spins up the test slave and executes a shell command in it.

If you think your docker setup isn't working do the following to check:

1. Got to http://localhost/configure (the jenkins config page)
2. Scroll down to "Docker Plugin" configuration (part of the clouds setup)
3. Find the "Test Connection" button and press it
4. You should get a text string back with version information.

If you get an error something is wrong with your local Docker setup. If you're running Docker for Windows see step 5 above and adjust configuration accordingly. 

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















