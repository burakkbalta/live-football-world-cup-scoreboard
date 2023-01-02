Welcome to the Live Football World Cup Score Board!

This is a simple application that allows you to track the scores of live football matches in a World Cup.
This application was developed using the Test-Driven Development (TDD) approach. 
The application has the following features:

  - Start a game: When a game starts, it captures the home team and away team names. The initial score is set to 0-0.
  - Finish a game: It removes a match from the scoreboard.
  - Update score: It updates the score of a game with the given home team score and away team score.
  - Get summary: It returns a summary of the games sorted by total score in descending order. 
    If there are multiple games with the same total score, they are returned in the order they were last added to the system.
    
Requirements
  - Java 17 or later
  - Maven

Installation
  1. Clone the repository:
    git clone https://github.com/burakkbalta/live-football-world-cup-scoreboard.git
  2. Import the project into your favorite Java IDE.
  3. Build and run the project.

Usage
  The `ScoreBoard` class provides the following methods:

  `int startGame(homeTeam, awayTeam)`: starts a new game with the given home team and away team. The initial score is 0-0.
    It returns the corresponding match id to be used when updating match scores or finishing the game.

  `boolean finishGame(matchId)`: finishes the game with the given match id. The game is removed from the scoreboard.
    If the operation was performed successfully, it returns true otherwise false.

  `boolean updateScore(matchId, homeTeamScore, awayTeamScore)`: updates the score of the game with the given match id, the given home score and away score.

  `String getSummaryInOrderByTotalScore()`: returns a string with a summary of live matches sorted by total score.
    If there are multiple games with same total score, they are returned in the order they were last added.

  - ScoreBoard scoreboard = new ScoreBoard();

  - start a new game 
  -- matchId = scoreboard.startGame("Mexico", "Canada");

  - update the score of the game 
    - scoreboard.updateScore(matchId, 0, 5);

  - start another game 
    - anotherMatchId = scoreboard.startGame("Spain", "Brazil");

  - finish the game 
    - scoreboard.finishGame(anotherMatchId);

  - get summary for live matches sorted by total score 
    - scoreboard.getSummaryInOrderByTotalScore();

Notes 
      - ExtendenScoreBoard class has been implemented. It provides more functionality regarding getting summary. 
      - Several Comparator classes have been implemented to increase decoupling relationship between ScoreBoard and Comparator. 
      - The topics such as ErrorHandling, Serialization, Thread-Safety are not taken into consideration while implementing this application. 
      - Code coverage report has been added. According to this report, most of the implementation codes have been covered and verified. 


   