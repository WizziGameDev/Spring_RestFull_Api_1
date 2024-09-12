# Contact Api Spec

## Create Contact

Endpoint : 

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "firstName" : "Ricky Adam",
  "lastName"  : "Saputra",
  "email"     : "bro@mail.com",
  "contact"   : "000998890"
}
```

Response Body (Success):

```json
{
  "id" : "random-string-id",
  "firstName" : "Ricky Adam",
  "lastName"  : "Saputra",
  "email"     : "bro@mail.com",
  "contact"   : "000998890"
}
```

Response Body (Failed):

```json
{
  "errors" : "Email format invalid, ..."
}
```

## Update Contact

Endpoint : PUT /api/contacts/{idContact}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "firstName" : "Ricky Adams",
  "lastName"  : "Saputra",
  "email"     : "bro@mail.com",
  "contact"   : "000998890"
}
```

Response Body (Success):

```json
{
  "id" : "random-string-id",
  "firstName" : "Ricky Adams",
  "lastName"  : "Saputra",
  "email"     : "bro@mail.com",
  "contact"   : "000998890"
}
```

Response Body (Failed):

```json
{
  "errors" : "Email format invalid, ..."
}
```

## Get Contact

Endpoint : GET /api/contacts/{idContact}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success):

```json
{
  "id" : "random-string-id",
  "firstName" : "Ricky Adams",
  "lastName"  : "Saputra",
  "email"     : "bro@mail.com",
  "contact"   : "000998890"
}
```

Response Body (Failed, 404):

```json
{
  "errors" : "Contact is Not found"
}
```

## Search Contact

Endpoint : GET /api/contacts

Query Param :

- name : String, contact firstname or lastname, using like query, optional
- phone : String, contact phone, using like query, optional
- email : String, contact email, using like query, optional
- page : Integer, Start 0
- size : Integer, default 10

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success):

```json
{
  "data" : [
    {
      "id" : "random-string-id",
      "firstName" : "Ricky Adams",
      "lastName"  : "Saputra",
      "email"     : "bro@mail.com",
      "contact"   : "000998890"
    }
  ],
  "paging" : {
    "currentPage" : 0,
    "totalPage" : 10,
    "size" : 10
  }
}
```

Response Body (Failed):

```json
{
  "errors" : "Unauthorized"
}
```

## Delete Contact

Endpoint : DELETE /api/contacts/{idContact}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success):

```json
{
  "data" : "OK"
}
```

Response Body (Failed):

```json
{
  "error" : "Not found"
}

```