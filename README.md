# Microservice, SpringBoot, SpringCloud, Docker 

# Git Commands to move local projects to remote git repository
1) rd .git /S/Q  is used to remove any existing git directories 
2) git init
3) git add .
4) git commit -m "My first commit"
5) git remote add origin https://github.com/ravikanthcopparapu/microservice.git
6) git push -u origin master

# Microservices are developed using 
Spring Boot 2.4.x+ & Spring Cloud 2020.x+
Service Registry using Eureka Naming Server
Load Balancing with Spring Cloud LoadBalancer (replacing Ribbon)
API Gateway with Spring Cloud Gateway (instead of Zuul)
Circuit Breaker with Resilience4j (instead of Hystrix)
Distributed Tracing with Zipkin
Asynchronous Communication using Rabbit MQ

# Maven Command to convert the service into a docker image
   mvn spring-boot:build-image

# Docker Compose Command to run all Services that are configured in yaml file
   docker-compose up
   
   
 
