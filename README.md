TechChallenge 

Fase 5 - Pós 2ADJT 

Aluno: Gabriel Fellone 

RM350771 

 
 

Documentação: 
 
https://miro.com/app/board/uXjVKw-COnE=/?share_link_id=800211219247 

 

 

Foi criado um microserviço (login) para gerenciamento de login e senha e com Spring Security foi gerado um token para autenticação. 

Abaixo os endpoints para realizar o teste junto com o repositorio. 
 

 

 

 

Login -  

https://github.com/gabrielfellone/login 

Para realizar o teste: 

Para criar o login: 

POST 

localhost:8080/usuario 

{ 

"nome": "Gabriel", 

"login": "gabriel", 

"senha": "123", 

"email": "gbfellone" 

} 

Realizar o login: 

POST 

localhost:8080/auth/login 

{ 

"username": "gabriel", 

"password": "123" 

} 

Criar o perfil ADM 

POST 

{ 

"perfil": { 

"id": 1, 

"descricao": "Administrador" 

}, 

"usuario": { 

"id": 2, 

"nome": "Gabriel", 

"login": "gabriel", 

"senha": "$2a$10$mAvGx9AObh5MadWVIJvXBeIZoJ7JHQaeCnLqf0cJaapaeEkOEcewK", 

"email": "gbfellone", 

"situacao": "P" 

} 

} 

Recuperar Usuario 

GET 

localhost:8080/perfil-usuario 

token bearer 

 

---------------------------- 

 

Um microserviço de carrinho, onde salvamos na base as informações do produto, descrição e usuário associado. 

Gestão de itens, onde simulamos o processo de fila para pagamento do pedido. 

OBS.: Tentei me aprofundar na autenticação via Feign mas não tive sucesso. 

Carrinho: 

https://github.com/gabrielfellone/carrinho 

Gestão de Pedidos: 

https://github.com/gabrielfellone/gestaopedido 

 

 

 

 

 

Criar o PostGres 

 

docker pull postgres 

docker run -d --name postgre-fiap -e POSTGRES_PASSWORD=102030 -p 5432:5432 postgres:latest 

 

 

Criar o Cassandra  

 

docker pull cassandra 

docker run -d --name cassandra-docker -p 9042:9042 cassandra 

 

Acessar o Cassandra 

docker exec -it cassandra-docker bash 

Cqlsh 

 

Criar o KeySpace 

create keyspace techchallengecinco with replication={'class':'SimpleStrategy', 'replication_factor':1}; 

 

Criar as tabelas 

use techchallengecinco; 

CREATE TABLE produtos ( id UUID, nome text, nometext, descricao text, PRIMARY KEY (id) ); 

CREATE TABLE pedido ( id UUID, idCliente UUID, idProduto bigint, status text, PRIMARY KEY (id) ); 

 

 

 

Criar e gerenciar o RabbitMq 

Filas, Exchanges e Docker: 

Comandos Docker: 

docker run -d --hostname local-rabbit --name rabbit-mq -p 15672:15672 -p 5672:5672 rabbitmq:3.6.9-management 

docker ps 

http://localhost:15672 

username: guest 

password: guest 

Queues: 

pedido-pagamento-queue 

Exchanges: 

pedido-pagamento-exchange 

Nas exchanges associar as filas 

Fila: pedido-pagamento-queue --> Routing key: pedido-pagamento-rout-key 
