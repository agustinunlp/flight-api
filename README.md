# flight-api

Create Microservice to manage flight tickets
Business Requirement

1. You need to create a service to manage flight tickets. You should be able to Create and Query.
A flight ticket has the following parameters:
i. Departure Date
ii. Arrival Date
iii. City Of Origin
iv. Destination City
v. Passenger Name
vi. Passenger Age
vii. It has a luggage storage
viii. Price
ix. Departure Time
x. Arrival Time

The ticket will generate an ID (itineraryID) with which it can be consulted.
Two endpoints.
 
i. One to create with the parameters of the itinerary. 
ii. Another to retrieve by ID

Technologies
Java or Kotlin Programming Language
Use the Spring  Boot framework
You can use any database or file repository, as you want the developer.
Must be REST API
Upload to apublic code repository, such as  Github..

Optional technologies
Docker. Create a Docker File
Unit test. Create unit tests

****
# Resolution

- I have Developed a Rest API using Spring boot. It has two endpoints.
- I used MongoDB as database.
- I have added Swagger 2 as API documentation. http://localhost:8080/swagger-ui.html
- I used Jmockit to create unit test. 
To run this solution you have to use any mongodb server and modify application.properties file. You also have to create a Collection calles 'itineraries' 

# Problems

I had an issue with the jmockit initialization due to junit version. Honestly i couldn't fix it and I have to continue with my current task. The Tests run OK from the IDE but htere is an error when you build the maven project. So please build it with -Dmaven.test.skip=true  
