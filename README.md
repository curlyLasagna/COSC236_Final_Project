# COSC236 Final Project
A simple text based game where you play as a guy lost in the woods and under the influence of psychedelics.  
Your main goal is to find your friends before your high runs out.  

This was my first exposure to Java build tools, [Gradle](https://gradle.org/) specifically.  
The main reason as to why I decided to use Gradle for this project was to use it for managing dependencies.  
I wanted to use Graphs but my knowledge with Graphs are very limited. Google's [Guava](https://github.com/google/guava) library provided the functionality that I needed.
## Download
`$ git clone https://github.com/curlyLasagna/COSC236_Final_Project.git`

## Build the project
#### Windows 
`$ gradlew.bat build`
#### *nix
`$ ./gradlew build`  
Classes should compile and an executable jar will be produced at app/build/

## Run the game
`$ java -jar app/build/libs/app.jar`
