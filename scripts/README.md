# Utility Scripts

This folder contains scripts for helping to maintain your development environment. If you decide to install additional plugins, set up credentials or or change any configuration the scripts in this directory will help you make things permanently applied to your base images.

# Updating Jenkins Configuration

If you change settings in the jenkins master config panel or make build jobs you'd prefer to always be available even if you blow away your data container just use the `getExport.sh` script as follows:

1. Go to the /scripts folder
2. run the getExport.sh script

This script relies on the `configIncludes.txt` file to determine what jenkins configuration to extra. You may have to add additional files depending on the plugin/configs you want to save. That said the script extracts all current job configuration and jenkins settings and places them in the `jenkinsexport.tar.gz` file in the "jenkins-data" directory.  To use it, just wipe your jenkins images (make clean-data) and rebuild (make build)

# Updating the default plugins

You may want to add additional plugins to your default install. The plugins built in by default are in `jenkins-master/plugins.txt` you could just hand edit that file and add the plugins you want. Alternatively, you can install the plugins to your local jenkins instance. Then do the following:

1. Go to: http://yourjenkinsip/script
2. Cut and paste the `listPlugins.groovy` in this folder into the script window and run it
3. Cut and paste the output of that run into the `plugins.txt` file in `jenkins-master/plugins.txt`.
4. Rebuild your jenkins environment (don't forget to clean your data file with `make clean-data`)

# Exporting and saving your credentials

Please Note: Be wary using this on a production jenkins instance, while the credentials are encrypted this captures and stores everything jenkins needs to decrypt them, which means anything a malicious intruder would also need to decrypt them.  Use with caution, but provided here incase you want to set up default creds for development/practice use and s hare them.

After setting your credentials in Jenkins how you want them do the following:

1. Go to the /scripts folder
2. Run `getSecrets.sh`
3. This will create a "jenkinssecrets.tar.gz" file

You can now, every time you rebuild jenkins, just run the `setSecrets.sh` to reimport your secrets (restart jenkins after) OR if you want to make them permanent:

4. Copy `jenkinssecrets.tar.gz` over the file `jenkins-data\jenkinsdefaultsecrets.tar.gz` 
5. Rebuild your jenkins environment (remember to clean data with make clean-data)

