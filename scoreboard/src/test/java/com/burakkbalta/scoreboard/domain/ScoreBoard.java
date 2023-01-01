package com.burakkbalta.scoreboard.domain;

import java.util.HashMap;
import java.util.Map;

import com.burakkbalta.scoreboard.interfaces.IScoreBoard;

public class ScoreBoard implements IScoreBoard {

    private Map<Integer, Match> liveMatches;

    public ScoreBoard() {
        liveMatches = new HashMap<>();
    }

    /**
     * Finish game that was already started.
     * Remove the relavent match from the liveMatches list.
     * @param matchId : Indicates the match to be removed
     * @return Returns true if the operation is successful
     */
    @Override
    public boolean finishGame(final int matchId) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Returns summary of games sorted by total score.
     * If there are multiple games with same total score
     *  they are returned in the order they were last added.
     * @return Returns summary of the games.
     */
    @Override
    public String getSummaryInOrderByTotalScore() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * It captures the home team and away team names when the game starts.
     * @param homeTeamName : home team name
     * @param awayTeamName : away team name
     * @return Match id that indicates the started match.
     */
    @Override
    public int startGame(final String homeTeamName, final String awayTeamName) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Updates a game score.
     * @param matchId : Indicates the game to be updated
     * @param homeTeamScore : new home team score
     * @param awayTeamScore : new away team score
     */
    @Override
    public void updateScore(final int matchId, final int homeTeamScore, final int awayTeamScore) {
        // TODO Auto-generated method stub
        
    }
    
}
