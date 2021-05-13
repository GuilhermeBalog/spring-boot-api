# Spring Boot API

Minha primeira API feita com Spring Boot.

## Anotações

Algumas notas que tomei no durante o desenvolvimento.

### Estrutura do projeto

- Sempre declarar o pacote nas classes. Ex:
    
    ```java
    package com.guilhermebalog;
  
    class Foo {
        //...  
    }   
    ```
  
- Deixar a classe `main` na base do projeto.
- Organização *package-by-feature*. Ex:

    ```text
    // Projeto PetClinic, implementação modelo do Spring
  
    // Entidades base
    org.springframework.samples.petclinic.model
  
    // Entidades, controllers e repositórios para Dono seu Pet
    org.springframework.samples.petclinic.owner;
  
    // Classes de sistema, como cache
    org.springframework.samples.petclinic.system;
  
    // Entidades, controllers e repositórios para veterinário 
    org.springframework.samples.petclinic.vet;
  
    // Entidade e repositório para Visita 
    org.springframework.samples.petclinic.visit;
    ```
  
### Actuators 

Usado principalmente para expor informações operacionais sobre a aplicação em execução através de endpoints HTTP.


### Arquivos SQL

Para popular o banco assim que a aplicação carrega, podemos definir um arquivo `src/main/resources/data.sql` com os `INSERT`. Ex:

```sql
/* src/main/resources/data.sql */
INSERT INTO book VALUES (3, 'Spring Boot Deployment', 'Guilherme Balog');
```

## Aulas

Fonte: [Baeldung](https://www.baeldung.com/spring-boot)

- [x] Spring Boot Tutorial – Bootstrap a Simple Application
- [x] A Comparison Between Spring and Spring Boot
- [x] Spring Boot Annotations
- [x] Intro to Spring Boot Starters
- [x] Recommended Package Structure of a Spring Boot Project
- [x] Spring Boot Actuator
- [x] Configure a Spring Boot Web Application
- [x] Quick Guide on Loading Initial Data with Spring Boot
- [x] Properties with Spring and Spring Boot (popular)
- [x] Guide to @ConfigurationProperties in Spring Boot
- [x] Automatic Property Expansion with Spring Boot
- [x] How to Define a Spring Boot Filter?
- [ ] How to Change the Default Port in Spring Boot
- [ ] Spring Boot Change Context Path
- [ ] Spring Boot: Customize Whitelabel Error Page
- [ ] Using Custom Banners in Spring Boot
- [ ] Testing in Spring Boot (popular)
- [ ] Exploring the Spring Boot TestRestTemplate
- [ ] Quick Guide to @RestClientTest in Spring Boot
- [ ] Logging in Spring Boot (popular)
- [ ] Creating a Custom Starter with Spring Boot
- [ ] Create a Custom Auto-Configuration with Spring Boot
- [ ] Display Auto-Configuration Report in Spring Boot
- [ ] How to Get All Spring-Managed Beans?
- [ ] Spring Boot Security Auto-Configuration
- [ ] Quick Guide on Loading Initial Data with Spring Boot
- [ ] Spring Boot with Multiple SQL Import Files
- [ ] Show Hibernate/JPA SQL Statements from Spring Boot
- [ ] Spring Boot With H2 Database (popular)
