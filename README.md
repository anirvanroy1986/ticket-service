# ticket-service
A Theater ticketing system 

Assumptions:

1. Each Rows are labeled as R-1, R-2, R-3,..,etc
2. Seats are assigned based on viewing experience, hence seats from top most rows are assigned first
3. If at anytime, number of Seats requested for hold is greater than number of available seats then request is not processed further
4. No REST API is written to test the application.
5. No database is used to persist the values. HashMaps in the Processor class is used for persisting and retrieving values


**Build**

The project is a Gradle project. To build, open up your Terminal and fire up the following commands:
```shell
$ cd ticket-service
$ gradle build

This would build the application and run JUnit tests. 

To create JAR

$gradle createJar

Run the application
$ java -jar ticket-service-0.1.0.jar