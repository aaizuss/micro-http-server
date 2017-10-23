# Micro HTTP Server
This is a very simple HTTP Server written in Java. My [cobspec](https://github.com/aaizuss/cobspec) repo demonstrates how to use this to create web applications.

### Dependencies
* [Java SE 8](http://docs.oracle.com/javase/8/docs/)
* [Maven](https://maven.apache.org/)
* [JUnit 4.12](http://junit.org/junit4/)

### Compilation
* `mvn package`
* `mvn install:install-file -Dfile=./target/micro-server-1.0-SNAPSHOT.jar -DpomFile=./pom.xml`

### Tests
* `mvn test`

## Usage
You can include this library with Clojars:

[![Clojars Project](https://img.shields.io/clojars/v/com.aaizuss/micro-server.svg)](https://clojars.org/com.aaizuss/micro-server)
