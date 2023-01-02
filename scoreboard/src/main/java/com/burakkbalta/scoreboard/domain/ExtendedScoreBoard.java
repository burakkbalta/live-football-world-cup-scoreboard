package com.burakkbalta.scoreboard.domain;

import java.util.HashMap;
import java.util.Map;

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSummaryInOrderByTotalScore(boolean isAscending) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSummaryForAllMatchesInOrderByTotalScore(boolean isAscending) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSummaryForFinishedMatchesInOrderByTotalScore(boolean isAscending) {
        // TODO Auto-generated method stub
        return null;
    }

    


    
}
