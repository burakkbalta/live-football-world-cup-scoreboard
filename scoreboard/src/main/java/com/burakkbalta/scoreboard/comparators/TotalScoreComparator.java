package com.burakkbalta.scoreboard.comparators;

import com.burakkbalta.scoreboard.domain.Match;
import com.burakkbalta.scoreboard.interfaces.IMatchComparator;

public class TotalScoreComparator implements IMatchComparator {

    private final boolean isAscending;
    
    public TotalScoreComparator(boolean isAscending) {
        this.isAscending = isAscending;
    }

    @Override
    public int compareInternally(Match o1, Match o2) {
        int lhsTotalScore = o1.getMatchScore().getHomeTeamScore() 
                + o1.getMatchScore().getAwayTeamScore();      
        int rhsTotalScore = o2.getMatchScore().getHomeTeamScore() 
                + o2.getMatchScore().getAwayTeamScore();

        int result = -1;
        if(isAscending) {
            result = Integer.compare(lhsTotalScore, rhsTotalScore);
        } else {
            result = Integer.compare(rhsTotalScore, lhsTotalScore);
        }
        
        return result;
    }
    
}
