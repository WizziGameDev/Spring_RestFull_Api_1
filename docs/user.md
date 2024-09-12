# User API Spec

## Register User
Endpoint : POST /api/users

Request Body :

```json
{
  "username" : "adam",
  "password" : "12345",
  "name"     : "Ricky Adam Saputra"
}
```

Response Body (Success) :

```json
{
  "data" : "OK"
}
```

Response Body (Failed) :

```json
{
  "errors" : "Username already use or not blank"
}
```

## Login user

Endpoint : POST /api/auth/login

Request Body :

```json
{
  "username" : "adam",
  "password" : "12345"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "token" : "TOKEN",
    "expiredAt" : 231243214 //millisecond
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Username or Password Wrong"
}
```

## Get User

Endpoint : GET /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "username" : "adam",
    "name"     : "Ricky Adam Saputra"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Unauthorized"
}
```

## Update User

Endpoint : PATCH /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name" : "new name",
  "password" : "new password"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "name"     : "new name",
    "username" : "new password"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Unauthorized"
}
```

## Logout User

Endpoint : DELETE /api/auth/logout

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "Success"
}
```