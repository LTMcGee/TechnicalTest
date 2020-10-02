# Technical Test Application
###### Author: Liam McGee
Description: API to call API at https://bpdts-test-app-v3.herokuapp.com/users and return users living in or within 60 miles of London.

Created using Java 11 

## Compilation 
Clone repository and import to Java IDE. (Intellij was used for development).
Execute a Maven build to pull down depencencies.
Then compile.

##Running Application
Application entry point is within org.lmcgee.technicaltest.TechnicalTestApplication, please run application from main method.

#API Test
Once the application is running, use curl command: curl -X GET "http://localhost:8080/getUsers" -H "accept: application/json" to retrieve the user list.
Alternatively use http://localhost:8080/getUsers within a GET request on Postman.