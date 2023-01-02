package com.burakkbalta.scoreboard.domain;

import com.burakkbalta.scoreboard.comparators.StartTimeComparator;
import com.burakkbalta.scoreboard.comparators.TotalScoreAndStartTimeComparator;
import com.burakkbalta.scoreboard.comparators.TotalScoreComparator;
import com.burakkbalta.scoreboard.enums.ComparatorTypes;
import com.burakkbalta.scoreboard.interfaces.IMatchComparator;

public class MatchComparatorFactory {

    public static IMatchComparator getMatchComparator(ComparatorTypes type, boolean isAscending) {
        IMatchComparator matchComparator = null;

        switch(type) {
            case InOrderByTotalScore:
                matchComparator = new TotalScoreComparator(isAscending);
                break;
            case InOrderByStartTime:
                matchComparator = new StartTimeComparator(isAscending);
                break;
            case InOrderByTotalScoreAndLastAddedTime:
                matchComparator = new TotalScoreAndStartTimeComparator(isAscending, isAscending);
                break;
            default:
                matchComparator = new TotalScoreAndStartTimeComparator(false, false);
                break;    
        }

        return matchComparator;
    }
    
}
