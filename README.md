# Schedule Message 
O projeto consiste em programar mensagens no banco de dados para aviso posterior ao destinatario.

## Frameworks & linguagem
* Desenvolvimento utilizando a linguagem Java v8
* Framework Spring Boot v2.5.6
* Apache Maven 4.0.0 
* Junit para testes unitários
* H2 para banco de dados em memória

### Requisitos de funcionamento

- Java SDK 1.8 ^
- Apache Maven 4.0.0 ^

### Startando o Projeto

* Clonar o repositório https://github.com/gustavohsantana/schedule-message.git
* IDE Utilizada Intellij Idea
* Executar o projeto localmente: mvn spring-boot:run

## Database Additional Information

O Banco de dados utilizado foi o H2 para execução em memória

* Para acessar o painel e visualizar os dados 
  - path: http://localhost:9000/h2-console
  - username: sa
  - password: 
* O arquivo contido no projeto schema.sql irá criar a tabela necessária para sua execução (src/main/resources/schema.sql)
