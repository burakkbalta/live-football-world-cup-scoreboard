package com.burakkbalta.scoreboard.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.burakkbalta.scoreboard.domain.Match;
import com.burakkbalta.scoreboard.domain.MatchComparatorFactory;
import com.burakkbalta.scoreboard.domain.MatchScore;
import com.burakkbalta.scoreboard.enums.ComparatorTypes;
import com.burakkbalta.scoreboard.interfaces.IMatchComparator;

public class TotalScoreComparatorTest {

    private static Stream<Arguments> totalScoreComparatorsDataProvider() {
        return Stream.of(
            Arguments.of(
                Match.createMatch("A", "B"), 
                MatchScore.createWithCustomScores(3,3),
                Match.createMatch("C", "D"),
                MatchScore.createWithCustomScores(1,3),
                true, 
                1),
            Arguments.of(
                Match.createMatch("A", "B"), 
                MatchScore.createWithCustomScores(3,3),
                Match.createMatch("C", "D"),
                MatchScore.createWithCustomScores(1,3),
                false, 
                -1),
            Arguments.of(
                Match.createMatch("A", "B"), 
                MatchScore.createWithCustomScores(3,3),
                Match.createMatch("C", "D"),
                MatchScore.createWithCustomScores(3,3),
                false, 
                0
            )     
        );
    }

    @ParameterizedTest(name="Match #{index}")
    @MethodSource("totalScoreComparatorsDataProvider")
    public void testCompareInternally(Match match1, MatchScore matchScore1, 
            Match match2, MatchScore matchScore2, 
            boolean isAscending,
            int compare) {
        match1.getMatchScore().setHomeTeamScore(matchScore1.getHomeTeamScore()); 
        match2.getMatchScore().setHomeTeamScore(matchScore2.getHomeTeamScore()); 
        match1.getMatchScore().setAwayTeamScore(matchScore1.getHomeTeamScore()); 
        match2.getMatchScore().setAwayTeamScore(matchScore2.getHomeTeamScore());        
        IMatchComparator totalScoreComparator = MatchComparatorFactory.getMatchComparator(ComparatorTypes.InOrderByTotalScore,
             isAscending);        
        assertEquals(compare, totalScoreComparator.compare(match1, match2));
    }
}
