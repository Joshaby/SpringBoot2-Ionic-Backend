# SpringBoot2-Ionic-Backend
Repositório de projetos do curso Spring Boot, Hibernate, REST, Ionic, JWT, S3, MySQL, MongoDB na Udemy por Nélio Alves

Backend feito em Java para promover implementação de um sistema de compra de produtos

## Estruturação do projeto

###Arquitetura

- Config: Contém classes de configuração do projeto, como tipo de persistência a ser usada
- Domain: Contém classes que representam objetos do bando de dados
- Dto: Contém objetos usados para fazer requisições
- Repositories: Contém interfaces que realizaram consultas no banco de dados
- Resource: API Rest do projeto junto com classes úteis e tratamento de execeção
- Services: Contém classes com regras de negócio junto com classes úteis e que acessam os repositories

### Properties

- application.properties: Arquivo base de proprieadades do projeto! Usado para setar o ambiente de execução
- application-dev.properties: Arquivo com propriedades do ambiente dev, que roda localmente e usa MySQL
- application-prod.properties: Arquivo com propriedades do ambiente prod que roda no heroku
- application-test.properties: Arquivo com propriedades do ambiente test, que roda localmente e usa hH2

##Endpoints disponíveis

###Clientes

- `/clientes` - GET: Obtem todos os clientes
- `/clientes` - POST: Cadastro de cliente
- `/clientes/{id}` - GET: Obtem um cliente por id
- `/clientes/{id}` - PUT: Atualização de cliente por id
- `/clientes/{id}` - DELETE: Deleção de cliente por id
- `/clientes/pages?page=0&linesPerPage=24&direction=ASC&orderBy=id` - GET: Obtem clientes em uma página

###Categorias

- `/categorias` - GET: Obtem todos as categorias
- `/categorias` - POST: Cadastro de categoria
- `/categorias/{id}` - GET: Obtem uma categoria por id
- `/categorias/{id}` - PUT: Atualização de categoria por id
- `/categorias/{id}` - DELETE: Deleção de categoria por id
- `/categorias/pages?page=0&linesPerPage=24&direction=ASC&orderBy=id` - GET: Obtem categorias em uma página

###Pedidos

- `/pedidos/{id}` - GET: Obtem todos um pedido por id
- `/pedidos/{id}` - POST: Cadastra um pedido por id

###Produtos

- `/produtos/{id}` - GET: Obtem um pedido
- `/produtos?nome=""&categorias=""&page=0&linesPerPage=24&direction=ASC&orderBy=nome` - GET: Obtem produtos em uma paǵina
