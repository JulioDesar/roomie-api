# Roomie API

O Roomie disponibiliza uma API REST que permite o acesso aos usuários do sistema.

Recursos disponíveis para acesso via API:
* **Usuários**

## Métodos
Requisições para a API devem seguir os padrões:
| Método   | Descrição                                             |
| :------- | ----------------------------------------------------- |
| `GET`    | Retorna informações de um ou mais registros.          |
| `POST`   | Utilizado para criar um novo registro.                |
| `PUT`    | Atualiza dados de um registro ou altera sua situação. |
| `DELETE` | Remove um registro do sistema.                        |

---

## Respostas

| Código | Descrição                                                    |
| ------ | ------------------------------------------------------------ |
| `200`  | Requisição executada com sucesso (success).                  |
| `400`  | Erros de validação ou os campos informados não existem no sistema. |
| `401`  | Dados de acesso inválidos.                                   |
| `404`  | Registro pesquisado não encontrado (Not found).              |

---

#### Solicitando acesso com `authorization`

**Redirecione o usuário para a URL de autorização.**

Monte a URL de autorização utilizando e-mail e senha e o endereço **/auth**. Utilize o `code` fornecido para solicitar um `Token`. O `Token` possui validade de 1 hora.

---

## Solicitando tokens de acesso /auth

### Utilizando o código de acesso [post]

Envie um POST com seus dados para receber um `Token`. O `Token` é válido por 1 hora.

| Parâmetro | Descrição                  |
| --------- | -------------------------- |
| `email`   | Digite o e-mail do usuário |
| `senha`   | Digite a senha do usuário  |



+ Request (application/json)

    + Body

            {
                "email": "teste@teste.com",
                "senha": "123456"
            }

+ Response 200 (application/json)

    + Body

            {
                "token": "[access_token]",
                "tipo": "Bearer"
            }

+ Response 400 (application/json)

    + Body

            {
            }

---

# Usuários

Os usuários no sistema podem ser administradores ou aprovadores.



### Listar (List) [GET]

+ Request (application/json)

    + Headers

            Authorization: Bearer [access_token]

+ Response 200 (application/json)

          {
              "content": [
                  {
                      "id": 1,
                      "nome": "nome do usuario",
                      "ativo": true,
                      "funcao": ["ADMINISTRADOR", "APROVADOR"]
                  }
              ],
              "pageable": {
                  "sort": {
                      "empty": false,
                      "sorted": true,
                      "unsorted": false
                  },
                  "offset": 0,
                  "pageNumber": 0,
                  "pageSize": 10,
                  "unpaged": false,
                  "paged": true
              },
              "last": true,
              "totalElements": 2,
              "totalPages": 1,
              "size": 10,
              "number": 0,
              "sort": {
                  "empty": false,
                  "sorted": true,
                  "unsorted": false
              },
              "first": true,
              "numberOfElements": 2,
              "empty": false
          }

+ Response 403 (application/json)

          {
          }





## Criar (Create) [POST]

+ Attributes (object)

    + nome: nome do usuário (string, required)
    + cpf(string, required) - limite de 11caracteres
    + telefone(string, required)
    + nascimento (LocalDate, required)
    + funcao (ENUM, required) - Tipo
        + ADMINISTRADOR
        + APROVADOR
    + email(string, required)
    + senha(string, required)



+ Request (application/json)

    + Headers

            Authorization: Bearer [access_token]

+ Body

            {
              "nome": "Kaya Labadie",
              "cpf": 83294489654,
              "telefone": "1189547523",
              "nascimento": "2001-04-29",
              "funcao": [
              	"ADMINISTRADOR",
              	"APROVADOR"
              ],
              "email": "teste@gmail.com",
              "senha": "123456"
            }

+ Response 200 (application/json)

    + Body

            {
                "id": 6,
                "nome": "julio",
                "ativo": true,
                "funcao": "ADMINISTRADOR"
            }

---

### Editar (Update) [PUT]

+ Request (application/json)

    + Headers

            Authorization: Bearer [access_token]

    + Body

            {
            	"telefone": "",
            	"ativo": true,
            	"funcao": [
              		"ADMINISTRADOR",
              		"APROVADOR"
              	]
            }



+ Response 200 (application/json)

    + Body

            {
                "id": 2,
                "nome": "nome usuario",
                "ativo": false,
                "funcao": "ADMINISTRADOR"
            }