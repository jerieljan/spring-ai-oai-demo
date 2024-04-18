# Spring AI OpenAI Demo

by [Jeriel Jan](https://github.com/jerieljan/)

---

A basic demonstration of using Spring Framework's AI packages.

Used for presentation purposes.

## Requirements

- A development environment with **Java 17 or higher**
- An OpenAI API key (see section on Usage)

## Usage

This repository requires an API key, stored separately in an `application-secrets.properties` 
file.

You can configure this by creating a new file with just this info:

```properties
spring.ai.openai.api-key=<YOUR-OPENAI-API-KEY>
```

Provide your API key on the space provided.

### Using Maven

Open a Terminal/Command Prompt: Navigate to the root directory of your Spring Boot project
(where the pom.xml file is located).

Execute the Spring Boot Maven plugin: Run the following command:  
```bash
./mvnw spring-boot:run
```

If you are on Windows, you might use 
```
mvnw.cmd spring-boot:run
```

instead.

## References

Spring AI Reference
- https://docs.spring.io/spring-ai/reference/1.0-SNAPSHOT/getting-started.html
- https://docs.spring.io/spring-ai/reference/1.0-SNAPSHOT/api/chat/openai-chat.html
- https://github.com/rd-1-2022/ai-openai-helloworld


