package com.burakkbalta.scoreboard.comparators;

import com.burakkbalta.scoreboard.domain.Match;
import com.burakkbalta.scoreboard.interfaces.IMatchComparator;

public class StartTimeComparator implements IMatchComparator {

    private final boolean isAscending;
    
    public StartTimeComparator(boolean isAscending) {
        this.isAscending = isAscending;
    }

    @Override
    public int compareInternally(Match o1, Match o2) {
        int result = -1;
        result = this.isAscending 
            ? o1.getStartTime().compareTo(o2.getStartTime())
            : o2.getStartTime().compareTo(o1.getStartTime());

        return result;
    }
    
}
