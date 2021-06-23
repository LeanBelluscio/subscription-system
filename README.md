

<h3 align="center">Adidas Subscription System Challenge</h3>
>

---



## 📝 Table of Contents

- [About](#about)
- [Assumptions](#assumptions)
- [Getting Started](#getting_started)
- [Usage](#usage)
- [Deployment on Docker](#deployment)
- [Docker Config](#docker_config)Z
- [CI/CD Pipeline Proposal (BONUS 1)](#pipeline)
- [Improvement Opportunities](#opportunities)
- [Authors](#authors)

## 🧐 About <a name = "about"></a>

  Coding Java Backend Challenge

  Write a Subscription System. The system will be composed of three microservices:
    - ### Public Service: 
            Backend for frontend microservice to be used by UI frontend.
    - ### Subscription Service:
            Microservice implementing logic, including persistence of subscription data in a database and email notification to confirm process is complete.
    - ### Email Service:
            Microservice implmenting email notitfication.

  ### Tech Stack:
   -   Java JDK 11
   -   Maven 3.8.1
   -   Spring Boot 2.5
   -   Swagger 2
   -   H2 database
   -   Docker
 
## 🤝 Assumptions <a name = "assumptions"></a>

-   A subscription is detemined by the user and the campaign. A user can subscribe to multiples campaigns
-   A user is identified by the email
-   Private services (subscription service and email service) are secure and in a private network
-   Public services (Publication service) are not secure. It is in a public network in order to communicate with UI, and in private network in order to communicate with Subscription service.

## 🏁 Getting Started <a name = "getting_started"></a>

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See  [deployment](#deployment) for notes on how to deploy the project on a docker container ..

### Prerequisites

Make sure you have installed Maven and Docker

```
    -   Maven: <a href="https://maven.apache.org/install.html" target="_blank">https://maven.apache.org/install.html</a>
    -   Docker: <a href="https://docs.docker.com/get-docker/" target="_blank">https://docs.docker.com/get-docker/</a>[]

```

### Run locally

All services run independentley from each other.

Using Maven
```
In the root service folder (i.e: publication-service/) run
mvn clean package -P local
mvn spring-boot:run -P local
```

Or

```
In the root service folder (i.e: publication-service/) run
mvn clean package -P local
java -jar .\target\publication-1.0-SNAPSHOT.jar

```
 

## 🔧 Running the tests <a name = "tests"></a>

    In each root service folder (i.e: publication-service/) you should exucute **mvn test** command

    ```
        mvn test

     ```

## 🎈 Usage <a name="usage"></a>

All services uses Swagger for Api Documentation. You can access to each Api defintion service

-   ### Publication Service:
    - Swagger URL:   http://localhost:8082/swagger-ui.html
    - Get access token for authenticate on private services (Subscription and Email Service):
        Execute http://localhost:8082/publication/api/devops/auth it will return the following response
        
    <p align="center">
    <img width="100%" src="images/getJWT.png" alt="get JWT"/>
    </p>

        ```
        Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJhZGlkYXNKV1QiLCJzdWIiOiJhZGlkYXMiLCJhdXRob3JpdGllcyI6WyJQVUJMSUNBVElPTl9VU0VSIl0sImlhdCI6MTYyNDQ0MTQyNSwiZXhwIjoxNjI0NDQ3NDI1fQ.RPcdFmwph4AnY5u8P0vdZf4746hTN7uus6P94Fs2yYKNTcdHEaIEbVQdySaSC--ceGT22PXChvxjZX9soDlzCA
        
        ```

    Use this response as "Authorization" header to private services. You can use **curl** or "Authorize into private services Swagger UI

    <p align="center">
    <img width="100%" src="images/swaggerui.png" alt="Swagger UI"/>
    </p>

    Click **Authorize** button

    <p align="center">
    <img width="75%" src="images/authorizations.png" alt="cli output"/>
    </p>

     Copy the access token returned by auth endpoint into **value** and press Authorize

## 🚀 Deployment on Docker <a name = "deployment"></a>


  To deploy in Docker you should follow the following steps:

- ### Build all services:
    For each service run into each service root folder **mvn clean package -P dev** it will generate the jar files that will be copied and deployed in a docker container. Note her we use **-P dev** insetead **-P local** it means that the jars will be generated using dev profile
    
    ```
     ..adidas-challenge\subscription-service> mvn clean package -P dev
     ..adidas-challenge\publication-service> mvn clean package -P dev
     ..adidas-challenge\email-service> mvn clean package -P dev

    ```
- ### Build Docker images

    Into the parent folder (the folder where the three services folders are contained) run **docker compose build** it will creates one docker image for each service

    ```
     ..adidas-challenge> docker compose build

    ```
    Verify that the images were created running **docker images** command dockerimg.png

    <p align="center">
    <img width="75%" src="images/dockerimg.png" alt="cli output"/>
    </p>

    Now we have the images for each service. The next step is to deploy those images in the container
    Into the parent folder Run **docker compose up -d** it will start with the deployment according to the config in docker-compose.yml file and the Dockerfiles.

    ```
     ..adidas-challenge> docker compose up -d

    ```
    <p align="center">
    <img width="50%" src="images/dockerup.png" alt="cli output"/>
    </p>
    
    Verify that the images were deployed running **docker container ls** command.

    ```
     ..adidas-challenge> docker container ls

    ```

    <p align="center">
    <img width="100%" src="images/dockercontainer.png" alt="cli output"/>
    </p>

    To stop the containers you should run **docker compose down** command

    ```
     adidas-challenge> docker compose down

    ```
    <p align="center">
    <img width="100%" src="images/dockerdown.png" alt="cli output"/>
    </p>

## ⛏️ Docker Config <a name = "docker_config"></a>

For deploy in Docker I used docker-compose.yml, here we have configured:

-   ### Container Names: 
    The names will be used as hostname for linked containers

    ```
     publication-service: 
        container_name: publication-service

    ```

-   ### Mapping ports: 
    Currently each container is mapped with a local port. Ideally just Public Service container should be exposed, and the private services will be in a private network.
-   ### Networks:
    For the communication between services a Private network will be created, also I configured a public network in order to allow the communication between public services and UI frontend. For development and testing purpose all services has a local port mapping. It can be disabled removing **port** attributes from docker-compose.yml

    Network definition

    ```
     networks: 
        adidas-public-network:
            name: adidas-public-network
        adidas-private-network:
            name: adidas-private-network

    ```

    Network Usage
    
    ```
     publication-service: 
        container_name: publication-service
        build: ./publication-service/
        image: adidas-challenge/publication-service
        ports:
            - "8082:8082"
        networks: 
            - adidas-public-network
            - adidas-private-network

    In this example publication-service container will be in the public and the private network
    ```

## 📝  CI/CD Pipeline Proposal (BONUS 1)<a name = "pipeline"></a>

The proposed CI/CD pipiline is similar to the steps explained in [deployment](#deployment) section.

### Deployment
-   Clone project from [repository](https://github.com/LeanBelluscio)
-   Into the parent folder (adidas-challenge) you can run **mvn clean pakage -P dev**, it will generate the jars for all services. Additionaly you  can run **mvn clean pakage -P dev** individualy for each services
-   Into parent folder run **docker compose build**, it will generate the docker images for each service
-   Into parent folder run **docker compose up -d**, it will creates the docker containers and deploy the services

    ```
        mvn clean package -P dev
        docker compose build
        docker compose up -d
    ```
    Note: to stop the services run **docker compose down**

    ```
        docker compose down
    ```

## ✨ Improvement Opportunities <a name = "opportunities"></a>

    As this is a POC due the limited time slot, there are some future improvements such as:

-   Full authentication mechanism, it means varify expired token, refresh token, etc
-   Create a **common** library for share objects (i.e: Request models that are shared across the serivices)
-   Use a non in memery database
-   Configure the project for Kubernates
-   Improve cache mechanism


## ✍️ Authors <a name = "authors"></a>

- [@leanbelluscio](https://github.com/LeanBelluscio)




