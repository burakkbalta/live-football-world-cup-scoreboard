package com.burakkbalta.scoreboard.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.burakkbalta.scoreboard.enums.ComparatorTypes;
import com.burakkbalta.scoreboard.interfaces.IScoreBoard;

public class ScoreBoard implements IScoreBoard {

    private final Map<Integer, Match> liveMatches;
    private final AtomicInteger matchIdCounter;

    public ScoreBoard() {
        liveMatches = new HashMap<>();
        matchIdCounter = new AtomicInteger(-1);
    }

    /**
     * Finish game that was already started.
     * Remove the relavent match from the liveMatches list.
     * @param matchId : Indicates the match to be removed
     * @return Returns true if the operation is successful
     */
    @Override
    public boolean finishGame(final int matchId) {
        Match match = liveMatches.remove(matchId);
        return match != null;
    }

    /**
     * Returns summary of games sorted by total score.
     * If there are multiple games with same total score
     *  they are returned in the order they were last added.
     * @return Returns summary of the games.
     */
    @Override
    public String getSummaryInOrderByTotalScore() {
        var matchesList = liveMatches.values().stream().collect(Collectors.toList());
        var sortedMatches = matchesList.stream()
            .sorted(MatchComparatorFactory.getMatchComparator(ComparatorTypes.InOrderByTotalScoreAndLastAddedTime,
                     false))
            .toList();
        return getSummary(sortedMatches);        
    }

    /**
     * It captures the home team and away team names when the game starts.
     * @param homeTeamName : home team name
     * @param awayTeamName : away team name
     * @return Match id that indicates the started match.
     */
    @Override
    public int startGame(final String homeTeamName, final String awayTeamName) {
        Match match = Match.createMatch(homeTeamName, awayTeamName);
        liveMatches.put(matchIdCounter.incrementAndGet(), match);
        return matchIdCounter.get();
    }

    /**
     * Updates a game score.
     * @param matchId : Indicates the game to be updated
     * @param homeTeamScore : new home team score
     * @param awayTeamScore : new away team score
     */
    @Override
    public boolean updateScore(final int matchId, final int homeTeamScore, final int awayTeamScore) {
        Match match = liveMatches.get(matchId);
        if(match != null) {
            match.getMatchScore().setHomeTeamScore(homeTeamScore);
            match.getMatchScore().setAwayTeamScore(awayTeamScore);
            return true;
        } else {
            return false;
        }
    }

    public Map<Integer, Match> getLiveMatches() {
        return liveMatches;
    }

    private String getSummary(final List<Match> sortedMatches) {
        StringBuilder summary = new StringBuilder();
        Match match = null;
        for(int i = 1; i<=sortedMatches.size(); i++) {
            match = sortedMatches.get(i-1);
            summary.append(i)
                .append(". ")
                .append(match.getHomeTeamName())
                .append(" ")
                .append(match.getMatchScore().getHomeTeamScore())
                .append(" - ")
                .append(match.getAwayTeamName())
                .append(" ")
                .append(match.getMatchScore().getAwayTeamScore())
                .append("\n");
        }

        return summary.toString();
    }
    
}
