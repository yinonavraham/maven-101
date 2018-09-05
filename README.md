# maven-101

Maven examples, created as part of a Maven 101 session given at eBay

## Useful `mvn` Commands

* [Help Plugin](#help-plugin)
* [Dependency Plugin](#dependency-plugin)

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
