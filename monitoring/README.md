# Quick Start

Make sure you have Docker for Mac/Windows or Docker-toolbox installed.

1. Sync the repo locally
2. `cd monitoring`
3. `make build`
4. `make run`

This will start a Graphite DB docker container, a Grafana docker ocntainer and a data-volume that keeps data from both Graphite and Grafana.
Once these are running you have a few more Jenkins Configuration steps:

1. Install the "metrics-plugin" for Jenkins
2. Install the "Metrics Graphite Reporting Plugin" for Jenkins
3. After restart Jenkins go to the jenkins configure page
4. Find "Graphite metrics reporting"
5. Set "hostname" to your local docker host IP (where Graphite is running)
6. Set the "port" to 2003
7. Set the "prefix" to "jenkinslocal"
8. Save config

Jenkins will now start pushing metrics to Graphite. This will take up to 30-45 minutes for all metrics to show up. While waiting, set up a grafana data source and dashboard!

## Data Source

1. Point your browser to: http://dockerhostip:3000
2. On the Grafana login page, login as "admin/admin"
3. Go to "Datasources" (http://dockerhostip:3000/datasources)
4. Configure a new data source
5. Set the "type" to "Graphite"
6. Set the name to "LocalGraphite"
7. Set the Url to "http://dockerhostip:8080"
8. Click "Save & Test"

## Import dashboard

I have a pre-built dashboard that should capture a lot of useful things about your jenkins server

1. Go to the Grafana home page (http://dockerhostip:3000)
2. Click on "Home" and then select "Import"
3. Choose "Upload .json file"
4. Pick the "JenkinsMetrics.json" file in the monitoring folder
5. Select "LocalGraphite" as the data source

You should now have a dashboard monitoring Jenkins good to go. Please keep in mind it takes some time for the Graphite Metrics plugin to send data to 
graphite and for the graphite to show up. 


