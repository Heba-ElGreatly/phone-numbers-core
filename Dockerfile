FROM java:8

COPY ./target/PhoneNumbersValidation-1.0-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch PhoneNumbersValidation-1.0-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","PhoneNumbersValidation-1.0-SNAPSHOT.jar"]