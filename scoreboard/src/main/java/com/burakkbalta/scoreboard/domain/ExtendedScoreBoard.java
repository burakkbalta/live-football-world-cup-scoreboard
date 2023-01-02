package com.burakkbalta.scoreboard.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.burakkbalta.scoreboard.enums.ComparatorTypes;
import com.burakkbalta.scoreboard.interfaces.IExtendedScoreBoard;

public class ExtendedScoreBoard extends ScoreBoard implements IExtendedScoreBoard {

    private final Map<Integer, Match> finishedMatches;

    public ExtendedScoreBoard() {
        super();
        finishedMatches = new HashMap<>();
    }

    public Map<Integer, Match> getFinishedMatches() {
        return finishedMatches;
    }

    @Override
    public boolean finishGame(int matchId) {
        var match = super.getLiveMatches().get(matchId);
        if(match != null) {
            finishedMatches.put(matchId, match);    
        }
        return super.finishGame(matchId);
    }

    @Override
    public String getSummaryInOrderByStartTime(boolean isAscending) {
        var matchesList = super.getLiveMatches().values().stream().collect(Collectors.toList());
        var sortedMatches = matchesList.stream()
            .sorted(MatchComparatorFactory.getMatchComparator(ComparatorTypes.InOrderByStartTime,
                    isAscending))
            .toList();
        return getSummary(sortedMatches); 
    }

    @Override
    public String getSummaryInOrderByTotalScore(boolean isAscending) {
        var matchesList = super.getLiveMatches().values().stream().collect(Collectors.toList());
        var sortedMatches = matchesList.stream()
            .sorted(MatchComparatorFactory.getMatchComparator(ComparatorTypes.InOrderByTotalScore,
                    isAscending))
            .toList();
        return getSummary(sortedMatches); 
    }

    @Override
    public String getSummaryForAllMatchesInOrderByTotalScore(boolean isAscending) {
        var matchesList = super.getLiveMatches().values().stream().collect(Collectors.toList());
        matchesList.addAll(finishedMatches.values().stream().collect(Collectors.toList()));
        var sortedMatches = matchesList.stream()
            .sorted(MatchComparatorFactory.getMatchComparator(ComparatorTypes.InOrderByTotalScore,
                    isAscending))
            .toList();
        return getSummary(sortedMatches); 
    }

    @Override
    public String getSummaryForFinishedMatchesInOrderByTotalScore(boolean isAscending) {
        var matchesList = finishedMatches.values().stream().collect(Collectors.toList());
        var sortedMatches = matchesList.stream()
            .sorted(MatchComparatorFactory.getMatchComparator(ComparatorTypes.InOrderByTotalScore,
                    isAscending))
            .toList();
        return getSummary(sortedMatches); 
    }

    


    
}
