# Stock Quote Manager

## Usage

```
docker container run -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bootdb -p 3306:3306 -p 33060:33060 -d mysql:8

docker container run -p 8080:8080 -d lucasvilela/stock-manager

mvn clean install -DskipTests

Run Spring Boot Application

```

## Comments

Caching registered stock quotes was not implemented.

Implementation of unit tests and generation of the docker image were not implemented.

Swagger documentation was not implemented.