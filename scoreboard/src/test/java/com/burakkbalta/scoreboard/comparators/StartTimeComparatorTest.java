package com.burakkbalta.scoreboard.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.burakkbalta.scoreboard.domain.Match;
import com.burakkbalta.scoreboard.domain.MatchComparatorFactory;
import com.burakkbalta.scoreboard.enums.ComparatorTypes;
import com.burakkbalta.scoreboard.interfaces.IMatchComparator;

public class StartTimeComparatorTest {
    private static Stream<Arguments> startTimeComparatorsDataProvider() {
        
        return Stream.of(
            Arguments.of(
                Match.createMatch("A", "B"), 
                Match.createMatch("C", "D"),
                true, 
                -1),
            Arguments.of(
                Match.createMatch("A", "B"), 
                Match.createMatch("C", "D"),
                false, 
                1)     
        );
    }

    @ParameterizedTest(name="Match #{index}")
    @MethodSource("startTimeComparatorsDataProvider")
    public void testCompareInternally(Match match1, Match match2, 
            boolean isAscending, int compare) {       
        IMatchComparator startTimeComparator = MatchComparatorFactory.getMatchComparator(ComparatorTypes.InOrderByStartTime,
             isAscending);        
        assertEquals(compare, startTimeComparator.compare(match1, match2));
    }

    @Test
    public void testCompareInternallyWithSameObject() {
        IMatchComparator startTimeComparator = MatchComparatorFactory.getMatchComparator(ComparatorTypes.InOrderByStartTime,
             true);
        Match match = Match.createMatch("A", "B");
        assertEquals(0, startTimeComparator.compare(match, match));

    }
}
