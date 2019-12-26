# Crystal Pizza API

Crystal Pizza API using Java Spring Boot Framework

## Getting Started
This repository contains POC for API development using Java Spring Boot Framework 

## Prerequisites
To start using or running this application in the local machine, below tools are need:
1. Java v11.0.5 or higher
2. SQL Server setup (you can use the remotely hosted server as well)
3. Any IDE to run or debug application


## Installation

1. Clone this repo on you local machine using below command.

```bash
git clone https://github.com/shekharr0705/springboot-crystalpizza.git
```

2. Open code in IDE and provide your database details in **application.properties** file.

3. Build code either with command line or using IDE tool.

4. Once build is successful you can  host API in two ways
   1. In dedicated Apache Tomcat server: [Click here](https://www.tutorialspoint.com/spring_boot/spring_boot_tomcat_deployment.htm) and follow the step to deploy the API in Tomcat server.
   2. Host an API in inbuildt tomcat server: Spring Boot initializer setup comes with in build tomcat server, we can use this for development and debug purpose. When you run your API, it will automatically gets hosted in inbuilt tomcat server.

      



## Usage

```sh
http://localhost:8088/
```
Use above base API Url to call service endpoints.

One can use swagger.spec.yml file present in **resource** folder to explore and try out API endpoints

## Author
* **Shekhar Rajepandhare**