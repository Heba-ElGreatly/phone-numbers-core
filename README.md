# Phone Number Validation(backend)
This application is to validate phone numbers for specific patterns.

## Tools
spring boot
docker
sqlite3 database

## How To Run java application:
open cmd at code location then build the code using --> mvn clean package -DskipTests
and use this one to run the app --> java -jar {jar path}\PhoneNumbersValidation-1.0-SNAPSHOT.jar

There are two APIs one for listing all customers 
and the other for listing the filtered customers(/filter).
filterd API takes page number and page size as parameters, you can use http://localhost:8080/customers/filter?pageNo=1&pageSize=5

## Docker
open cmd at backend code directory then follow the follwing commands to build docker image:
build java application using maven --> mvn clean package -DskipTests
then use this command to build the image --> docker build -t phone-numbers .
and to run the container use --> docker run -d -p 8080:8080 phone-numbers
