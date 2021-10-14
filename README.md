"#testOffer" 

Anta DIEYE

14/10/2021

#Description
 testOffer is a mini project in Github format 
 
 A SpringBoot API that exposes two services:
 
✓ The one that allows to register a user

✓ One that displays the details of a registered user

✓ Source code (Github link ): https://github.com/antadieye/testOffer.git

✓ Request samples (Postman collection) : testOffer.postman_collection.json

 #Installation
Spring Tools Suite (STS), version: sts-4.12.0.RELEASE

Lombok

Git, Version Git-2.33.0.2-64-bit:

Postman Version 5.5.0 win32 10.0.19042 / x64 to be able to test with datasets.

#technologies and dependencies
✓ Java version: 8

✓ Packaging: Jar

✓ Dependencies

	• Spring Boot DevTools
	
	• Spring Data JPA
	
	• Spring Web
	
	• H2 Database
	
	• Lombok

#Project Architecture
In this project we have 8 packages:
1. com.app.testoffer.model

This class named UserEntity, in this class, all the fields have been declared with the preconditions defined by the customer.
2. com.app.testoffer.repository

This class  is named UserRepository, the repository extends the JpaRepository class which allows us to have all the CRUD methods.
3. com.app.testoffer.service

This class is named UserService which implements the UserServiceInterface, this is a SpringBoot API that exposes two services:

✓ The one that allows to register a user

✓ One that displays the details of a registered user
4. com.app.testoffer.mm

This class is named UserMM, it allows you to manage the mapping.

5. com.app.testoffer.controller

This class is named UserController, It uses the UserService class to create or display a user,

6. com.app.testoffer.aspect

This class is named LoggingAdvice, AOP,Aspect Oriented Programming, defines aspects that will perform logging tasks.

7. com.app.testoffer.exception

This class is named ResourceException, it is used to trace errors and display them on the console.

8. com.app.testoffer

The class is named JPAUnitUserTest, it is used to unit tests

#Project execution under Postman
A SpringBoot API that exposes two services:

✓ The one that allows to register a user,the link on postman is: localhost:8080/api/users,  (POST)

{
"name":"Anta DIEYE",

"birthDate":"24-08-1994",

"countryResidence":"France",

"phoneNumber":"+3 7 80 91 61 40",

"gender":"Feminine",

"email":"anta.dieye@atos.net"
}


✓ One that displays the details of a registered user, the link on postman is:localhost:8080/api/users/{id}, (GET)

#Test Integrated H2 database
We have a built-in database to make it easy for someone else to install and integrate.

http://localhost:8080/h2-console, this is the link that allows us to check the data stored in the database on the browser.