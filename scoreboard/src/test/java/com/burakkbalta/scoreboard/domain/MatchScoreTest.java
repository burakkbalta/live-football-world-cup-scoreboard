package com.burakkbalta.scoreboard.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MatchScoreTest {
    @Test
    public void givenTwoEqualsMatchScores_whenEquals_thenTrueIsReturned() {
        MatchScore matchScore = new MatchScore(3,3);
        MatchScore matchScoreIdentical = new MatchScore(3,3);

        assertTrue("equals function should be returned true since objects are same."
                , matchScore.equals(matchScoreIdentical));
    }

    @Test
    public void givenMatchScore_whenGetAwayTeamScore_thenTheConstructedVariableIsReturned() {
        MatchScore matchScore = new MatchScore(0, 3);

        assertEquals(3, matchScore.getAwayTeamScore());
    }

    @Test
    public void givenMatchScore_whenGetHomeTeamScore_thenTheConstructedVariableIsReturned() {
        MatchScore matchScore = new MatchScore(2, 1);

        assertEquals(2, matchScore.getHomeTeamScore());
    }

    @Test
    public void givenMatchScore_whenHashCode_thenCorrectHashValueIsReturned() {
        MatchScore matchScore = new MatchScore(1,1);
        final int hashCode = 993;

        assertEquals(hashCode, matchScore.hashCode());
    }

    @Test
    public void givenMatchScore_whenAwayTeamScoreIsUpdated_thenThatUpdatedScoreIsReturned() {
        MatchScore matchScore = new MatchScore();
        matchScore.setAwayTeamScore(4);

        assertEquals(4, matchScore.getAwayTeamScore());
    }

    @Test
    public void givenMatchScore_whenHomeTeamScoreIsUpdated_thenThatUpdatedScoreIsReturned() {
        MatchScore matchScore = new MatchScore();
        matchScore.setAwayTeamScore(5);

        assertEquals(5, matchScore.getHomeTeamScore());
    }
}
