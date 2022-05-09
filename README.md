# Microserviço Relatório Compras

Gera relatórios de compra diversos com base nos dados consumidos de uma API externa.

Java | Spring Boot | Spring Webflux | Spring Cloud | Eureka Server | Spring Cloud Gateway | Swagger

## Endpoints

- /compras - Retorna uma lista de compras ordenadas de forma crescente por valor:

<p align="center">
    <img align="center" alt="GET - compras ordem crescente" src="https://github.com/rensilver/image-repo-github/blob/main/GET%20-%20compras ordem crescente.JPG">
</p>

- /maior-compra/ano - Retorna a maior compra do ano informado com dados da compra:

<p align="center">
    <img align="center" alt="GET - maior-compra-ano" src="https://github.com/rensilver/image-repo-github/blob/main/GET%20-%20maior-compra-ano.JPG">
</p>

- /clientes-fieis - Retorna o Top 3 clientes mais fiéis, clientes que possuem mais compras recorrentes com maiores valores.

<p align="center">
    <img align="center" alt="GET - clientes-fieis" src="https://github.com/rensilver/image-repo-github/blob/main/GET%20-%20clientes-fieis.JPG">
</p>

- /recomendacao/cliente/tipo - Retorna uma recomendação de vinho baseado nos tipos de vinho que o cliente mais compra.

<p align="center">
    <img align="center" alt="GET - recomendacao-cliente-vinho" src="https://github.com/rensilver/image-repo-github/blob/main/GET%20-%20recomendacao-cliente-vinho.JPG">
</p>

## Utilização - passo a passo

1. Clonar o repositório ou baixar os três módulos para uma pasta única.
2. Importar essa pasta única numa IDE como Eclipse ou IntelliJ.
3. Executar primeiro o módulo ms-eureka-server
4. Executar o módulo ms-api-gateway
5. Executar o módulo ms-relatorio-compras
6. Para acessar os endpoints, enviar request com postman ou no browser para o seguinte endereço base: http://localhost:8080/ms-relatorio-compras/<endpoint>

## Eureka Server

Acessar via endereço http://localhost:8761

<p align="center">
    <img align="center" alt="eurek instances" src="https://github.com/rensilver/image-repo-github/blob/main/eureka%20instances.JPG">
</p>

## Swagger

Acessar via endereço http://localhost:8080/ms-relatorio-compras/swagger-ui.html
