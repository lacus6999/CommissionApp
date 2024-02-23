# CommissionApp documentation

## Introduction

The CommissionApp is a job application with the main purpose of calculating commissions for business associates in the
format of XML. It commonly uses the instance patterns alongside with its own logging mechanism, XML and test
implementation. These were inspired by Spring-Boot, Jackson, JUnit and logging libraries.

## Running the application

As of requirements you only need a Java 11 installed on your machine, since there is no external packages used.
In order to package the application I used IntelliJ's built-in artifact creator. To run the application use the
following command:

```shell
java -jar CommissionApp.jar <param>
```

The parameter is the name of the document you pass as an argument. This document needs to be next to the application
inside a folder called _resources_ (you do not need to pass the file path). The output is saved to a file called
_BusinessAssociates.xml_.

User `java -jar CommissionApp.jar --test` to test the application.

## Package Description

### [Calculator](src/main/calculator)

This holds the conditions, that are used to determine the commission intervals alongside with some helper functions.

### [Commission](src/main/commission)

This package holds the [CommissionType](src/main/commission/CommissionType.java) next to 2 subpackages. There are 2
types of [parsers](src/main/commission/parser/CommissionParser.java) with their
corresponding [CommissionToXMLParsingObject](src/main/commission/parser/CommissionToXMLParsingObject.java).

- [dto](src/main/commission/dto)

This package contains the different intermediate representations for the commission data, that are later used by the
parsers.

- [parser](src/main/commission/parser)

This package holds the parsing classes, that are used to parse
the [CommissionRawData](src/main/commission/dto/CommissionRawData.java) into a more usable format. This format will
later be parsed to and XML string.

### [Io](src/main/io)

In order to serve the application with data the [CommissionIO](src/main/io/CommissionIO.java) uses a reader. Later, when
the XML got created, this IO is used to gather and save the result.

### [Logger](src/main/logger)

The [Logger](src/main/logger/Logger.java) implemented in this package is used throughout the runtime to keep the
user up to date with the state of the application. Besides DEBUG all the logfiles gets saved into a *logs* folder next
to the jar file. All the logs that was created in the same day are saved into one file. This Logger can be disabled (
useful when testing).

### [XML](src/main/xml)

In order to output a _.xml_ file there is an [XML](src/main/xml/XML.java) class, that is used for parsing (mostly) any
object into the format of XML. It is built around the collection (MAP) fields having to be declared in each
subclass (useful for naming fields in the result xml) for better result. There is also a node system,
called [XMLNode](src/main/xml/XMLNode.java), that is used for a tree-like representation of the object, that was passed
in.

### [CommissionApp](src/main/CommissionApp.java)

This is the driver class of the application. It connects the different parts together to work as a system.

## Testing

The application is using its own test implementation. The tests are written inside
the [testclasses](src/test/testclasses) package. Most of the important bits are tested, although there was not enough
time to implement some integration test.

The test are ran automatically using the [TestRunner](src/test/TestRunner.java) class. It seeks the testclasses package
and runs each test method marked with the [@Test](src/test/helper/Test.java) annotation.

## Additional Information

The application is capable of extracting the commissions into multiple structures. These are marked by
the [CommissionToXMLParsingObject](src/main/commission/parser/CommissionToXMLParsingObject.java) interface and are meant
to be easily expandable. There are currently 2 implementations:

- [BusinessAssociatesByType](src/main/commission/dto/BusinessAssociatesByType.java): commissions are broke down by their
  type
- [BusinessAssociatesSummed](src/main/commission/dto/BusinessAssociatesSummed.java): commissions are summed and
  represented by their business associate (this is the one that was asked for)