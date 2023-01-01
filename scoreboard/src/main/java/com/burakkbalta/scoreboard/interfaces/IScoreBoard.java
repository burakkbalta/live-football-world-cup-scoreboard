package com.burakkbalta.scoreboard.interfaces;

public interface IScoreBoard {

    // Returns Match Id that refers the started match
    int startGame(final String homeTeamName, final String awayTeamName);
    boolean finishGame(final int matchId);
    boolean updateScore(final int matchId, final int homeTeamScore, final int awayTeamScore);
    String getSummaryInOrderByTotalScore();
}
