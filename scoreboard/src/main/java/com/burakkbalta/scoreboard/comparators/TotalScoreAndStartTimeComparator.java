package com.burakkbalta.scoreboard.comparators;

import com.burakkbalta.scoreboard.domain.Match;
import com.burakkbalta.scoreboard.interfaces.IMatchComparator;

public class TotalScoreAndStartTimeComparator implements IMatchComparator {

    private final TotalScoreComparator totalScoreComparator;
    private final StartTimeComparator startTimeComparator;

    public TotalScoreAndStartTimeComparator(final boolean isAscendingTotalScore, final boolean isAscendingStartTime) {
        totalScoreComparator = new TotalScoreComparator(isAscendingTotalScore);
        startTimeComparator = new StartTimeComparator(isAscendingStartTime);
    }
    
    @Override
    public int compareInternally(Match o1, Match o2) {
        int compare = totalScoreComparator.compareInternally(o1, o2);
        compare = compare == 0 
            ? startTimeComparator.compareInternally(o1, o2)
            : compare;
        return compare;
    }
    
}
