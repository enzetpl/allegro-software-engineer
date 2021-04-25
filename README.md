# Zadanie nr 3. Software Engineer

Application created for the Allegro recruitment process 

## Getting started

###
## technologies
  - Java 16
  - Spring Boot  
  - Maven
### Requirements

For building and running the application you need:

- [Java JDK](https://jdk.java.net/16/)
- [Maven 3](https://maven.apache.org)

### Run applicaton
 #### with maven
```shell
mvn spring-boot:run
```
  - app is now available at [localhost:8080](http://localhost:8080)
#### Or just use application deployed on heroku (http://google.pl)
## Using application
### API endpoints:
|                                          URL                        | Method |                 Description                       | Example|
|---------------------------------------------------------------------|--------|-----------------------------------------------|---------------------------|
|`http://localhost:8080/api/users/{username}/repos?page=`        | GET   |Get Page of user repositories (one page contains 100 repos|(https://sheltered-chamber-04457.herokuapp.com/api/users/allegro?page=1) |
|`http://localhost:8080/api/users/{username}/stars`| GET | Get sum of stars for user | (https://sheltered-chamber-04457.herokuapp.com/api/users/allegro/stars)|
|`http://localhost:8080/api/users/{username}`| GET   |Get basics user infromation|(https://sheltered-chamber-04457.herokuapp.com/api/users/allegro)  |

### Additional info
Solution uses concurrency for get stars, because for users with big collection of repositories counting stars requires a lot of http requests.
(ex mcirosoft with about 4000 repositories - https://sheltered-chamber-04457.herokuapp.com/api/users/microsoft/stars)
Application limitation is that GitHub API allows only for 60 requests in a certain period of time, so it's impossible to get stars for user for a user who has more than 6000 repositories. (One request contains max 100 repositories) 
