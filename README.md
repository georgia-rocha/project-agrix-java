# Projeto Agrix 🚀

Projeto para uma hipotetica empresa especializada em tecnologias para melhorar a eficiência no cultivo de plantações. Visando reduzir o desperdício de recursos, fazendo um uso mais responsável da terra disponível para plantio.

## Sobre o Projeto

O projeto Agrix é iniciado do zero ao deploy. É um sistema que permite a gestão e o monitoramento das fazendas participantes. O projeto foi desenvolvido em fases, com o objetivo de melhorar a eficiência agrícola e promover a sustentabilidade, a linguagem do projeto é Java e o ambiente de desenvolvimento integrado(IDE) foi o IntelliJ IDEA.

### Considerações Pessoais

Este projeto foi a minha conclusão do curso na Trybe, no módulo de eletriva de Java. Durante este módulo, adquiri os conhecimentos necessários para concluir o projeto com 100% de aproveitamento. No início, enfrentei algumas dificuldades, mas posteriormente percebi que havia algumas semelhanças com TypeScript, o que me permitiu compreender melhor e me adaptar. Inicialmente, tentei programar no Eclipse, mas não consegui me familiarizar com ele. Por orientação de um mentor da Trybe, optei pelo IntelliJ, o que teve um impacto significativo no meu desempenho, já que descobri que é uma ferramenta bastante prática, especialmente para rodar os testes.

## Funcionalidades

- **Fase A:** Nesta fase inicial, foram desenvolvidas as funcionalidades básicas da aplicação.

- **Fase B:** Após o sucesso da Fase A, a aplicação foi expandida e foram adicionadas funcionalidades extras.

- **Fase C:** Fase C do projeto Agrix! Nesta fase, segurança do sistema, garantindo que os dados dos usuários e das fazendas estejam protegidos.

## Instruções de Uso

<summary><strong>Tecnologias Utilizadas 📖 </strong></summary><br />

- Nesse projeto foi utilizada a linguagem de programação [Java](https://docs.oracle.com/en/java/)
- [IntelliJ](https://www.jetbrains.com/idea/download/)
- [Maven](https://maven.apache.org/)
- [Checkstyle](https://checkstyle.sourceforge.io/)
- [MySQL](https://dev.mysql.com/doc/)
  
<summary><strong>Para clonar e testar 👩‍🎓 </strong></summary><br />

  obs: Para instalar as depedências e rodar o projeto é necessário ter instalado na sua máquina o Java e o Maven!

1. Clone o repositório
* `git clone git@github.com:georgia-rocha/project-agrix-java.git`
  
2. Entre na pasta do repositório que você acabou de clonar:
 * `cd project-agrix-java`
   
3. Instale as dependências:
* `mvn install -DskipTests`

4. Testar a Aplicação:
   
- todos os testes
  * `mvn test`

- testes por classe
  * `mvn test -Dtest="TestClassName"`

<details>
<summary><strong>🎛 Checkstyle</strong></summary>

Para garantir a qualidade do código, utilizei nesse projeto o `Checkstyle`. Assim, o código
está alinhado às boas práticas de desenvolvimento, sendo mais legível e de fácil manutenção!
Para poder rodar o `Checkstyle`, certifique-se de ter executado o comando `mvn install` dentro do
repositório.

Para rodá-los localmente no repositório, execute os comandos abaixo:

```bash
mvn checkstyle:check
```

Se a análise do `Checkstyle` encontrar problemas no código, tais problemas serão mostrados no
seu terminal. Se não houver problema no código, nada será impresso no seu terminal.

</details>

<details>
<summary><strong>Relações das Tabelas 📑 </strong></summary>
  
  ![Modelo de tabelas](agrix-tabelas-fase-a-1.png)
  
Temos as seguintes tabelas:
- `farm`: representa uma fazenda
- `crop`: representa uma plantação, e está em relacionamento `n:1` ("muitos para um") com a tabela `farm`
  - Esta tabela recebeu alguns campos a mais, que guardam datas, e que precisarão ser considerados durante o desenvolvimento da Fase B.
- `fertilizer`: esta nova tabela representa um fertilizante, e está em um relacionamento `n:n` ("muitos para muitos") com a tabela `crop`. Esse relacionamento é realizado através da tabela `crop_fertilizer`.

</details>

## Requisitos

## Fase A

### 1. Criei uma API para controle de fazendas com a rota POST /farms

<details>
  <summary>Criei a aplicação com uma API para gerenciamento de fazendas inclue a rota POST</summary><br />

Neste requisito, criei a base para gerenciamento de fazendas da API, utilizando
Spring, Spring Boot, Spring Web e Spring Data.

Criei a primeira rota:

- `/farms` (`POST`)
    - Receber via corpo do POST os dados de uma fazenda 
    - Salvar uma nova fazenda a partir dos dados recebidos
    - Em caso de sucesso:
        - Retorna o status HTTP 201 (CREATED)
        - Retorna os dados da fazenda criada. O `id` da fazenda deve estar incluso na resposta.
<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição:
```json
{
  "name": "Fazendinha",
  "size": 5
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Fazendinha",
  "size": 5
}
```
</details>
</details>

### 2. Criei a rota GET /farms

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

- Retornar uma lista de todas as fazendas. O `id` da fazenda deve estar
      incluso na resposta.
  
Exemplo de resposta:

```json
[
  {
    "id": 1,
    "name": "Fazendinha",
    "size": 5.0
  },
  {
    "id": 2,
    "name": "Fazenda do Júlio",
    "size": 2.5
  }
]
```

</details>

### 3. Criei a rota GET /farms/{id}

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

  - `/farms/{id}` (`GET`):
    - Recebe um `id` pelo caminho da rota e retorna a fazenda com esse `id`. O `id` da
      fazenda está incluso na resposta.
    - Caso não exista uma fazenda com esse `id`, a rota retorna o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

Exemplo de resposta para a rota `/farms/3` (supondo que exista uma fazenda com `id = 3`):

```json
{
  "id": 3,
  "name": "My Cabbages!",
  "size": 3.49
}
```

</details>

### 4. Criei a rota POST /farms/{farmId}/crops

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

- `/farms/{farmId}/crops` (`POST`)
    - Recebe o `id` da fazenda pelo caminho da rota (representado aqui por `farmId` apenas para diferenciar da plantação)
    - Recebe via corpo do POST os dados da plantação
    - Salva a nova plantação a partir dos dados recebidos, associada à fazenda com o ID
      recebido
    - Em caso de sucesso:
        - Retorna o status HTTP 201 (CREATED)
        - Retorna os dados da plantação criada. A resposta inclui o `id` da plantação e
          o `id` da fazenda, mas não inclui os dados da fazenda.
    - Caso não exista uma fazenda com o `id` passado, a rota retorna o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

Exemplo de requisição na rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
{
  "name": "Couve-flor",
  "plantedArea": 5.43
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "farmId": 1
}
```

Note que o `id` da resposta se refere à plantação, e que o da fazenda está em `farmId`.

</details>


### 5. Criei a rota GET /farms/{farmId}/crops

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

- `/farms/{farmId}/crops` (`GET`):
    - Recebe o `id` de uma fazenda pelo caminho
    - Retorna uma lista com todas as plantações associadas à fazenda
    - Caso não exista uma fazenda com esse `id`, a rota retorna o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.
      
Exemplo de resposta para a rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "farmId": 1
  }
]
```

</details>

### 6. Criei a rota GET /crops

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

- `/crops` (`GET`)
    - Retorna uma lista de todas as plantações cadastradas. A resposta deve incluir o `id` de
      cada plantação e o `id` da fazenda associada, mas não inclui os dados da fazenda.

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "farmId": 1
  },
  {
    "id": 3,
    "name": "Tomate",
    "plantedArea": 1.9,
    "farmId": 2
  }
]
```

</details>

### 7. Criei a rota GET /crops/{id}

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

- `/crops/{id}` (`GET`):
    - Recebe o `id` de uma plantação pelo caminho da rota
    - Caso exista a plantação com o `id` recebido, retorna os dados da plantação. A resposta
      inclui o `id` de cada plantação e o `id` da fazenda associada, mas não inclui os
      dados da fazenda.
    - Caso não exista uma plantação com o `id` passado, a rota retorna o status HTTP 404 com a
      mensagem `Plantação não encontrada!` no corpo da resposta.

Exemplo de resposta para a rota `/crops/3` (supondo que exista uma plantação com `id = 3`:

```json
{
  "id": 3,
  "name": "Tomate",
  "plantedArea": 1.9,
  "farmId": 2
}
```

</details>

### 8. Criei um Dockerfile para sua aplicação

## Fase B

### 1. Ajustei a rota POST /farms/{farmId}/crops para utilizar datas

<details>
  <summary>Ajustei a rota POST /farms/{farmId}/crops para utilizar campos com datas</summary><br />

- `/farms/{farmId}/crops` (`POST`)
    - Recebe o `id` da fazenda pelo caminho da rota (representado por `farmId` apenas para diferenciar da plantação)
    - Receber via corpo do POST os dados da plantação 
    - Salvar a nova plantação a partir dos dados recebidos, associada à fazenda com o ID
      recebido
    - em caso de sucesso, deve:
        - retorna o status HTTP 201 (CREATED)
        - retorna os dados da plantação criada. A resposta inclui o `id` da plantação e
          o `id` da fazenda, mas não inclui os dados da fazenda.
    - caso não exista uma fazenda com o `id` passado, a rota retorna o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

Inclui dois atributos novos (descritos no diagrama atualizado das tabelas):
- `plantedDate`, representando a data em que a plantação foi semeada
- `harvestDate`, representando a data em que a plantação foi ou está prevista para ser colhida

As datas são recebidas e retornadas no formato ISO (`YYYY-MM-DD`).

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
{
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "plantedDate": "2022-12-05",
  "harvestDate": "2023-06-08"
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "plantedDate": "2022-12-05",
  "harvestDate": "2023-06-08",
  "farmId": 1
}
```

</details>

</details>

### 2. Ajustei a rota GET /farms/{farmId}/crops para utilizar datas

<details>
  <summary>Ajustei a rota GET /farms/{farmId}/crops para utilizar campos com datas</summary><br />

Da mesma forma que no requisito 2, inclui os campos com datas na resposta deste requisito.

A definição original da rota é:
- `/farms/{farmId}/crops` (`GET`):
    - Receber o `id` de uma fazenda pelo caminho
    - Retornar uma lista com todas as plantações associadas à fazenda
    - Caso não exista uma fazenda com esse `id`, a rota retorna o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

Os campos novos incluídos são os mesmos do requisito anterior.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-12-05",
    "harvestDate": "2023-06-08",
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  }
]
```

</details>

</details>

### 3. Ajustei a rota GET /crops para utilizar datas

<details>
  <summary>Ajustei a rota GET /crops para utilizar campos com datas</summary><br />

A definição original da rota é:
- `/crops` (`GET`)
    - Retorna uma lista de todas as plantações cadastradas. A resposta inclui o `id` de
      cada plantação e o `id` da fazenda associada, mas não inclui os dados da fazenda.

Os campos novos incluídos são os mesmos do requisito anterior.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  },
  {
    "id": 3,
    "name": "Tomate",
    "plantedArea": 1.9,
    "plantedDate": "2023-05-22",
    "harvestDate": "2024-01-10",
    "farmId": 2
  }
]
```

</details>

</details>

### 4. Ajustei a rota GET /crops/{id} para utilizar datas

<details>
  <summary>Ajustei a rota GET /crops/{id} para utilizar campos com datas</summary><br />

A definição original da rota é:
- `/crops/{id}` (`GET`):
    - Recebe o `id` de uma plantação pelo caminho da rota
    - Caso exista a plantação com o `id` recebido, é retornado os dados da plantação. A resposta
      inclui o `id` de cada plantação e o `id` da fazenda associada, mas não inclui os
      dados da fazenda.
    - Caso não exista uma plantação com o `id` passado, a rota retorna o status HTTP 404 com a
      mensagem `Plantação não encontrada!` no corpo da resposta.

Os campos novos a serem incluídos são os mesmos do requisito anterior.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/3` (supondo que exista uma plantação com `id = 3`:

```json
{
  "id": 3,
  "name": "Tomate",
  "plantedArea": 1.9,
  "plantedDate": "2023-05-22",
  "harvestDate": "2024-01-10",
  "farmId": 2
}
```

</details>

</details>


### 5. Criei a rota GET /crops/search para busca de plantações

<details>
  <summary>Criei a rota GET /crops/search para busca de plantações a partir da data de colheita</summary><br />

A rota a ser criada é:
- `/crops/search` (`GET`)
  - Receber dois parâmetros por query string para busca:
    - `start`: data de início
    - `end`: data de fim
  - Retorna uma lista com as plantações nas quais o campo `harvestDate` está entre as data de início e de fim.
    - A comparação das datas está inclusa (ou seja, deve incluir datas que sejam iguais à de início ou à de fim)
  - A resposta inclui o `id` de cada plantação e o `id` da fazenda associada, mas não inclui os dados da fazenda.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/search?start=2023-01-07&end=2024-01-10`:

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  },
  {
    "id": 3,
    "name": "Tomate",
    "plantedArea": 1.9,
    "plantedDate": "2023-05-22",
    "harvestDate": "2024-01-10",
    "farmId": 2
  }
]
```

</details>

</details>


### 6. Criei a rota POST /fertilizers

<details>
  <summary>Criei a rota POST /fertilizers para criação de um novo fertilizante</summary><br />

A rota criada é:
- `/fertilizers` (`POST`)
    - Receber via corpo do POST os dados de um fertilizante
    - Salva um novo fertilizante a partir dos dados recebidos
    - Em caso de sucesso:
        - Retorna o status HTTP 201 (CREATED)
        - Retorna os dados do fertilizante criado, incluindo seu `id`

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição:

```json
{
  "name": "Compostagem",
  "brand": "Feita em casa",
  "composition": "Restos de alimentos"
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Compostagem",
  "brand": "Feita em casa",
  "composition": "Restos de alimentos"
}
```

</details>

</details>


### 7. Criei a rota GET /fertilizers

<details>
  <summary>Criei a rota GET /fertilizers para listar todos os fertilizantes cadastrados</summary><br />

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

```json
[
  {
    "id": 1,
    "name": "Compostagem",
    "brand": "Feita em casa",
    "composition": "Restos de alimentos"
  },
  {
    "id": 2,
    "name": "Húmus",
    "brand": "Feito pelas minhocas",
    "composition": "Muitos nutrientes"
  },
  {
    "id": 3,
    "name": "Adubo",
    "brand": "Feito pelas vaquinhas",
    "composition": "Esterco"
  }
]
```
</details>

</details>


### 8. Criei a rota GET /fertilizers/{id}

<details>
  <summary>Criei a rota GET /fertilizers/{id} para pegar as informações de um fertilizante</summary><br />

    - Recebe o `id` de um fertilizante pelo caminho da rota
    - Caso exista o fertilizante com o `id` recebido, deve retorna seus dados, incluindo seu `id`
    - Caso não exista um fertilizante com o `id` passado, a rota retorna o status HTTP 404 com a
      mensagem `Fertilizante não encontrado!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta da rota `/fertilizers/3` (supondo que exista um fertilizante com `id = 3`):

```json
{
  "id": 3,
  "name": "Adubo",
  "brand": "Feito pelas vaquinhas",
  "composition": "Esterco"
}
```

</details>

</details>


### 9. Criei a rota POST /crops/{cropId}/fertilizers/{fertilizerId}

<details>
  <summary>Criei a rota POST /crops/{cropId}/fertilizers/{fertilizerId} associar uma plantação com um fertilizante</summary><br />
  
    - Recebe tanto o `id` da plantação quanto o `id` do fertilizante pelo caminho da rota
    - O corpo da requisição é vazio
    - Faz a associação entre o fertilizante e a plantação
    - Em caso de sucesso, retorna o status HTTP 201 (CREATED) com a mensagem `Fertilizante e plantação associados com sucesso!` no corpo da resposta
    - Caso não exista uma plantação com o `id` recebido, a rota retorna o status HTTP 404 com a mensagem `Plantação não encontrada!` no corpo da resposta.
    - Caso não exista um fertilizante com o `id` recebido, a rota retorna o status HTTP 404 com a mensagem `Fertilizante não encontrado!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de resposta para a rota `/crops/1/fertilizers/2` (supondo que exista uma plantação com `id = 1` e um fertilizante com `id = 2`):

```text
Fertilizante e plantação associados com sucesso!
```

</details>

</details>


### 10. Criei a rota GET /crops/{cropId}/fertilizers

<details>
  <summary>Crie a rota GET /crops/{cropId}/fertilizers para listar os fertilizante associados a uma plantação</summary><br />

    - Recebe o `id` de uma plantação pelo caminho
    - Retorna uma lista com todas os fertilizantes associados à plantação
    - Caso não exista uma plantação com o `id` recebido, a rota retorna o status HTTP 404 com a mensagem `Plantação não encontrada!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/2/fertilizers` (supondo que exista uma plantação com `id = 2`):

```json
[
  {
    "id": 2,
    "name": "Húmus",
    "brand": "Feito pelas minhocas",
    "composition": "Muitos nutrientes"
  },
  {
    "id": 3,
    "name": "Adubo",
    "brand": "Feito pelas vaquinhas",
    "composition": "Esterco"
  }
]
```

</details>

</details>

## Fase C

### 1. Criei a rota POST /persons

<details>
  <summary>Criei a rota POST /persons para salvar novas pessoas no banco</summary><br />

A definição da rota é:
- `/persons` (`POST`)
    - Recebe no corpo da requisição:
      - `username`
      - `password`
      - `roles` (conforme definido no enum `Role`, disponibilizado com o código)
    - Cria a pessoa com os dados passados
    - Responde com os campos `id`, `username` e `role` (mas não `password`)

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota POST `/persons`:

```json
{
  "username": "zerocool",
  "password": "senhasecreta",
  "role": "ADMIN"
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "username": "zerocool",
  "role": "ADMIN"
}
```

</details>

</details>

### 2. Adicionei autenticação no projeto

<details>
  <summary>Adicione autenticação no projeto, incluindo uma rota para login que retorna um JWT</summary><br />

Configurei o Spring Security e implementei no projeto a autenticação por usuário e senha.

1. Garanti acesso público (ou seja, desprotegido) aos endpoints:
    - POST `/persons` 
    - POST `/auth/login` (foi criado no próximo requisito, para permitir login) 
2. Criei a rota POST `/auth/login`:
    - Recebe o `username` e `password` no corpo da requisição
    - Valida os dados passados utilizando as pessoas que foram criadas pela rota `/persons`
    - Caso os dados estejam incorretos, retorna status 403
    - Caso os dados estejam corretos, retorna um campo `token` contendo um JWT gerado

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota POST `/auth/login` (suppondo que os dados estejam corretos):

```json
{
  "username": "zerocool",
  "password": "senhasecreta"
}
```

Exemplo de resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZ3JpeCIsInN1YiI6Im1ycm9ib3QiLCJleHAiOjE2ODk5ODY2NTN9.lyha4rMcMhFd_ij-farGCXuJy-1Tun1IpJd5Ot6z_5w"
}
```

</details>

</details>

### 3. Limita acesso à rota GET /farms

<details>
  <summary>Limitar acesso à rota GET /farms para pessoa autenticada com role correto</summary><br />

Neste requisito limitei o acesso à rota GET `/farms` para que apenas uma pessoa autenticada com role `USER`, `MANAGER` ou `ADMIN` possa acessar.

Retorna status 403 caso a pessoa não tenha permissões corretas. Do contrário, a rota retorna a resposta usual.

</details>

### 5. Limitei acesso à rota GET /crops

<details>
  <summary>Limitei acesso à rota GET /crops para pessoa autenticada com role correto</summary><br />

Neste requisito você deve limitei o acesso à rota GET `/crops` para que apenas uma pessoa autenticada com role `MANAGER` ou `ADMIN` possa acessar.

Retorna status 403 caso a pessoa não tenha permissões corretas. Do contrário, a rota retorna a resposta usual.

</details>

### 6. Limitei acesso à rota GET /fertilizers

<details>
  <summary>Limitei acesso à rota GET /fertilizers para pessoa autenticada com role correto</summary><br />

Neste requisito limitei o acesso à rota GET `/fertilizers` para que apenas uma pessoa autenticada com role `ADMIN` possa acessar.

Retorna status 403 caso a pessoa não tenha permissões corretas. Do contrário, a rota retorna a resposta usual.

</details>

### 7. Escrevi testes com cobertura acima de 80% das linhas de todas as classes de Service





