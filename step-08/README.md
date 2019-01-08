# Step 08 - Securing the application

In this step we are using Spring Securty to easily protect the create, delete and edit endpoints from non-authorized usage.

## Set up Spring Security

We are going to add the `spring-boot-starter-security` as a Gradle dependency. `spring-boot-starter-security` installs Spring Security in the application. If Spring Security is on the classpath, then Spring Boot automatically secures all HTTP endpoints with "basic" authentication. But you can further customize the security settings. 

Modify the `build.gradle` file and refresh the Gradle project.

```groovy
dependencies {
	compile 'org.springframework.boot:spring-boot-starter-web'
	compile 'org.springframework.boot:spring-boot-starter-data-mongodb'   
	compile 'org.springframework.boot:spring-boot-starter-security'
	
	// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
	compile group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.9.8'

}
```

