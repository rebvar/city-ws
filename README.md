# The location web service.

### Build using Travis-CI

[![Build Status](https://travis-ci.org/rebvar/city-ws.svg?branch=master)](https://travis-ci.org/rebvar/city-ws)

This repository contains the source code for the location task. The project is implemenmted according to the microservice architecture. The implementation also extends the requirements by adding new features, beside the requirements. The following contains the implementation details.

# Run the code locally
The code can be executed using spring tool suite or from command line using the following commands:


```
mvn dependency:tree
```
and after compilation and build: 

```
mvn spring-boot:run
```


Alternatively, one can build the jar file first using 

```
mvn clean install -DskipTests
```

And after building the jar file which will be located in target folder, it can be executed using 

```
java -jar target/filename.jar 
```

where filename is the name of the generated jar file.


# Run the code in docker
The Dockerfile and docker-compose.yml files, contain build information for creating the docker images. The Dockerfile, contains the name of the application configuration to be used when deploying to docker. So, it will identify the environment and whether it is executed in docker or not through this docker file.

To create the docker file, one needs to run

```
 docker-compose up --build --force-recreate
```

which will rebuild the docker image with new source code. The command creates the docker images  if they do not exist. It also configures the MySql container which is used by the application container to include the data. Please note that this command runs the created images as well. So, a parameter is specified for the application to restart if it has a problem, which happens when it takes longer for MySql to start than the app. One can create the images as follows:

```
docker-compose up --build --no-start --force-recreate
```
which creates the images but does not execute them. After buidling the images one can run the containers separately to insure correct behaviour as follows:

```
docker-compose up r_mysql
docker-compose up app
```

The docker version uses application-docker.properties file which contains r_mysql:3306 instead of localhost:3306 as the MySql database server in application.properties and application-local.properties.

# Implementation details and tools

## Development Environment
I used the Spring tools suite for implementing this project. The spring tool suite provides a convinient development environment based on eclipse which is familiar and easy to use. I used docker desktop for windows to build the images and postman to test the rest endpoints.

## Production Database
I used a MySql database for production purposes. The application.properties file in resources, must contain the username, password and connection string, including the database name. The specified user must have suitable permissions. The permissions are specified in the docker-compose.yml file for docker installation and the tests use H2 in memory database which is defined in application-test.properties file.

## Testing Database
I used H2 in memory database for testing purposes. I am aware of few differences and potential inconsistensies. However, with standard coding practices, those concerns would be minimal, and of no importance for this particular task. The application-test.properties file contains the options required for a H2 in memory database. 

## Port and Run
The application run details are provided in application.properties file which include the port number and the context path. The backend has the following options.

```
server.servlet.context-path=/city-ws
server.port=8080
```

so the endpoints are available at http://server/city-ws/endpoint

# Backend Components
The application is comprised of two main components. These include User and Location classes/entities/services/etc. 
I have tried to keep the code clean with meaningful names and structure, to minimize the requirements for excessive documentations. 
For each of the above components, the corresponding Entities, DTOs, Response Modesl, Repositories and Services are Created. 


# Backend Security

I implemented the security using the spring security framework to restrict access to the rest endpoints. The rest API has multiple open endpoints to login and register. Beside these two, I have specified an option in the application properties files which enables or disables security. If security_enabled option is specified as true, the post and delete endpoints, as well as loading data from the resource file are restricted, while the Get methods, whoch query the data are open and can be queried without authorization and authentication. 

One can login and register using the following examples: 

```
curl -X POST \
  http://localhost:8080/city-ws/users \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
	"name":"rebvar",
	"email":"rebvar@gmail.com",
	"password":"123"
}'
```

The reponse to this looks as follows: 

```
{
    "name": "rebvar",
    "email": "rebvar@gmail.com",
    "userId": "86be659b-7a46-4c73-9acf-1862644d069e"
}
```

which denotes successful registration.

One can login using:

```
curl -X POST \
  http://localhost:8080/city-ws/users/login \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
	"email":"rebvar@gmail.com",
	"password":"123"
}'
```

The resulting Authorization token which is similar to the following: 

```
Authorization→TOKEN_eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4NmJlNjU5Yi03YTQ2LTRjNzMtOWFjZi0xODYyNjQ0ZDA2OWUiLCJleHAiOjE1NTg5NDc3NDZ9.D5sV7IfYxJ9wXZ-clY1cV26JRUZ3-wl0O51TeDNLzKl0LYbUvdE4Hpcnt6SQXaSvoCxsSY2Mg5UoVD5XtKo6Ig
```

can be used with the protected requests to access the endpoints.


# Search

Search queries can be sent to the rest api endpoint /map in the following formats: 
```

/map/city/{id}
/map/country/{id}
/map/continent/{id}

/map/search/city/{partial_name}
/map/search/country/{partial_name}
/map/search/continent/{partial_name}

/map/search/country/{unique_id of the country}/city/{partial_name}
/map/search/continent/{unique_id of the continent}/city/{partial_name}
/map/search/continent/{unique_id of the continent}/country/{partial_name}

```

which finds cities, countries and continents by exact unique ids, search for cities, countries, continents using a partial name, and search for cities in a specific country, cities in a specific continent, and countries in a specific continent.

Examples are as follows : 

```
Request : localhost:8080/city-ws/map/search/city/a    -> All cities that include letter a

Request : localhost:8080/city-ws/map/search/county/fi  -> all countries that have fi in their names

Request : localhost:8080/city-ws/map/search/country/b6598cbe-3843-4a8f-93d9-c3e2440c5d6a/city/n   -> All cities that have include letter n in country with id b6598cbe-3843-4a8f-93d9-c3e2440c5d6a


Request : localhost:8080/city-ws/map/country/b6598cbe-3843-4a8f-93d9-c3e2440c5d6a      -> Gets the information for the exact country with id of  b6598cbe-3843-4a8f-93d9-c3e2440c5d6a


```

Unsuccessful searches, return an empty list. This is a good pattern in defensive programming to avoid Nulls and unnecessary exceptions and unity in handling requests.


#Creating and deleting data

Data can be fed into the model using the post methods and models for city, country and continent. If country and/or continent do not exist for a city, with valid data they will be created when a new city is posted:

Consider the following example:

```
curl -X POST \
  http://localhost:8080/city-ws/map/city \
  -H 'Accept: application/json' \
  -H 'Authorization: TOKEN_eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4NmJlNjU5Yi03YTQ2LTRjNzMtOWFjZi0xODYyNjQ0ZDA2OWUiLCJleHAiOjE1NTg5NDcxOTF9.RbP7mQR9VXd1kvnNkE3IbTqN8FGJbcItfhR6k2qhk9LR-JMQ7TduZOwkTzER0RQC852mgsJtP2n7zygbNSLGXg' \
  -H 'Content-Type: application/json' \
  -d '{
	"continentName":"Europe",
	"countryName":"Finland",
	"name": "pori" 
}'

```

This data will create the city pori if it does not exists. Prior to creating the city, the country and continent are validated. Either country name or id, must be given. The name is requirred for continent if it does not exist in the database. Otherwise, it can be extracted from the countryEntity. The method, throws an exception if none of country and continent are specified. The method returns a list containing the city in both cases of it, existing and if it is created, following the defensive programming pattern.

The city can be deleted using the id as follows:

```

curl -X DELETE \
  http://localhost:8080/city-ws/map/city/90b968be-eb62-4d1b-a641-e06f1d1c535e \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -H 'Authorization: TOKEN_eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4NmJlNjU5Yi03YTQ2LTRjNzMtOWFjZi0xODYyNjQ0ZDA2OWUiLCJleHAiOjE1NTg5NDcxOTF9.RbP7mQR9VXd1kvnNkE3IbTqN8FGJbcItfhR6k2qhk9LR-JMQ7TduZOwkTzER0RQC852mgsJtP2n7zygbNSLGXg'

```

Similar endpoints exist for county and continent. The delete method uses DELETE request method. 


The data can also be inserted from a resource file included in the resource folder under data folder and name data.txt. To load the data from this file to the database, one should run :

```
curl -X POST \
  http://localhost:8080/city-ws/map/admin/bulk-load/200 \
  -H 'Accept: application/json' \
  -H 'Authorization: TOKEN_eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4NmJlNjU5Yi03YTQ2LTRjNzMtOWFjZi0xODYyNjQ0ZDA2OWUiLCJleHAiOjE1NTg5NDc3NDZ9.D5sV7IfYxJ9wXZ-clY1cV26JRUZ3-wl0O51TeDNLzKl0LYbUvdE4Hpcnt6SQXaSvoCxsSY2Mg5UoVD5XtKo6Ig' \
  -H 'Content-Type: application/json' \
```

This method returns the number of items that were inserted or were there before in the dataset. The value specified, shows the number of lines that need to be inserted into the database. This process can be time consuming and including it in the endpoint is a bad practice. But I exposed it this way for your convinience.


# Tests 

Relevant JUnit tests are specified as examples of testing. For this, I have used Mockito for dependency injection. The repositories are tested using the H2 in memory database. 
The services are tested using Mockito injection approach. I have provided a test suite for the assignment. 
I have not implemented a test for every single part of the code. The reason for this is the lack of time and will be implemented if required. Current implementations, are there to show my understanding of the frameworks and methods. I have implemented the first step in a CI pipeline by including the travis CI build in the process. The .travis.yml file contains the configuration for the running the tests in travis. The status of the repository is shown at the top, which is read from travis-ci

# Other 

There probably are few more things that I have done which I might be missing in this readme, but will be happy to describe and clarify.
