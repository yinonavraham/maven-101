# Minimal POM

This project examples the very minimal `pom.xml`.  
The only required fields are: `groupId`, `artifactId` and `version`

Although the project's POM does not contain much, it inherit a lot from the super POM.  
To see the effective pom, use the following help command:

```bash
mvn help:effective-pom -Doutput=effective-pom.xml
```

The command above calculates the effective POM and saves it to a file named `effective-pom.xml`.
