Rest Endpoints

User Crud APIs
- Authorization Header
  - Basic Auth
    - Username: superadmin@apica.com
    - Password: superadmin
- Register User
  - POST /api/v1/user
    - Body
      ```json
      {
      "username": "tes1t",
      "password": "pw1d",
      "email": "test1@email.com",
      "role": "USER"
      }
        ```
    - Response
      - 201 Created
        ```json
        {
        "username": "tes1t",
        "email": "test1@email.com",
        "role": "USER"
        }
        ```
      - 401 unauthorized
        ```json
        {
        "message": "Unauthorized"
        }
        ```
      - 400 Bad Request
        ```json
        {
        "message": "Email already exists"
        }
        ```
- Get User
- GET /api/user/email/{email}
  - Response
    - 200 OK
      ```json
      {
        "username": "tes1t",
        "email": "test1@email.com",
        "role": "USER"
        }
      ```
    - 401 unauthorized
       ```json
       {
       "message": "Unauthorized"
       }
       ```
    - 400 Bad Request
      ```json
      {
      "message": "user not found"
      }
      ```
- Update User
  - PUT /api/user/email/{email}
      - Body
        ```json
        {
        "username": "tes1t",
        "password": "pw1d"
        }
          ```
      - Response
          - 201 Created
            ```json
            {
            "username": "tes1t",
            "email": "test1@email.com",
            "role": "USER"
            }
            ```
          - 401 unauthorized
            ```json
            {
            "message": "Unauthorized"
            }
            ```
          - 400 Bad Request
            ```json
            {
            "message": "user not found"
            }
            ```
- Delete User
  - Delete /api/user/email/{email}
    - Response
        - 200 OK
          ```json
          {
            "username": "tes1t",
            "email": "test1@email.com",
            "role": "USER"
            }
          ```
        - 401 unauthorized
           ```json
           {
           "message": "Unauthorized"
           }
           ```
        - 400 Bad Request
          ```json
          {
          "message": "user not found"
          }
        ```
- User Audit APIs       
    - Get User Audit
      - GET /api/user-audit?page=1&size=10
        - Response
          - 200 OK
            ```json
            {
  "content": [
  {
  "id": 1,
  "email": "test1@email.com",
  "username": "tes1t",
  "role": "USER",
  "createdDate": "2024-03-22T13:33:30.494+00:00",
  "action": "User fetched"
  },
  {
  "id": 2,
  "email": "test1@email.com",
  "username": "tes1t",
  "role": "USER",
  "createdDate": "2024-03-22T13:35:36.812+00:00",
  "action": "User fetched"
  },
  {
  "id": 3,
  "email": "test1@email.com",
  "username": "tes1t",
  "role": "USER",
  "createdDate": "2024-03-22T13:35:50.685+00:00",
  "action": "User deleted"
  }
  ],
  "pageable": {
  "pageNumber": 0,
  "pageSize": 10,
  "sort": {
  "sorted": false,
  "empty": true,
  "unsorted": true
  },
  "offset": 0,
  "paged": true,
  "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 3,
  "last": true,
  "size": 10,
  "number": 0,
  "sort": {
  "sorted": false,
  "empty": true,
  "unsorted": true
  },
  "numberOfElements": 3,
  "first": true,
  "empty": false
  }
            ```

