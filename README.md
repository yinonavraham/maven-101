# Maven 101

[Maven](https://maven.apache.org/) examples, created as part of a Maven 101 session given at eBay.

## Examples

* [Minimal Maven Project](minimal)
* [Multi Module Project](multi-module)  
  Also demonstrates: dependency scopes, dependency management, distribution management
* [Dependency Conflict Debugging](dependency-conflict)

## Useful Command Line Options

* `-h, --help`  
  Show the usage help page of the `mvn` command line tool
* `-U, --update-snapshots`  
  Force updating snapshots (overrides the once-a-day strategy)
* `-D, --define <arg>`    (e.g. `-DskipTests`)  
  Define a system property, usually used for setting properties of plugins
* `-fae, --fail-at-end`  
  In multi module projects - fail only at the end of the run, after building all modules
  (instead of right after the first module with error)
* `-fn, --fail-never`  
  Do not fail the build at all
* `-pl, --projects <module>[,<module>]*`  
  Run a command only for specific projects (modules)
* `-P, --activate-profiles <profile>[,<profile>]*`  
  Activate a maven profile (which is defined in the pom or in the settings)

## Useful Plugins

* [Help Plugin](#help-plugin)
* [Dependency Plugin](#dependency-plugin)
* [Versions Plugin](#versions-plugin)
* [Build Helper Plugin](#build-helper-plugin)
* Surefire & Failsafe

### Help Plugin

The [Apache Maven Help Plugin](https://maven.apache.org/plugins/maven-help-plugin/) 
provides goals aimed at helping to make sense out of the build environment. 
It includes the ability to view the effective POM and settings files, 
after inheritance and active profiles have been applied, 
as well as a describe a particular plugin goal to give usage information.

Start with:

```
mvn help:help
```

A very helpful goal is the `help:describe`. 
It helps you get usage help information for other plugins, commands and goals.  
For example, to see detailed help information on the `help:describe` goal itself, use:

```
mvn help:describe -Dplugin=help -Dgoal=describe -Ddetail
```

Another example - to see which plugin corresponds to the `compile` build lifecycle phase, use:

```
$ mvn help:describe -Dcmd=compile
...
[INFO] --- maven-help-plugin:3.1.0:describe (default-cli) @ my-minimal-project ---
[INFO] 'compile' is a phase corresponding to this plugin:
org.apache.maven.plugins:maven-compiler-plugin:3.1:compile

It is a part of the lifecycle for the POM packaging 'jar'. This lifecycle includes the following phases:
* validate: Not defined
* initialize: Not defined
...
```

It also prints the other build lifecycle phases and their corresponding plugins (if any).

### Dependency Plugin

The [Apache Maven Dependency Plugin](https://maven.apache.org/plugins/maven-dependency-plugin/)
provides the capability to manipulate artifacts. 
It can copy and/or unpack artifacts from local or remote repositories to a specified location.

The most useful goal of the dependency plugin is the `dependency:tree`. 
As its name implies, it prints the dependency tree of the project.
A common use case is when you need to debug dependency issues.

For example, the following command prints a verbose dependency tree to a file named `dependencies.txt`:

```
mvn dependency:tree -Dverbose -DoutputFile=dependencies.txt
```

### Versions Plugin

The [Versions Maven Plugin](https://www.mojohaus.org/versions-maven-plugin/) 
is used when you want to manage the versions of artifacts in a project's POM.
It is very useful for automating several parts of the version management. 
It is mostly useful in multi-module projects.

For example, a common use case is to set the version for all modules in a multi-module project. 
To set the project's new version to `1.2.0-SNAPSHOT`, use:

```
mvn version:set -DnewVersion=1.2.0-SNAPSHOT
```

### Build Helper Plugin

The [Maven Build Helper Plugin](https://www.mojohaus.org/build-helper-maven-plugin/) 
contains various small independent goals to assist with the Maven build lifecycle.

Some very usefule tasks are:

* Adding source folders
* Adding resource folders
* Adding test source folders
* and more...
