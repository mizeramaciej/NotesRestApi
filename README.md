# NotesRestApi
Project management in Apache Maven

Github repository:
git clone https://github.com/mizeramaciej/NotesRestApi.git

Used database:
    Apache db-derby-10.14.1.0

Setup database:
    1./bin/startNetworkServer
    2./bin/ij.bat
        When creating:
            connect 'jdbc:derby://127.0.0.1/PolSource;create=true';
        When connecting:
            connect 'jdbc:derby://127.0.0.1/PolSource';

Persistence layer:
     Hibernate 5.2.14.Final

REST API:
    JAX-RS 2.1

SDK: 
    1.8
Languagle level:
    9


For test:
    Glassfish jersey 2.22.2
    JUnit 4.12
    In-memory database:
        H2 version 1.4.194

EE Aplication Server:
TomEE 8.5.20


Examples of usage:

curl -X GET localhost:8080

curl -X POST -d title=Title -d content=SomeContent localhost:8080/note

curl -X PUT -d title=Another -d content=Interesing localhost:8080/note

curl -X PUT -d title=Another -d content=ChangedInteresing localhost:8080/note

curl -X GET localhost:8080/note/1

curl -X GET localhost:8080/note/Title

curl -X DELETE localhost:8080/note/1

curl -X DELETE localhost:8080/note/Another

