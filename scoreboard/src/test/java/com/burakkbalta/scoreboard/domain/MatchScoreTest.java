package com.burakkbalta.scoreboard.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MatchScoreTest {
    @Test
    public void givenTwoEqualsMatchScores_whenEquals_thenTrueIsReturned() {
        MatchScore matchScore = MatchScore.createWithCustomScores(3,3);
        MatchScore matchScoreIdentical = MatchScore.createWithCustomScores(3,3);

        assertTrue(matchScore.equals(matchScoreIdentical), 
            "equals function should return true since objects are same.");
    }

    @Test
    public void givenMatchScore_whenEqualsIsInvokedWithItself_thenTrueIsReturned() {
        MatchScore matchScore = MatchScore.createWithCustomScores(3,3);

        assertTrue(matchScore.equals(matchScore), 
            "since equals function is invoked with itself, it should return true.");
    }

    @Test
    public void givenMatchScoreAndNullObject_whenEquals_thenFalseIsReturned() {
        MatchScore matchScore = MatchScore.createWithDefaultScores();

        assertFalse(matchScore.equals(null), 
            "equals function should return false while comparing null with concrete object.");
    }

    @Test
    public void givenMatchScoreAndString_whenEquals_thenFalseIsReturned() {
        MatchScore matchScore = MatchScore.createWithDefaultScores();
        Object dummyObject = new String("dummy object");

        assertFalse(matchScore.equals(dummyObject), 
            "equals function should return false while comparing String class with concrete object.");
    }

    @Test
    public void givenMatchScore_whenGetAwayTeamScore_thenTheConstructedVariableIsReturned() {
        MatchScore matchScore = MatchScore.createWithCustomScores(0, 3);

        assertEquals(3, matchScore.getAwayTeamScore());
    }

    @Test
    public void givenMatchScore_whenGetHomeTeamScore_thenTheConstructedVariableIsReturned() {
        MatchScore matchScore = MatchScore.createWithCustomScores(2, 1);

        assertEquals(2, matchScore.getHomeTeamScore());
    }

    @Test
    public void givenMatchScore_whenHashCode_thenCorrectHashValueIsReturned() {
        MatchScore matchScore = MatchScore.createWithCustomScores(1,1);
        final int hashCode = 23311;

        assertEquals(hashCode, matchScore.hashCode());
    }

    @Test
    public void givenMatchScore_whenAwayTeamScoreIsUpdated_thenThatUpdatedScoreIsReturned() {
        MatchScore matchScore = MatchScore.createWithDefaultScores();
        matchScore.setAwayTeamScore(4);

        assertEquals(4, matchScore.getAwayTeamScore());
    }

    @Test
    public void givenMatchScore_whenHomeTeamScoreIsUpdated_thenThatUpdatedScoreIsReturned() {
        MatchScore matchScore = MatchScore.createWithDefaultScores();
        matchScore.setHomeTeamScore(5);

        assertEquals(5, matchScore.getHomeTeamScore());
    }
}
