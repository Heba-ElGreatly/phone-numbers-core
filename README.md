# Phone Number Validation(backend)
This application is to validate phone numbers for specific patterns.

## Tools
spring boot <br /> 
docker <br /> 
sqlite3 database <br /> 


## Description
There are two APIs one for listing all customers  <br /> 
and the other for listing the filtered customers(/filter).  <br /> 
filterd API takes page number and page size as parameters, you can use http://localhost:8080/customers/filter?pageNo=1&pageSize=5


## How To Run java application
- open cmd at code location <br />
- build the code using --> mvn clean package -DskipTests <br />
- run the app --> java -jar {jar path}\PhoneNumbersValidation-1.0-SNAPSHOT.jar 


## Docker
To build docker image: <br /> 
- open cmd at backend code directory. <br /> 
- build java application using maven --> mvn clean package -DskipTests  <br />
- build the image --> docker build -t phone-numbers .   <br />
- run the container use --> docker run -d -p 8080:8080 phone-numbers
