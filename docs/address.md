# Address Api Spec

## Create Address

Endpoint : POST /api/contact/{idContact}/addresses

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "street" : "Jalan ...",
  "city"    : "Kota ...",
  "province" : "prvinsi ...",
  "country" : "Indonesia",
  "postalCode" : "32123"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "random-string",
    "street" : "Jalan ...",
    "city"    : "Kota ...",
    "province" : "prvinsi ...",
    "country" : "Indonesia",
    "postalCode" : "32123"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Contact not found"
}
```

## Update Address

Endpoint : PUT /api/contact/{idContact}/addresses/{idAddresses}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "street" : "Jalan ...",
  "city"    : "Kota ...",
  "province" : "prvinsi ...",
  "country" : "Indonesia",
  "postalCode" : "32123"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "random-string",
    "street" : "Jalan ...",
    "city"    : "Kota ...",
    "province" : "prvinsi ...",
    "country" : "Indonesia",
    "postalCode" : "32123"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Address not found"
}
```

## Get Address

Endpoint : GET /api/contact/{idContact}/addresses/{idAddresses}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "id" : "random-string",
    "street" : "Jalan ...",
    "city"    : "Kota ...",
    "province" : "prvinsi ...",
    "country" : "Indonesia",
    "postalCode" : "32123"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Address not found"
}
```

## Remove Address

Endpoint : DELETE /api/contacts/{idContact}/addresses/idAddress

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "OK"
}
```

Response Body (Failed) :

```json
{
  "errors" : "Address not found"
}
```

## List Address

Endpoint : GET /api/contact/{idContact}/addresses

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : [
    {
      "id" : "random-string",
      "street" : "Jalan ...",
      "city"    : "Kota ...",
      "province" : "prvinsi ...",
      "country" : "Indonesia",
      "postalCode" : "32123"
    }
  ]
}
```

Response Body (Failed) :

```json
{
  "errors" : "Contact not found"
}
```