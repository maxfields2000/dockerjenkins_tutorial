# Jenkins and Docker Tutorial Series

This repository is provided as a set of functional working examples that follows along with a blog series released by Maxfield Stewart at Riot Games, Each folder contains a self contained set of files that are the end result of each tutorial and are provided as is.

# Blogs In Order of Appearance

1. [Thinking Inside the Container](http://engineering.riotgames.com/news/thinking-inside-container)
2. [Putting Jenkins into a Docker Container](http://engineering.riotgames.com/news/putting-jenkins-docker-container)
3. [Docker Jenkins and Data that Persists](http://engineering.riotgames.com/news/docker-jenkins-data-persists)
4. [Jenkins, Docker, Proxies and Compose](http://engineering.riotgames.com/news/jenkins-docker-proxies-and-compose)
5. [Taking Control of your Docker Image](http://engineering.riotgames.com/news/taking-control-your-docker-image)
6. [Building With Jenkins Inside an Ephemeral Docker Container](http://engineering.riotgames.com/news/building-jenkins-inside-ephemeral-docker-container)

# Instructions For Using this Repository

Ideally users should follow the blog series for specific directions.  That said here's some basic setup instructions you will need before these become viable.

# Step 1 - Install Docker For Mac/Win

1. You’ll need Windows 7 (or later) or Mac OSX Sierra or later.
2. Go to: https://docs.docker.com/docker-for-mac/  (or https://docs.docker.com/docker-for-windows/)
3. Download and install Docker for your platform
4. Follow all setup instructions.
4. Verify your installation is working by opening a docker terminal window (in windows this step is done by clicking the docker quickstart desktop icon) by running the following steps:
    1. Type: docker images (verify it gives you an empty list back with no errors).

# Step 2 - Choose your Adventure

1. If you want to get started with Jenkins right away, follow the instructions for using the pre-built jenkins setup in this repo here: https://github.com/maxfields2000/dockerjenkins_tutorial/blob/master/jenkins/README.md
2. If you'd prefer to follow the tutorials in the blog and learn as you go/build it yourself, start here: https://engineering.riotgames.com/news/putting-jenkins-docker-container


Good luck, Have Fun!