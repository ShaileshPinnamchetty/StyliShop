# StyliShop
API automation assessment

***Framework architecture***
Project name: Stylishop


Decsription: Its an API automation testing project, to test the "Add to Bag" functionality of https://stylishop.com application. With all the information that was available to me, I've tried to make the entire framework as generic as possible, incorporating multiple components like following proper project structure for managing the test class and the other utility classes, implementing best practices like reusablity of the methods, implementing POJO classes, property files etc. The framework is also scalable, and it is very easy to add new tests as the project grows. 


Test scenarios covered:
Both the scenarios below are present in a single test class "CartFunctionalityTest.java"

1) Adding items to cart as a login user (Test name: addItem(), method=POST)
Step 1- Login details for this are fetched from applicaton.properties file. Using login details, the JWToken and customer id are fetched first from resource url /rest/customer/v4/login
Step 2- Pass the token, customer id and the product details as a POJO object to the resource url /rest/quote/auth/v5 to add the product to cart
Step 3- Validate that the returned status code is 200, and "Success!" message is received in the response. Additional validations could be added as required

2) Updating the quantity of the product in the cart (Test name: updateQuantity(), method=PUT)
Step 1- Pass the product id (sku), customerId, quantity to update and the quoteId (fetched from addItem() test) as a POJO object to the resource url /rest/quote/auth/v5
Step 2- Validate that the returned status code is 200, and "Success" message is received in the response. Additional validations could be added as required


Explanation of architecture:
- Project uses maven as a build tool. All dependencies are handled by pom.xml
- Theres a single test class "CartFunctionalityTest.java" under /Styli/src/test/java/com/stylishop/tests package
- Theres a utility class "Base.java" under /Styli/src/test/java/com/stylishop/tests package which has a few utility methods, which are used by the test class
- The request payload is being sent through POJO. All POJO classes are under /Styli/src/test/java/com/stylishop/tests/pojo package
- Few of the static properties like base and resource URIs, username and password are being fetched from the properties file "application.properties" under /Styli/src/test/resources package
- testng.xml is present in the root folder, to put together test suits for execution


How to execute tests:
- testng.xml is a good way to trigger the tests. Right click on testng.xml and select Run as --> TestNG suite

