
# 📄 API Documentation Patients

## ⚙️ Technology and Configuration

- **☕ Language & Framework**: Java (Spring Boot)
- **🗄️ Database**: PostgreSQL
- **⚡ Caching**: Redis is used to store cached responses from GET /api/patients to improve data retrieval speed.
- **✅ Validation**: Field-level validation is implemented to ensure data integrity during both creation and updates.
- **⚡ Concurrency**: Supports Virtual Threads to enhance performance and efficiency in handling concurrent requests.

## 📥 Endpoint

### 🔹 GET /api/patients

#### 📝 Description

Retrieve all patient data from the system.

#### ✅ Response

Status Code: 200 OK

```json
{
  "statusCode": 200,
  "data": [
    {
      "slug": "patient-001",
      "name": "John Doe",
      "email": "john.doe@example.com",
      "phoneNumber": 6281234567890,
      "address": "123 Main St, Springfield",
      "gender": "Male",
      "birthDate": 631152000000
    },
    {
      "slug": "patient-002",
      "name": "Jane Smith",
      "email": "jane.smith@example.com",
      "phoneNumber": 6289876543210,
      "address": "456 Elm St, Springfield",
      "gender": "Female",
      "birthDate": 631152360000
    },
    {
      "slug": "patient-003",
      "name": "Alice Johnson",
      "email": "alice.johnson@example.com",
      "phoneNumber": 6281112233445,
      "address": "789 Oak St, Springfield",
      "gender": "Female",
      "birthDate": 631152720000
    },
    {
      "slug": "patient-004",
      "name": "Bob Brown",
      "email": "bob.brown@example.com",
      "phoneNumber": 6284433221100,
      "address": "101 Pine St, Springfield",
      "gender": "Male",
      "birthDate": 631153080000
    },
    {
      "slug": "john-markocop-baru-5106",
      "name": "John Markocop Baru",
      "email": "johndoe@example.com",
      "phoneNumber": 1234567890,
      "address": "Suroboyo",
      "gender": "Male",
      "birthDate": 631152000000
    }
  ],
  "errors": null
}
```

---

### 🔹 GET /api/v1/patient/{slug}

#### 📝 Description

Retrieving patient data based on unique slug.

#### 📌 Example Endpoint

GET localhost:8085/api/v1/patient/patient-002

#### ✅ Success Response

Status Code: 200 OK

```json
{
  "statusCode": 200,
  "data": {
    "slug": "patient-002",
    "name": "Jane Smith",
    "email": "jane.smith@example.com",
    "phoneNumber": 6289876543210,
    "address": "456 Elm St, Springfield",
    "gender": "Female",
    "birthDate": 631152360000
  },
  "errors": null
}
```

#### ❌ Error Response

Status Code: 404 Not Found

```json
{
  "statusCode": 404,
  "data": null,
  "errors": [
    "Patient Not Found"
  ]
}
```

---

### 🔹 POST /api/v1/patient

#### 📝 Description

Adding new patient data into the system.

#### 📌 Example Endpoint

POST localhost:8085/api/v1/patient

```json
{
  "name": "Dimas Anjay Mabar",
  "email": "dimas@gmail.com",
  "phoneNumber": 1234567890,
  "address": "Ketintang, Surabaya",
  "gender": "Male",
  "birthDate": 631152000000
}
```

#### ✅ Success Response

Status Code: 200 OK

```json
{
  "statusCode": 200,
  "data": {
    "slug": "dimas-anjay-mabar-9015",
    "name": "Dimas Anjay Mabar",
    "email": "dimas@gmail.com",
    "phoneNumber": 1234567890,
    "address": "Ketintang, Surabaya",
    "gender": "Male",
    "birthDate": 631152000000
  },
  "errors": null
}
```

#### ❌ Validation Error Response

Status Code: 400 Bad Request

##### ❌ Example Request Not Valid:

```json
{
  "name": "",
  "email": "dimas",
  "phoneNumber": "",
  "address": "",
  "gender": "Male",
  "birthDate": 631152000000
}
```

##### ❌ Response Validation Error:

```json
{
  "statusCode": 400,
  "data": null,
  "errors": {
    "name": "Name can't be blank",
    "email": "Your email is not valid",
    "phoneNumber": "Phone Number can't be null",
    "address": "Address can't be blank"
  }
}
```

---

### 🔹 PUT /api/v1/patient/{slug}

#### 📝 Description

Update patient data based on slug.

#### 📌 Example Endpoint

PUT localhost:8085/api/v1/patient/dimas-anjay-mabar-9015

```json
{
  "name": "Dimas anjay baru",
  "email": "dimasbaru@gmail.com",
  "phoneNumber": 123456789010,
  "address": "Ketintang, Surabaya, Jawa",
  "gender": "Male",
  "birthDate": 631152000000
}
```

#### ✅ Success Response

Status Code: 200 OK

```json
{
  "statusCode": 200,
  "data": {
    "slug": "dimas-anjay-baru-5804",
    "name": "Dimas anjay baru",
    "email": "dimasbaru@gmail.com",
    "phoneNumber": 123456789010,
    "address": "Ketintang, Surabaya, Jawa",
    "gender": "Male",
    "birthDate": 631152000000
  },
  "errors": null
}
```

#### ❌ Error Response

Status Code: 404 Not Found

```json
{
  "statusCode": 404,
  "data": null,
  "errors": [
    "Patient Not Found"
  ]
}
```

---

### 🔹 DELETE /api/v1/patient/{slug}

#### 📝 Description

Deleting patient data based on slug.

#### 📌 Example Endpoint

DELETE localhost:8085/api/v1/patient/dimas-anjay-baru-5804

#### ✅ Success Response

Status Code: 200 OK

```json
{
  "statusCode": 200,
  "data": "Patient Deleted Successfully",
  "errors": null
}
```

#### ❌ Error Response

Status Code: 404 Not Found

```json
{
  "statusCode": 404,
  "data": null,
  "errors": [
    "Patient Not Found"
  ]
}
```
