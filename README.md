# Chapter 1: Refactoring to microservices
To understand what microservices are, we will start from a more comfortable project and tranform it to a distributed system. 
The process of transforming is a form of application modernization. Developers have been doing it for decades and as a result,
there are some ideas that we can reuse when refactoring a monolith into microservices.

## Strategy 1: Domain modeling
When Identifying the scope of our microservices, we look at the functionality a service needs to provide. 
Domain modeling is a core principle of designing loosely coupled microservices.
The goal is to ensure that each of your microservice is isolated from runtime side effects and insulated from changes in the implementation of another microservice in the system.
To help you defining the bounded context of your microservice, we extract what we expect from the functionality. 
Here are three questions that can help you with this exercise:

* Does one service need more instances(copy of a microservice) to receive more traffic than another?
* Does the user need to rely on a service to be up all the time than another service?
* Does one service changes data more often than another? 

### Exercise
Research and define the bounded context inside this project and write down why you would split it into different services.
When defined, check with your coach before you continue.
Now that we defined our bounded context, we can create for each bounded context a Spring Boot project.

* Go to [Spring Initializr](https://start.spring.io/)
You can reference to the dependencies of this project for adding it to the new Spring Boot project.
* Generate the project and unzip
* Place the folder in your local git folder and open with Intellij

## Strategy 2: Each service has its own database
To have your own database for each service it enables developers to use the type of database that is best suited for the service's needs. 
But it comes with more complexity, like what do we with coupled transactions in a distributed system? 
 
### Exercise
* Copy the code from the monolith in the proper service.
* Refactor the JPA entities so they are not coupled anymore.


### Next step
 ```sh
   git checkout chpt2-ServiceDiscovery
   ```













