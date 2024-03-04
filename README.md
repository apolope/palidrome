# Palindrome
Projeto Caça-palíndromo, atendendo aos requisitos solicitados como abaixo:
- Desenvolva uma API rest com dois endpoints.
  - Os dois endpoints solicitado com o path de contexto ```/test```
    ```bash
    curl --location 'http://localhost:8082/test' \
    --header 'Accept-Language: pt-BR' \
    --header 'Content-Type: application/json' \
    --data '{
    "matrix": [
    ["A", "O", "S", "S", "O"],
    ["Y", "R", "Z", "X", "L"],
    ["J", "S", "A", "P", "M"],
    ["J", "K", "P", "R", "Z"],
    ["Y", "L", "E", "R", "A"]
    ]
    }'
    ```
    > - header: [Accept-Language] internationalization language [Content-Type] content type
    > - data: [JSON] MatrixDTO.class
  - Search Palindromes GET
    ```bash
    curl --location 'http://localhost:8082/test?q=AR&matrixId=65e50ebfe34221200e7100bc' \
    --header 'Accept-Language: pt-BR'
    ```
    >   - header: [Accept-Language] internationalization language
    >   - query-params: [q] string to query filter
  - Foram desenvolvido os endpoints de CRUD para as matrizes com o path de contexto ```/matrix```
    - Save Matrix POST
    - Get All Matrix
    - Get Matrix By Id
    - Update Matrix
    - Delete Matrix
  - Foram desenvolvido os endpoint para o manuseio dos palindormos com o path de contexto ```/palindrome```
    - Save Palindrome
    - Get Palindromes
    - Get Palindromes By Matrix Id
- Persista as palavras encontradas em um banco de dados H2 ou MongoDB, sendo um registro para cada requisição.
  Envie os dados persistidos na área de retorno da requisição.
  - Foi utiizado o MongoDB com uma imagem embarcada em docker no docker-compose.yml
- Deve permitir a pesquisa da lista de palavras inserida na requisição POST

# Será avaliado:
- [x] Aplicação dos conceitos de Clean Code;
- [x] Arquitetura utilizada;
- [x] Configuração da conexão com o banco de dados;
- [x] Utilização de design patterns;
- [x] Documentação do microsserviço (Swagger/OpenAPI);
- [x] Testes unitários (Assertividade e percentual de cobertura);
- [x] Tratamento de erros;

# Para rodar o projeto
## Ambiente de DEV
- Abra o projeto e com o Java 17 e Maven 3 instalados
  > mvn quarkus:dev
- Em seguida você pode abrir o painel de desenvolvimento do quarkus
  ```http://localhost:8082```
## Ambiente de PROD
- Abra o projeto e com o Java 17 e Maven 3 instalados
  > mvn package
- Com o docker e docker compose instalados
  > docker compose up -d
# Para executar os testes e obter a cobertura
- Executar cobertura de testes com o Jacoco
> mvn clean verify
- Em seguida basta ir para a pasta raiz do projeto em ```target/site/jacoco/index.html```

# Routines
## Run
> mvn quarkus:dev
## Build
> mvn package
## Make Dokcer Image
> docker compose build
## Run with Docker Image
> docker compose up -d
## Tests
> mvn clean verify

# Dependencias
- MongoDB com Panache
- SmallRye Open API
- Junit5
- Config YAML
- Lombook
- RestEasy Reactive Jackson
- Mockito
- Jacoco

# API Docs
Foi criado um workspace publico para utilização do endpoint no postman
https://www.postman.com/teama3s/workspace/palindrome/overview
- Obtain Palindromes POST
  ```bash
  curl --location 'http://localhost:8082/test' \
  --header 'Accept-Language: pt-BR' \
  --header 'Content-Type: application/json' \
  --data '{
  "matrix": [
  ["A", "O", "S", "S", "O"],
  ["Y", "R", "Z", "X", "L"],
  ["J", "S", "A", "P", "M"],
  ["J", "K", "P", "R", "Z"],
  ["Y", "L", "E", "R", "A"]
  ]
  }'
  ```
    > - header: [Accept-Language] internationalization language [Content-Type] content type
    > - data: [JSON] MatrixDTO.class
- Search Palindromes GET
  ```bash
  curl --location 'http://localhost:8082/test?q=AR&matrixId=65e50ebfe34221200e7100bc' \
  --header 'Accept-Language: pt-BR'
  ```
    > - header: [Accept-Language] internationalization language
    > - query-params: [q] string to query filter
- Foram desenvolvido os endpoints de CRUD para as matrizes com o path de contexto ```/matrix```
  - Save Matrix POST
    ```python
      curl --location 'http://localhost:8082/matrix' \
      --header 'Content-Type: application/json' \
      --data '{
      "matrix": [
      ["A", "O", "S", "S", "O"],
      ["Y", "R", "Z", "X", "L"],
      ["J", "S", "A", "P", "M"],
      ["J", "K", "P", "R", "Z"],
      ["Y", "L", "E", "R", "A"]
      ]
      }'
      ```
      > - header: [Accept-Language] internationalization language
      > - data: [JSON] MatrixDTO.class
  - Get All Matrix
    ```bash
    curl --location 'http://localhost:8082/matrix' \
    --header 'Accept-Language: pt-BR'
    ```
      > - header: [Accept-Language] internationalization language
  - Get Matrix By Id
    ```bash
    curl --location 'http://localhost:8082/matrix/65e492aba0ef1545d9bf5931' \
    --header 'Accept-Language: pt-BR'
    ```
      > - header: [Accept-Language] internationalization language
  - Update Matrix
    ```bash
    curl --location --request PUT 'http://localhost:8082/matrix/65e50ebfe34221200e7100bc' \
    --header 'Accept-Language: pt-BR' \
    --header 'Content-Type: application/json' \
    --data '{
    "matrix": [
      ["A", "O", "S", "S", "O"],
      ["Y", "R", "Z", "X", "L"],
      ["J", "S", "A", "P", "M"],
      ["J", "K", "P", "R", "Z"],
      ["Y", "L", "E", "R", "A"]
      ]}'
    ```
      > - header: [Accept-Language] internationalization language
      > - data: [JSON] MatrixDTO.class
      > - path-params: [id] matrix id
  - Delete Matrix
    ```bash
    curl --location --request DELETE 'http://localhost:8082/matrix/65e50ebfe34221200e7100bc?=null' \
    --header 'Accept-Language: pt-BR'
    ```
      > - header: [Accept-Language] internationalization language
      > - path-params: [id] matrix id
- Foram desenvolvido os endpoint para o manuseio dos palindormos com o path de contexto ```/palindrome```
  - Save Palindrome
    ```bash
    curl --location 'http://localhost:8082/palindrome' \
    --header 'Accept-Language: pt-BR' \
    --header 'Content-Type: application/json' \
    --data '{
    "palindrome": "ARARA"
    }'
    ```
      > - header: [Accept-Language] internationalization language [Content-Type] content type
      > - data: [JSON] PalindromeDTO.class
  - Get Palindromes
    ```bash
    curl --location 'http://localhost:8082/palindrome?q=ARA&matrix=65e50ebfe34221200e7100bc' \
    --header 'Accept-Language: pt-BR'
    ```
      > - header: [Accept-Language] internationalization language [Content-Type] content type
      > - query-params: [q] string to query filter [matrix] matrix id filter
  - Get Palindromes By Matrix Id
    ```bash
    curl --location 'http://10.0.0.67:8082/palindrome/65e50ebfe34221200e7100bc' \
    --header 'Accept-Language: pt-BR'
    ```
      > - header: [Accept-Language] internationalization language
      > - path-params: [id] palindrome id