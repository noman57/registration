# Registration wizard
Backend for registration

## Pre-requisites

   * Java 11
   * Apache Maven 4.0.0
   * Spring boot 2.4.0
   * H2 in memory database


## Application Requirements

1. Create a user account.
2. Choose which whether you are a dog or cat person.
3. Based on the input of the first step ask which are their favorite breeds.
4. Submit and view their data.

## Application flow
 * Create a user using ```/user``` endpoint
 * authenticate using ```/authenticate``` end point and get the jwt token
 * Get user info using ```/users/info``` endpoint by providing the token
 * A test user is available with email Id ```test@test.com``` and password ```password```

### Running application

Importing the project as maven project from you  ide 
and running as a spring boot application should work.

To run using terminal please from project home  to run with mvn
```
mvn install
```

```

mvn spring-boot:run
```

running with docker
```
 docker build -t noman57/registration .
```

```
 docker run  noman57/registration .
```

you can also pull a public docker image  from docker hub.
Build from local jenkins pipeline

```
docker pull noman57/registration:latest
```

## Project Architecture and features

   * Uses typical Rest architecture 
   * Has Spring security JWT token based authentication design
   * Follows MVC design pattern
   * Maintainable and extendable (adding update and delete methods as well as adding roles)
   * Global error handling  to improve response messages.
   * Uses Spring global logging 
   * Lombok removes boilerplate codes
   * It uses Javax validation libraries validates incoming DTOs.
   * Implements Spring Java based initializer
   * Swagger Ui was added for rest endpoint debugging http://localhost:8080/swagger-ui/
   * publicly the swagger will be  available at http://18.185.48.80:8080/swagger-ui/ upon request 
  
## Challenges
The design was bit tricky since there is both advantages and disadvantages of having one or multiple calls to the server.
I opted for single call solution for better simplicity. 
For business who want to send a reminder to clients who did not complete the flow can be traced back.  
From technical point of view one request is the better solution while from the business point of view multiple request.

I opted for enum value for preferred pet rather than having a separate table.


Finding enough time to work on assignment after office hours with a toddler running around 

## For future Considerations 
   * email verification
   * Confirm password validation
   * Update and other user crud method 
   * Role based Authentication


   


## Additional Libraries
   * Project uses Lombok to remove boilerplate code. Please visit https://projectlombok.org for more details.
   * I really love swagger. When working with the backend it's really great way to test out your endpoints. More info can be found on their website https://swagger.io
   
## External Resources

Spring boot 

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

Spring data Jpa 

https://spring.io/projects/spring-data-jpa

Spring specification for filter

https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/

JAXa validation

https://www.baeldung.com/javax-validation

Jwt token based authenticaion
https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world
