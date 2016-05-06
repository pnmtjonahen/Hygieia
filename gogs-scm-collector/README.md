GogsSourceCodeCollector
=========================

Collect source code details from [Gogs](gogs.io) based on URL and branch

This project uses Spring Boot to package the collector as an executable JAR with dependencies.

Building and Deploying
--------------------------------------

Run
```
mvn install
```
to package the collector into an executable JAR file. Copy this file to your server and launch it using :
```
java -JAR gogs-collector.jar
```
You will need to provide an **application.properties** file that contains information about how
to connect to the Dashboard MongoDB database instance, as well as properties the Gogs collector requires. See
the Spring Boot [documentation](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-external-config-application-property-files)
for information about sourcing this properties file.

###Sample application.properties file
--------------------------------------
    #Database Name
    database=dashboard

    #Database HostName - default is localhost
    dbhost=10.0.1.1

    #Database Port - default is 27017
    dbport=9999

    #Database Username - default is blank
    dbusername=db

    #Database Password - default is blank
    dbpassword=dbpass

    #Collector schedule (required)
    gogs.cron=0 0/5 * * * *

    gogs.host=try.gogs.io

    #Maximum number of days to go back in time when fetching commits
    gogs.commitThresholdDays=15
