# Ptah
![img.png](doc/assets/logo.png) | Ptah is an Egyptian creator god who conceived the world and brought it into being through the creative power of speech.<br/> This project is a Kotlin Spring Boot application that blablabla
:-------------------------:|:-------------------------:
[![license](https://img.shields.io/badge/license-MIT-green.svg?style=for-the-badge&)](./LICENSE)

![Postgres](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin)
![Vue](https://img.shields.io/badge/Vue%20js-35495E?style=for-the-badge&logo=vuedotjs)
![SpringBoot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Gradle](https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger)
## Overview
I create this project after my boss told me go f**k with Kotlin.
So I created the project by using Kotlin as backend and Vue as frontend(cuz it is easier for me than React and the others)
The purpose is for study and practice~
### Prerequisites

To run this project, you must have the following prerequisites:

For backend

```
- Java 17
- Gradle 8.3
- PostgresSQL 12.0 or newer
```

For frontend

```
- Node 18
- NPM
```

### Installation

Run backend/frontend individually:

1. Clone the repository to your local machine.
2. Configure the database connection in the `application.yaml` file.
3. Run the following command to build the project: `gradle clean build`.
4. Run the project using the following command: `java -jar build/libs/ptah-0.0.1-SNAPSHOT.jar`.

Run docker-compose
```
docker-compose up -d
```

Once the project is running, you can visit [http://localhost:8080/ptah/swagger-ui/index.html](http://localhost:8080/ptah/swagger-ui/index.html) to check all exposed APIs.

### Usage

The project is providing backend services(APIs). 


### Author

- Jarry Zhou - [https://jarryzhou.space](https://jarryzhou.space)

### License
This project is licensed under the MIT License - see the LICENSE file for more details.