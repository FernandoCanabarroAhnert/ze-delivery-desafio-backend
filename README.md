# Desafio Backend: API Localizadora de Parceiros 📌

![Java](https://img.shields.io/badge/java-FF5722.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-0B3D30?style=for-the-badge&logo=mongodb&logoColor=white)
![Mongo Express](https://img.shields.io/badge/Mongo%20Express-285C35?style=for-the-badge&logo=mongodb&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-0077B6?style=for-the-badge&logo=docker&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)

## O que é o desafio? 🤔

O desafio, encontrado no GitHub do Zé Delivery, é de uma API de Parceiros, em que é possível criar um parceiro, consultar um parceiro por Id e consultar um parceiro por suas coordenadas (longitude e latitude), que estejam dentro de sua respectiva área de cobertura.
Para realizar a consulta utilizando dados geográficos, eu utilizei o MongoDB, que é um banco de dados NoSQL, que premite a consulta através da funcionalidade GeoJson.

O desafio pode ser encontrado aqui: <https://github.com/ab-inbev-ze-company/ze-code-challenges/blob/master/backend_pt.md>

<p align="left" width="100%">
    <img width="25%" src="https://github.com/user-attachments/assets/1ec43b12-bc4d-4fda-ad8e-36de0f45affd"> 
</p>

### Serviço RESTful 🚀

* Desenvolvimento de um serviço RESTful para toda a aplicação.

## Tecnologias 💻
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [MongoDB](https://www.mongodb.com/)
- [Mongo Express](https://alphasec.io/mongo-express-mongodb-management-made-easy/)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Docker](https://www.docker.com/)
- [JUnit5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)
- [Jacoco](https://www.eclemma.org/jacoco/)
- [Bean Validation](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html)
- [HATEOAS](https://spring.io/projects/spring-hateoas)

## Práticas adotadas ✨

- SOLID, DRY, YAGNI, KISS
- API REST
- Injeção de Dependências
- Testes Automatizados
- Geração automática do Swagger com a OpenAPI 3
- Uso de Banco de Dados NoSQL

## Diferenciais 🔥

Alguns diferenciais que não foram solicitados no desafio:

* Utilização de Docker
* TDD - Test Driven Development
* Tratamento de exceções
* Validações com Constraints Customizados
* Testes Unitários e de Integração
* Cobertura de Testes com Jacoco
* Documentação Swagger
* Implementação de HATEOAS

## Como executar 🎉

1.Clonar repositório git:

```text
git clone https://github.com/FernandoCanabarroAhnert/ze-delivery-desafio-backend.git
```

2.Instalar dependências.

```text
mvn clean install
```

3.Executar a aplicação Spring Boot.

4.Testar endpoints através do Postman ou da url
<http://localhost:8080/swagger-ui/index.html#/>

### Usando Docker 🐳

- Clonar repositório git
- Construir o projeto:
```
./mvnw clean package
```
- Construir a imagem:
```
./mvnw spring-boot:build-image
```
- Executar o container:
```
docker run --name desafio-ze-delivery -p 8080:8080  -d desafio-ze-delivery:0.0.1-SNAPSHOT
```
## API Endpoints 📚

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/):
- Collection do Postman completa: [Postman-Collection](https://github.com/user-attachments/files/17171573/Ze-Delivery.postman_collection.json)

- Criar Parceiro
```
$ http POST http://localhost:8080/partners

{
  "tradingName": "Adega da Cerveja - Pinheiros",
  "ownerName": "Zé da Silva",
  "document": "1432132123891/0001",
  "coverageArea": [
      [[[30, 20], [45, 40], [10, 40], [30, 20]]], 
      [[[15, 5], [40, 10], [10, 20], [5, 10], [15, 5]]]
    ],
  "address": { 
    "longitude": -46.57421,
    "latitude": -21.785741
  }
}
```

- Consultar Parceiro por Id
```
$ http GET http://localhost:8080/partners/{id}

{
  "id": "66f7445d717ef24486dc080a",
  "tradingName": "Ze da Ambev",
  "ownerName": "Daniboy",
  "document": "54321515000112",
  "coverageArea": [
                [[[-46.70544,-23.50796],[-46.70694, -23.49875],
                    [-46.70178,-23.49507],[-46.68589,-23.49123],
                    [-46.67945,-23.48373],[-46.6742,-23.4812],
                    [-46.67236,-23.47663],[-46.67014,-23.47423],
                    [-46.67032,-23.46789],[-46.66818,-23.46852],
                    [-46.66677,-23.46572],[-46.66416,-23.46321],
                    [-46.6607,-23.46196],[-46.65652,-23.46497],
                    [-46.62918,-23.44757],[-46.61939,-23.43826],
                    [-46.60204,-23.45257],[-46.57077,-23.45316],
                    [-46.56734,-23.4386],[-46.55009,-23.43612],
                    [-46.53975,-23.44208],[-46.53634,-23.44078],
                    [-46.53003,-23.44837],[-46.51447,-23.4508],
                    [-46.50876,-23.45293],[-46.49288,-23.44785],
                    [-46.49024,-23.46125],[-46.49171,-23.47339],
                    [-46.47945,-23.47356],[-46.4799,-23.4857],
                    [-46.4755,-23.50559],[-46.47844,-23.50724],
                    [-46.48224,-23.50699],[-46.49292,-23.50792],
                    [-46.50142,-23.51197],[-46.49001,-23.52696],
                    [-46.49846,-23.53257],[-46.49285,-23.53602],
                    [-46.48824,-23.53585],[-46.48501,-23.53962],
                    [-46.48785,-23.54434],[-46.49842,-23.54276],
                    [-46.50188,-23.54732],[-46.53327,-23.57342],
                    [-46.55097,-23.57626],[-46.55356,-23.58192],
                    [-46.57199,-23.5831],[-46.59521,-23.58821],
                    [-46.59961,-23.58507],[-46.60184,-23.57622],
                    [-46.61436,-23.55918],[-46.62294,-23.55627],
                    [-46.62978,-23.54447],[-46.6253,-23.53361],
                    [-46.62931,-23.52566],[-46.64145,-23.5185],
                    [-46.67787,-23.51339],[-46.68286,-23.51004],
                    [-46.69466,-23.50742],[-46.70544,-23.50796]]]],
  "address": {
      "longitude":-46.563892, 
      "latitude": -23.509415
  }
}

```

- Consultar Parceiro por Coordenadas
```
$ http GET http://localhost:8080/partners/geo?longitude={longitude}&latitude={latitude}

{
  "id": "66f7445d717ef24486dc080a",
  "tradingName": "Ze da Ambev",
  "ownerName": "Daniboy",
  "document": "54321515000112",
  "coverageArea": [
                [[[-46.70544,-23.50796],[-46.70694, -23.49875],
                    [-46.70178,-23.49507],[-46.68589,-23.49123],
                    [-46.67945,-23.48373],[-46.6742,-23.4812],
                    [-46.67236,-23.47663],[-46.67014,-23.47423],
                    [-46.67032,-23.46789],[-46.66818,-23.46852],
                    [-46.66677,-23.46572],[-46.66416,-23.46321],
                    [-46.6607,-23.46196],[-46.65652,-23.46497],
                    [-46.62918,-23.44757],[-46.61939,-23.43826],
                    [-46.60204,-23.45257],[-46.57077,-23.45316],
                    [-46.56734,-23.4386],[-46.55009,-23.43612],
                    [-46.53975,-23.44208],[-46.53634,-23.44078],
                    [-46.53003,-23.44837],[-46.51447,-23.4508],
                    [-46.50876,-23.45293],[-46.49288,-23.44785],
                    [-46.49024,-23.46125],[-46.49171,-23.47339],
                    [-46.47945,-23.47356],[-46.4799,-23.4857],
                    [-46.4755,-23.50559],[-46.47844,-23.50724],
                    [-46.48224,-23.50699],[-46.49292,-23.50792],
                    [-46.50142,-23.51197],[-46.49001,-23.52696],
                    [-46.49846,-23.53257],[-46.49285,-23.53602],
                    [-46.48824,-23.53585],[-46.48501,-23.53962],
                    [-46.48785,-23.54434],[-46.49842,-23.54276],
                    [-46.50188,-23.54732],[-46.53327,-23.57342],
                    [-46.55097,-23.57626],[-46.55356,-23.58192],
                    [-46.57199,-23.5831],[-46.59521,-23.58821],
                    [-46.59961,-23.58507],[-46.60184,-23.57622],
                    [-46.61436,-23.55918],[-46.62294,-23.55627],
                    [-46.62978,-23.54447],[-46.6253,-23.53361],
                    [-46.62931,-23.52566],[-46.64145,-23.5185],
                    [-46.67787,-23.51339],[-46.68286,-23.51004],
                    [-46.69466,-23.50742],[-46.70544,-23.50796]]]],
  "address": {
      "longitude":-46.563892, 
      "latitude": -23.509415
  }
}
```
