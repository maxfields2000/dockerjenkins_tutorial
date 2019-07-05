# Quick Start Guide

You can find a more detailed start up guide [here](https://github.com/maxfields2000/dockerjenkins_tutorial/blob/master/jenkins/README.md)

This will get you up and running with some basic jobs and default config.

1. Make sure you have all the pre-reqs installed (Docker for Mac or Docker for Windows)
2. Clone this repository to your local drive
3. `cd jenkins` 
 1. `make build`
 2. `make run` (if this is your first time it will ask for your docker host IP)
4. Point your browser to http://localhost (you can get this by running "docker-machine ip default")

# ATTENTION: Riot Engineer Blog Readers

If you're following along with Jenkins Tutorial series that uses Jenkins 1 I've placed a corresponding Markdown file [here](https://github.com/maxfields2000/dockerjenkins_tutorial/blob/master/BLOG.md)


# ATTENTION: Docker 1.12/1.13 Users!

This setup is untested on Docker versions 1.12 or older. You will likely have problems if your Docker version doesn't support "LABELS" or "ARGS". Please upgrade, it's well worth it!

# Monitoring Basics

You can find a basic monitoring setup in the `monitoring` folder. Check the [README](https://github.com/maxfields2000/dockerjenkins_tutorial/blob/master/monitoring/README.md)





