This is an application built by Ayomide Joysbright Oyediran for WIZER

Git URL - https://github.com/Oyejoysbright/wizer-lib-service
SWAGGER URL - http://localhost:5000/library/v1/swagger-ui/

HOW TO START THIS APPLICATION
- Clone
- Configure the application.properties with appropriate values. You can change the value of spring.profiles.active to "dev" to be able to add and use application-dev.properties
- Run the main class - LibraryServiceApplication in org.wizer.library
- Access the swagger Documentation on http://localhost:5000/library/v1/swagger-ui/ (provided that you start on 5000)

MAIN LIBRARIES
* JDK 11
* MVN 3.8.7
* Springboot 2.5.7

OTHER MAJOR LIBRARIES
* Lombok- for Avoiding boilerplate codes
* Jackson (core and databind) for processing JSON
* Swagger-UI for Rest API documentation

PATTERNS
* Singleton pattern (Ensure classes are initiated once though I implemented a helper class to instantiate even though I can create a singleton class )

FOLDER STRUCTURE
* Files are grouped by nature


THINGS I WOULD LOVE TO IMPLEMENT/IMPROVE
* Proper documentation
* Write JUnit test cases
* Chunk my GIT commits
* And some other things to improve efficiency.
