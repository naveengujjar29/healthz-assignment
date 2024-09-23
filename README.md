# Spring Boot Health Check Application

This is a simple Spring Boot application that provides a `/healthz` endpoint to check the connection to a MySQL database. The application is built using Maven and can be run as a standalone JAR file.

## Prerequisites

Before you begin, make sure you have the following installed on your system:

- [Java 21+](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/what-is-corretto-21.html)
- [Maven 3.6+](https://maven.apache.org/install.html)
- [MySQL Database](https://dev.mysql.com/downloads/mysql/)

## Build the Application

To build the application, you need to compile the code and package it as a JAR file using Maven. Execute the following command in the root directory of the project:

```bash
mvn clean package
```

This will create a runnable JAR file in the target/ directory, for example:
```bash
target/healthz-assignment-0.0.1-SNAPSHOT.jar
```

## Run the Application
After building the application, you can run it using the java -jar command as follows:
```bash
java -jar target/healthz-assignment-0.0.1-SNAPSHOT.jar
```


## /healthz Endpoint
The application provides a /healthz API endpoint that can be used to check the MySQL database connection.

Execute the /healthz API
To check the health of the database connection, you can send a GET request to:
```bash
http://localhost:8080/healthz
```

### If connection is good then below response will be returned.
```bash
curl -vvv  http://localhost:8080/healthz
*   Trying ::1:8080...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /healthz HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.65.0
> Accept: */*
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 200
< Cache-Control: no-cache
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 38
< Date: Mon, 23 Sep 2024 08:53:37 GMT
<
* Connection #0 to host localhost left intact
Database connection is up and running.  
```

###  If connection is not established then below response will be returned.
```bash
 curl -vvv  http://localhost:8080/healthz
*   Trying ::1:8080...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /healthz HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.65.0
> Accept: */*
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 503
< Cache-Control: no-cache
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 42
< Date: Mon, 23 Sep 2024 08:52:56 GMT
< Connection: close
<
* Closing connection 0
Database connection is not up and running. 
```

