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
