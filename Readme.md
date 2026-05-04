# CP02 - API REST com Spring Boot

API REST desenvolvida com Spring Boot para o Checkpoint 01 da FIAP.  
O projeto contém dois domínios: **Finanças** (títulos financeiros) e **Futebol** (edições da Copa).

---

## Tecnologias utilizadas

- Java 17
- Spring Boot 4.0.3
- Spring Data JPA
- MySQL 9.6
- Lombok
- SpringDoc OpenAPI (Swagger UI)
- Docker

---

## Pré-requisitos

- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/)

---

## 1. Subindo o banco de dados com Docker

Antes de rodar a aplicação, suba o MySQL via Docker com o comando abaixo no terminal (Git Bash ou PowerShell):

```bash
docker run -d \
  --name mysql \
  --rm \
  -e MYSQL_ROOT_PASSWORD=root_pwd \
  -e MYSQL_USER=new_user \
  -e MYSQL_PASSWORD=my_pwd \
  -p 3306:3306 \
  mysql
```

Verifique se o container está rodando:

```bash
docker ps
```

Você deve ver o container `mysql` listado com a porta `3306` mapeada.

> **Atenção:** a flag `--rm` faz o container ser removido automaticamente ao ser parado. Toda vez que reiniciar o computador ou o Docker, rode o comando acima novamente antes de iniciar a aplicação.

---

## 2. Configuração do banco

O arquivo `src/main/resources/application.properties` já está configurado para conectar ao MySQL local:

```properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/api?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root_pwd

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

O banco `api` é criado automaticamente na primeira execução.

---

## 3. Rodando a aplicação

Com o Docker rodando, execute na raiz do projeto:

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: **http://localhost:8080**

---

## 4. Documentação interativa (Swagger UI)

Acesse a documentação e teste os endpoints diretamente pelo navegador:

```
http://localhost:8080
```

---

## 5. Endpoints disponíveis

### Finanças — `/financas`

| Método | Rota             | Descrição               |
| ------ | ---------------- | ----------------------- |
| GET    | `/financas`      | Lista todas as finanças |
| GET    | `/financas/{id}` | Busca finança por ID    |
| POST   | `/financas`      | Cria uma nova finança   |
| PUT    | `/financas/{id}` | Atualiza uma finança    |
| DELETE | `/financas/{id}` | Remove uma finança      |

#### Exemplo de body (POST/PUT):

```json
{
  "id": 1,
  "emissor": "Tesouro Nacional",
  "taxa": 12.5,
  "risco": "baixo",
  "vencimento": "2030-01-01",
  "quantidade": 10
}
```

---

### Copa do Mundo — `/copa`

| Método | Rota         | Descrição              |
| ------ | ------------ | ---------------------- |
| GET    | `/copa`      | Lista todas as edições |
| GET    | `/copa/{id}` | Busca edição por ID    |
| POST   | `/copa`      | Cria uma nova edição   |
| PUT    | `/copa/{id}` | Atualiza uma edição    |
| DELETE | `/copa/{id}` | Remove uma edição      |

#### Exemplo de body (POST/PUT):

```json
{
  "id": 1,
  "ano": 2022,
  "capeao": "Argentina",
  "vice": "França",
  "sede": "Qatar",
  "melhorJogador": "Lionel Messi"
}
```

> **Atenção:** o campo `id` deve ser enviado manualmente no body, pois não é gerado automaticamente.

---

## 6. Estrutura do projeto

```
src/main/java/br/com/fiap/cp01_api01/
├── Application.java
├── controller/
│   ├── FinancasController.java
│   └── FutebolController.java
├── model/
│   ├── Financa.java
│   └── Futebol.java
└── repository/
    ├── FinancaRepository.java
    └── FutebolRepository.java
```
