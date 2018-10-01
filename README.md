# I like it!!
http://localhost:8080/ilikeit/rest/swagger-ui.html - SWAGGER PAGE
1. You can create Database, using resources/db/schema.sql
2. Run Application, using ILikeItApplication.java

3. For testing go to: http://localhost:8080/ilikeit/rest/swagger-ui.html

![alt text](https://user-images.githubusercontent.com/28773654/46302054-caac3700-c5b0-11e8-9292-49ba667c39c8.png)

Go to login-controller

4.Sign-up using userName and Password, click
![alt text](https://user-images.githubusercontent.com/28773654/46302052-ca13a080-c5b0-11e8-84d1-21ebc6b0c1db.png)
5.Get token, using our username and password
**curl -i -H "Content-Type: application/json" -X POST -d '{
    "username": "admin",
    "password": "admin1234"
}' http://localhost:8080/ilikeit/rest/login**

6.Copy token from response and go to the Swagger.

Token example:
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzOTI3MzMwNH0.HmQBOL6rzMyt929C9fDX6JRBa2RY2UVzZLpKMTJTekUrlKMiv_e1AeDCOCjnD6eS_XrDXfmmlLX6z7qJO-oZmQ

7.Click **Authorize** and insert token in value. After this click Authorize.
![alt text](https://user-images.githubusercontent.com/28773654/46302050-ca13a080-c5b0-11e8-9329-1901cddc0158.png)

8.Now you can use application.

featers:

**create user**, **search friends**, **add friends**, **chatting**
