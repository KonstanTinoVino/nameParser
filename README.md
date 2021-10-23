# Name Parser Spring Module

Implementation of service that is able to parse a string of a single person's name  or multiple persons' names and provide a result where it is derived what is first name(s) and what is the last name.

It must be able to parse the following names:

- John Doe
- Doe, John
- Hans-Christian Jensen
- H-C Jensen
- P. H. Kristensen
- Kristensen, P. H.
- Peter Hans Kristensen
- Peter H. Kristensen

The correct names are:

- First name: John, Last name: Doe
- First name: Hans-Christian, Last name: Jensen
- First name: P. H., Last name: Kristensen
- First name: Peter Hans, Last name: Kristensen
- First name: Peter H., Last name: Kristensen

Additionally, the service can handle a string of correctly ordered full names separated by a coma such as:

- H-C Jensen, Peter Hans Kristensen, John Doe


## Installation
The application requires the following tools:
- Java 11 or higher
- Apache Maven

Install relevant libraries through maven

```bash
mvn clean install
```
Main method of the application is NameDisambiguationApplication.java located at
```java
nameDisambiguation\src\main\java\com\elsevier\test\main\NameDisambiguationApplication.java
```

## Usage
The functionalities of the application can be accessed through the NameParsingService.java interface if the module is integrated as part of another application. Alternatively if ran as a spring booth application it can be accessed via the rest service running on localhost:8080.

The two endpoints with examples of usage are

```java
http://localhost:8080/service/parsing/normalisePersonName?person=John Doe
```


```java
http://localhost:8080/service/parsing/normalisePersonListNames?personList=H-C Jensen, Peter Hans Kristensen, John Doe