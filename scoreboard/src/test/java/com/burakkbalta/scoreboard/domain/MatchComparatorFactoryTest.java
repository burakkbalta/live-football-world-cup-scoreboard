package com.burakkbalta.scoreboard.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.burakkbalta.scoreboard.comparators.StartTimeComparator;
import com.burakkbalta.scoreboard.comparators.TotalScoreAndStartTimeComparator;
import com.burakkbalta.scoreboard.comparators.TotalScoreComparator;
import com.burakkbalta.scoreboard.enums.ComparatorTypes;
import com.burakkbalta.scoreboard.interfaces.IMatchComparator;

public class MatchComparatorFactoryTest {

    private MatchComparatorFactory matchComparatorFactory = null;

    @BeforeEach
    void setUp() {
        matchComparatorFactory = new MatchComparatorFactory();
    }

    private static Stream<Arguments> comparatorsDataProvider() {
        return Stream.of(
            Arguments.of(MatchComparatorFactory.getMatchComparator(ComparatorTypes.InOrderByTotalScore, 
                false), TotalScoreComparator.class),
            Arguments.of(MatchComparatorFactory.getMatchComparator(ComparatorTypes.InOrderByTotalScore, 
                true), TotalScoreComparator.class),
            Arguments.of(MatchComparatorFactory.getMatchComparator(ComparatorTypes.InOrderByStartTime, 
                false), StartTimeComparator.class),
            Arguments.of(MatchComparatorFactory.getMatchComparator(ComparatorTypes.InOrderByStartTime, 
                true), StartTimeComparator.class),
            Arguments.of(MatchComparatorFactory.getMatchComparator(ComparatorTypes.InOrderByTotalScoreAndLastAddedTime, 
                false), TotalScoreAndStartTimeComparator.class),
            Arguments.of(MatchComparatorFactory.getMatchComparator(ComparatorTypes.InOrderByTotalScoreAndLastAddedTime, 
                true), TotalScoreAndStartTimeComparator.class)        
            
        );
    }

    @ParameterizedTest(name="Match #{index}")
    @MethodSource("comparatorsDataProvider")
    public void testGetMatchComparator(IMatchComparator matchComparator, Class<? extends IMatchComparator> compClass) {
        assertEquals(MatchComparatorFactory.class, matchComparatorFactory.getClass());
        assertEquals(compClass, matchComparator.getClass());
    }
}
