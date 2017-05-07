// If you need to determine which plugins you're Jenkins instance requires, use this workflow:
//
// 1. Start your existing Jenkins instance
// 2. Use the Plugin Manager in "Configure Jenkins" to install needed plugins and resolve dependencies
// 3. Run this script in the System Groovy Console with the 'Groovy' plugin
// 4. Replace the plugins.txt file contexts with the resulting output

def plugins = Jenkins.instance.pluginManager.plugins.sort()

plugins.each() { plugin ->
  println plugin.getShortName() + " " + plugin.getVersion()
}

//println "\nPlugin Count: " + plugins.size()

// Print one last time so the output isn't cluttered with .each()'s return of the list
println() 