[![Build Status](https://travis-ci.org/gleb-kosteiko/latepost-scheduler.svg?branch=master)](https://travis-ci.org/gleb-kosteiko/latepost-scheduler)
[![Dependabot Status](https://api.dependabot.com/badges/status?host=github&identifier=216210184)](https://dependabot.com)
[![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](./LICENSE)

# Late Post Scheduler

## Requirements:
- Java 8
- GraalVM
- Maven 3.6
- Docker

## How to build and to run:
```
mvn clean package quarkus:dev
```

## How to build native binary image:
```
mvn package -Pnative -Dnative-image.docker-build=true
```

## How to build and to run Docker image with native app:
- build app with Maven as described in steps above
- build Docker image
```
docker build -f src/main/docker/Dockerfile.native -t latepost-scheduler .
```
- run Docker container
```
docker run -i --rm -p 8080:8080 latepost-scheduler
```

## Swagger page
http://localhost:8080/swagger-ui/
