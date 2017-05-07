# Scriptler Scripts for API Control of Docker Plugins

These scripts are provided as is.  To use them, make sure your Jenkins environment has the "Scriptler" plugin installed. Add these groovy scripts as 
Scriptler groovy scripts to your jenkins configuration.

#inputs 

Create these input variables to each script (for the API):

* cloudName existing or current docker cloud name
* label     desired label for build node
* image     image location (eg. dockers.tf.riotgames...)

# Notes

The Regular Dockerplugin groovy script assumes your build slaves connect via SSH. This also requires a "credentialsId" UUID, which Left out of my example for 
obvious security reasons, please add your own!

The Yet Another Docker Plugin assumes you're using JNLP connections, not SSH ones (recommended approach for YADP) and therefor doesn't use a credentials Id.

# Questions

If you have questions please freel free to ask.  These are part of my 2016 Dockercon presentation!