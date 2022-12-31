package com.burakkbalta.scoreboard.interfaces;

public interface IScoreBoard {
    
    // Returns Match Id that refers the started match
    int startGame(final String homeTeamName, final String awayTeamName);
    boolean finishGame(int macthId);
    void updateScore(int matchId, int homeTeamScore, int awayTeamScore);
    String getSummaryInOrderByTotalScore();
}
