# Sudoku

tools, frameworks, libraries used:
- spring boot, lightweight framework perfect for a task like this
- junit - indispensable for java unit tests
- gson - for parsing JSON
- Mockito
- Apache Http Client - used for integration tests
- mvn 3.2.5
- java 1.8

I divided the Sudoku project into two subprojects:
- Sudoku Rest
- Sudoku Integration Tests

HOW TO RUN?
go into directory /SudokuRest and run 
$mvn install

then run:
/SudokuRest/target/sudoku-0.0.1-SNAPSHOT.jar

then go into directory /SudokuIntegrationTests and run 
$mvn test
to see the result of integration tests

If you want to call the server in the browser use any http client like "DHC client" to call manually the endpoints of the 2 restservices provided. There are examples of calling them below:

post http://localhost:8080/sudoku/newGame

The above call will return a Json with the gameId of the newly created game

post http://localhost:8080/sudoku/putNumber/1?row=5&column=6&value=7

The above call will return a Json with information if a given game is finished, or 
an error message if a move is invalid.

Description of the SudokuRest project:

 I structured the code into 2 packages:
- service - responsible for calling logic and representing the output. For example, if we want to return xml instead of json only this package should be altered
- logic - algorithms for verifying sudoku. In future, the algorithms for creating random boards should be put here

If I had been instructed to create a persistance layer I would have created a third package called "repository" where every detail about the database would be encapsulated.

Once our application gets big (at some point in the future) we could divide it into three seperate projects like:
- Sudoku Service
- Sudoku Business Logic
- Sudoku Repository


What should be improved:
- automation for builds, testing, releasing. I did not play with it too much since I did not know anything about operating system of the company. Otherwise I would supply some shell/python scripts
- more testing should be added, especially for the service layer
- appropriate handling of huge number of games (the simple cache I implemented has its limits)
- security - as for now any user can use games created by other users, some security tokens should be added, or/and at least a more sophisticated way for creating gameId.