package com.burakkbalta.scoreboard.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
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
    public Optional<Integer> startGame(final String homeTeamName, final String awayTeamName) {
        Optional<Integer> matchId = Optional.empty();
        Match match = Match.createMatch(homeTeamName, awayTeamName);
        Predicate<Match> matchPred = m -> (liveMatches.values().stream().anyMatch(mt -> {
            return (mt.getHomeTeamName().equals(m.getHomeTeamName()) 
                || mt.getHomeTeamName().equals(m.getAwayTeamName())
                || mt.getAwayTeamName().equals(m.getHomeTeamName())
                || mt.getAwayTeamName().equals(m.getAwayTeamName()));  
        }));
        if(!checkEntryIsValid(matchPred, match)) {
            liveMatches.put(matchIdCounter.incrementAndGet(), match);
            matchId = Optional.of(matchIdCounter.get());
        }
        
        return matchId;
    }
    /**
     * Updates a game score.
     * @param matchId : Indicates the game to be updated
     * @param homeTeamScore : new home team score
     * @param awayTeamScore : new away team score
     */
    @Override
    public boolean updateScore(final int matchId, final int homeTeamScore, final int awayTeamScore) {
        Predicate<Integer> homeTeamScorePred = homeScore -> (homeScore >= 0); 
        Predicate<Integer> awayTeamScorePred = awayScore -> (awayScore >= 0);
        Predicate<Integer> isMatchExistPred = mId -> (liveMatches.containsKey(matchId));
        boolean isEntryValid = checkEntryIsValid(homeTeamScorePred, homeTeamScore) 
            && checkEntryIsValid(awayTeamScorePred, awayTeamScore) 
            && checkEntryIsValid(isMatchExistPred, matchId);
        if(isEntryValid) {
            Match match = liveMatches.get(matchId);
            match.getMatchScore().setHomeTeamScore(homeTeamScore);
            match.getMatchScore().setAwayTeamScore(awayTeamScore);
            return true;
        } else {
            return false;
        }
    }

    public Map<Integer, Match> getLiveMatches() {
        final Map<Integer, Match> unmodifiableMap = Collections.unmodifiableMap(liveMatches);
        return unmodifiableMap;
    }

    private <T> boolean checkEntryIsValid(Predicate<T> predicate, T toBeTested) {
        return predicate.test(toBeTested);
    }
}
