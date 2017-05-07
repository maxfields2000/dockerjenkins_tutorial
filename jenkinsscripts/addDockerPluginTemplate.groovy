import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*

import com.nirima.jenkins.plugins.docker.*
import hudson.plugins.sshslaves.SSHConnector
import com.nirima.jenkins.plugins.docker.launcher.*
import com.nirima.jenkins.plugins.docker.strategy.*


/*
=================================
= NEW TEMPLATE HARDCODED VALUES =
=================================
*/
def remoteFs = "/home/jenkins"
def remoteFsMapping = ""
def idleTerminationMinutes = "5"
def launchTimeoutMinutes = 1
def jvmOptions = ""
def javaPath = ""
def prefixStartSlaveCmd = ""
def suffixStartSlaveCmd = ""
def instanceCapStr = ""
def dnsString = "10.201.17.11"
def dockerCommand = ""
def volumesString = ""
def volumesFrom = ""
def lxcConfString = ""
def hostname = ""
def bindPorts =""
def bindAllPorts = false
def privileged = false
def environment = ""
def memoryLimit = null
def cpuShares = null
def tty = false
def macAddress = ""
def numExecutors = 1
def mode = Node.Mode.EXCLUSIVE
def idleMinutes = 5 
def removeVolumes = true

/* This works because we want to pull only when an image doesn't exist on the box,
 * OR the image ends with :latest. :lkg images will not end up pulling if the image is
 * on the box already.
 */
def pullStrategy = DockerImagePullStrategy.PULL_LATEST

/*
 * Checks if specified label is in any docker cloud
 * dockerServer list of cloudhosts from docker plugin
 * return       true if label is found
 */
def labelInCloud(dockerServers) {
    def found = false
    dockerServers.each() { dockerServer ->
      	//label checking
      	ArrayList<DockerCloud> templates = dockerServer.templates
        templates.each() { template ->
            if (template.labelString.toLowerCase().equals(label.toLowerCase())) {
                found = true
            }
        }
    }
    return found
}

/*
===============================
= ADDING NEW DOCKER HOST CODE =
===============================
*/
//injected variables from jenkins envinject:
//cloudName existing or current docker cloud name
//label     desired label for build node
//image     image location (eg. dockers.tf.riotgames...)
def dockerPlugin = Jenkins.instance.pluginManager.getPlugin("docker-plugin").getPlugin()
def dockerServers = dockerPlugin.getServers()
def cloudExists = false
def labelExists = labelInCloud(dockerServers)
if (label.equals("")) {
    println("Label cannot be empty. Slave not added")
} else if (labelExists) {
    println("Specified label already exists. Slave not added.")
} else {
    dockerServers.each() { dockerServer ->
        //label checking
        ArrayList<DockerCloud> templates = dockerServer.templates
        if (dockerServer.name.toLowerCase().equals(cloudName.toLowerCase())) {
            cloudExists = true
            println("Adding "+ label + " to cloud[" + cloudName + "]...")
            def retentionStrategy = new DockerOnceRetentionStrategy(idleMinutes)
            def sshConnector = new SSHConnector(22, credentialsId, jvmOptions, javaPath, prefixStartSlaveCmd,
                                                suffixStartSlaveCmd, launchTimeoutMinutes * 60)
            def launcher = new DockerComputerSSHLauncher(sshConnector)
            def templateBase = new DockerTemplateBase(image, dnsString, dockerCommand, volumesString,
                                                      volumesFrom, environment, lxcConfString, hostname,
                                                      memoryLimit, cpuShares, bindPorts, bindAllPorts,
                                                      privileged, tty, macAddress)
                                                      
            def template = new DockerTemplate(templateBase, label, remoteFs, remoteFsMapping, instanceCapStr,
                                              mode, numExecutors, launcher, retentionStrategy, removeVolumes,
                                              pullStrategy)
            templates.add(template)
        }
    }
    if (!cloudExists) {
        println("Cloud not found. Please specify a currently existing cloud.")
    }
}
println()