# Rick and Morty exercise

# Generate jar and execute the tests

* Unzip the file and change directory to rickandmorty-exercise

>cd \rickandmorty-exercise

* Using maven to compile, launch the tests and generate jar file:

>mvn clean install

* creates the jar in \target\rickandmorty-exercise-0.0.1.jar

# Launching the application

* Launch the application using maven. Logger file will be created in path \logs

>mvn clean install spring-boot:run

* Launch the application using java. Logger file will be created in path \target\logs

>java -jar \target\rickandmorty-exercise-0.0.1.jar

* Launch using docker. Use PowerShell for Windows.

Use the file Dockerfile

> docker build . -t rickandmorty-exercise

> docker run -p3456:3456 rickandmorty-exercise:latest

Check that container is running

> docker ps

# Execute application

* You can launch service "search-character-appearance" from swagger page http://localhost:3456/swagger-ui/ typing name
and clicking in "Execute" button

* You can call GET service calling the uri http://localhost:3456/search-character-appearance?name={name}
replacing {name} using the name to search




