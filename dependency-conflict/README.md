# Dependency Conflict Example

## Project Structure

(the pom in the root directory is an aggregation of the 3 projects above for simplicity)

### Project 1
`com.acme.dep-conf:dep-conf-proj1:1.0`  

Dependes on:  

* `commons-cli:commons-cli:1.3`  

### Project 2
`com.acme.dep-conf:dep-conf-proj1:1.0`  

Dependes on:  

* `commons-cli:commons-cli:1.4`  

### Main Project
`com.acme.dep-conf:dep-conf-proj:1.0`  

Depends on:  

* `com.acme.dep-conf:dep-conf-proj1:1.0`
* `com.acme.dep-conf:dep-conf-proj2:1.0`

## Running the Example

Check the dependencies, run:
```bash
mvn dependency:tree

...

[INFO] ------------------< com.acme.dep-conf:dep-conf-proj1 >------------------
[INFO] Building dep-conf-proj1 1.0                                        [1/4]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ dep-conf-proj1 ---
[INFO] com.acme.dep-conf:dep-conf-proj1:jar:1.0
[INFO] \- commons-cli:commons-cli:jar:1.3:compile
[INFO]
[INFO] ------------------< com.acme.dep-conf:dep-conf-proj2 >------------------
[INFO] Building dep-conf-proj2 1.0                                        [2/4]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ dep-conf-proj2 ---
[INFO] com.acme.dep-conf:dep-conf-proj2:jar:1.0
[INFO] \- commons-cli:commons-cli:jar:1.4:compile
[INFO]
[INFO] ------------------< com.acme.dep-conf:dep-conf-proj >-------------------
[INFO] Building dep-conf-proj 1.0                                         [3/4]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ dep-conf-proj ---
[INFO] com.acme.dep-conf:dep-conf-proj:jar:1.0
[INFO] +- com.acme.dep-conf:dep-conf-proj2:jar:1.0:compile
[INFO] |  \- commons-cli:commons-cli:jar:1.3:compile
[INFO] \- com.acme.dep-conf:dep-conf-proj1:jar:1.0:compile

...
```
We can see above that maven resolved version `1.3` of `common-cli` for the main project.  
To see more information, run with verbose output:
```bash
mvn dependency:tree -Dverbose

...

[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ dep-conf-proj ---
[INFO] com.acme.dep-conf:dep-conf-proj:jar:1.0
[INFO] +- com.acme.dep-conf:dep-conf-proj1:jar:1.0:compile
[INFO] |  \- commons-cli:commons-cli:jar:1.3:compile
[INFO] \- com.acme.dep-conf:dep-conf-proj2:jar:1.0:compile
[INFO]    \- (commons-cli:commons-cli:jar:1.4:compile - omitted for conflict with 1.3)

...
```
### Note 
As can be seen above, `common-cli:1.4` is ommited because of the conflict with version `1.3` comming from `dep-conf-proj1`.
In this case, version `1.3` is selected because the dependency on project 1 is declared in the main project's pom before project 2.
To verify it, you can switch the order and run the `dependency:tree` goal again. This time version `1.4` should be resolved.

## Bottom Line

When there are dependency conflicts, by default Maven resolves the dependency declared closest to the root module. 
Closest can be for example due to the number of transitive steps to the dependency, or the order the dependencies are declared (like in this example).