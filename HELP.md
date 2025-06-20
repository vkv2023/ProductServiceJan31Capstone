# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.4/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.4/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.4/reference/web/servlet.html)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/3.4.4/specification/configuration-metadata/annotation-processor.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.4/reference/using/devtools.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

[//]: POST # (To Create a product using postman)
POST localhost:9000/products

{
"name": "Bulova",
"description": "Fossil is a great luxry Watch",
"price": 43000,
"category": "Watches",
"imageUrl": "http://example.com"
}

{
"name": "SAMSUNG",
"description": "SAMSUNG is good tv",
"price": 75000,
"category": "Television",
"imageUrl": "http://example.com"
}

[//]: GET # (To search a product or products )
GET localhost:9000/products/search

User QueryParam under Params 
key     value
name  =  tissot
category = Watches

[//]: GET # (To list all products)

GET localhost:9000/products/all

[//]: GET # (To list specific product with id)

GET localhost:9000/products/13

[//]: GET # (To DELETE specific product with id)

DELETE localhost:9000/products/delete/6

[//]: # (Chat AI - OPenAI - gpt-4.1 is added for testing purposes)
[//]: GET # (use request Param, message=?? )
localhost:9000/products/chat?message=tell me one more joke on programming


[//]: # (CHAT AI - OPan AI - GEt and save dexription for a new Product registration)
[//]: # (description will be added to the product by OpenAI)
[//]: # (POST)
POST http://localhost:9000/products/products-without-description

{
"name": "Curd",
"price": 200,
"imageUrl": "http://example.com",
"category": "Grocery"
}

[//]: # (To search a product or products using Pagination)
[//]: # (We have used searchController and searchService separately)
POST http://localhost:9000/products/searchQuery

{
"query": "tissot",
"pageNumber": 0,
"pageSize": 2,
"sortParam": "name"
}


[//]: # (To search a product or products using Pagination)
[//]: # (We have used searchController and searchService separately)
GET  http://localhost:9000/products/searchQuery
use Params to get the search values 

query = "tissot"
pageNumber 0
pageSize  2
sortParam name


#Rather using List<Product> either use List<ProductResponseDto> 
or List<searchResponseDto>, in order to avoid expose to Product Model.

