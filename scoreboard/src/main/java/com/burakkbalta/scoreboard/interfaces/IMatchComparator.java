package com.burakkbalta.scoreboard.interfaces;

import java.util.Comparator;

import com.burakkbalta.scoreboard.domain.Match;

public interface IMatchComparator extends Comparator<Match> {

    @Override
    default int compare(Match o1, Match o2) {
        return compareInternally(o1, o2);
    }

    int compareInternally(final Match o1, final Match o2);
    
}
