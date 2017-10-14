# TestContainers Dynalite Module

Testcontainers module for [Dynalite](https://github.com/mhart/dynalite). Dynalite is a clone of DynamoDB, enabling local testing.

See [testcontainers.org](https://www.testcontainers.org) for more information about Testcontainers.

<!--[![Build Status](https://travis-ci.org/testcontainers/testcontainers-java.svg?branch=master)](https://travis-ci.org/testcontainers/testcontainers-java)-->

## Usage example

Running Dynalite as a stand-in for DynamoDB in a test:

```java
public class SomeTest {

    @Rule
    public DynaliteContainer dynamoDB = new DynaliteContainer();
    
    @Test
    public void someTestMethod() {
        // getClient() returns a preconfigured DynamoDB client that is connected to the
        //  dynalite container
        final AmazonDynamoDB client = dynamoDB.getClient();

        ... interact with client as if using DynamoDB normally
```

## Why Dynalite for DynamoDB testing?

In part, because it's light and quick to run. Also, please see the [reasons given](https://github.com/mhart/dynalite#why-not-amazons-dynamodb-local) by the author of Dynalite and the [problems with Amazon's DynamoDB Local](https://github.com/mhart/dynalite#problems-with-amazons-dynamodb-local-updated-2016-04-19).

## Dependency information

### Maven

```
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>dynalite</artifactId>
    <version>1.4.3</version>
</dependency>
```

### Gradle

```
compile group: 'org.testcontainers', name: 'dynalite', version: '1.4.3'
```

## License

See [LICENSE](LICENSE).

## Copyright

Copyright (c) 2015, 2016 Richard North and other authors.

See [AUTHORS](AUTHORS) for contributors.
