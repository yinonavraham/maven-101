# maven-101

Maven examples, created as part of a Maven 101 session given at eBay

## Useful `mvn` Commands

### Help Plugin

The Maven Help plugin provides goals aimed at helping to make sense out of the
build environment. It includes the ability to view the effective POM and
settings files, after inheritance and active profiles have been applied, as
well as a describe a particular plugin goal to give usage information.

Start with:

```bash
mvn help:help
```

A very helpful goal is the `help:describe`. 
It helps you get usage help information for other plugins, commands and goals.  
For example, to see detailed help information on the `help:describe` goal itself, use:

```bash
mvn help:describe -Dplugin=help -Dgoal=describe -Ddetail
```
