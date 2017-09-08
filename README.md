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
* You can reference to the dependencies of this project for adding it to the new Spring Boot project.
* Generate the project and unzip
* Place the folder in your local git folder and open with Intellij

## Strategy 2: Each service has its own database
To have your own database for each service enables developers to use the type of database that is best suited for the service's needs. 
But it comes with more complexity by managing each database.
 
### Exercise
* Copy the code of the domain from the monolith in the proper service.
* Copy the necessary dependencies from the monoliths pom into the services pom.
* Refactor the JPA entities so they are not coupled anymore.
* Replace the properties file with a yml file and configure the in memory database (H2)
* Configure the name of the application (We will be needing it later on)
* Configure a different port on each service
* Create the import.sql in each service and input the data you got from the monolith

## End Result
Run your services and go to your localhost:<port server>/<entities>
You should see a list of data in each service. 

### Next step
 ```sh
   git checkout chpt2-ServiceDiscovery
   ```





# Chapter 2: Service Registry 
Since we are working with a distributed system, the services don't know of each other existence.
So how do we let the services discover each other for querying data, etc...? 
To tackle this issue, Spring Cloud provides us the ability to register our services with Eureka.
Eureka as a service is comparable to a phone book for your microservices.
Each service registers itself with the service registry and tells the registry where it lives.

## Exercise
* Go to [Spring Initializr](https://start.spring.io/)
* Name the project eureka.
* Add the Eureka Server dependency.
* Generate, unzip and add it to your local git folder.
* Change your properties file to a yml file.
* Configure the Eureka Server
* Link the two microservices to the Eureka server by making them a Eureka Client. 
* Have a different port when running.
* Run the three applications.

### [Documentation](http://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/1.3.4.RELEASE/)

If you found the solution, the logs will tell you that they register themselves with Eureka.

## End result
* Go to the Eureka application: localhost:<port> 
* You should now see an UI Eureka dashboard with both the microservices registered.


## Next step
 ```sh
   git checkout chpt3-ConfigServer
   ```




# Chapter 3: Spring Cloud Config Server 
Aside from discovering our services, we need a way to centralize and externalize our configuration.
In the theory class, we explained that we follow the 12 factor app as guidelines in building microservices. 
One of these principles is a strict separation of config from code.
This is because config varies substantially across deploys, code does not. 
Spring Cloud Config server provides an HTTP, resource-based API for external configuration to tackle this issue.

## Exercise 

### Spring Cloud Config Server
The Config server needs a repository where all the config is stored. 
* Go to your GitHub and add a new repository.
* Name this `ws-config` and clone the repo. 
* Add a yml file for each microservice named after the configured application name.
* Push to the remote master.

* Go to [Spring Initializr](https://start.spring.io/)
* Add the following dependencies
    * Config Server
    * Actuator
* Generate, unzip and add to your local git.
* Change properties to yml file. 
* Figure out how the config server fetches his configuration of `ws-config` for the correct microservice
    

### Spring Cloud Config Client
* Go to your microservices
* Enable the config client by adding it to your classpath. 
* Create a bootstrap.yml file. 
* Place the Spring application name in the bootstrap
* Remove the application.yml

### [Documentation](http://cloud.spring.io/spring-cloud-static/spring-cloud-config/1.2.3.RELEASE/#_spring_cloud_config_server)

## End Result
When starting up your microservices you will see a fetch happening to the config server. 
If it is successful, your work is done here. 

## Next step
`git checkout chpt4-Zuul`





# Chapter 4: Spring Cloud Zuul
To unify our traffic to one service, we will be implementing an edge service application.
Take it as the gateway to your microservices, where you can stop, pass through and modify requests to the downstream services.
If you decide to scale up your microservice to 2 instances, Zuul will load balance requests to both instances to reduce load. 
For routing to the microservices our goal is to define the correct route to the correct service. 
For example /rental is mapped on rental service and /movie on the movie service.
With the help of Eureka, Zuul will find the correct microservices to handle the requests to.

## Exercise 
* Go to [Spring Initializr](https://start.spring.io/)
* Add following dependencies
    * Zuul
    * Eureka Discovery
    * Config Client
* Generate, unzip, put it in your local git.
* Change properties into yml.
* Externalize your configuration to the ws-config repo.
* Enable the reverse proxy by adding @@EnableZuulProxy on top of your application.
* Make it possible to fetch configuration from the config server.
* Make it possible to discover the microservices.

## End result
When starting all your applications, go to your Zuul address and try out your routing to the microservices.
Example: localhost:<port>/rental/rentals

## Next Step
`git checkout chpt5-FeignClient`