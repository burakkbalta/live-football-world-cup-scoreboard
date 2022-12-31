package com.burakkbalta.scoreboard.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class MatchTest {
    @Test
    public void whenCreateMatch_thenCorrectMatchIsReturned() {
        Match match = Match.createMatch("Mexico", "Canada");
        MatchScore matchScore = MatchScore.createWithDefaultScores();

        assertEquals(Match.class, match.getClass());
        assertEquals("Mexico", match.getHomeTeamName());
        assertEquals("Canada", match.getAwayTeamName());
        assertEquals(matchScore, match.getMatchScore());
    }

    @Test
    public void givenTwoEqualsMatch_whenEquals_thenTrueIsReturned() {
        Match match = Match.createMatch("Spain", "Brazil");
        Match matchIdentical = Match.createMatch("Spain", "Brazil");

        assertTrue(match.equals(matchIdentical), 
                "equals function should return true since objects are same.");
    }

    @Test
    public void givenMatch_whenEqualsIsInvokedWithItself_thenTrueIsReturned() {
        Match match = Match.createMatch("Spain","Brazil");

        assertTrue(match.equals(match), 
            "since equals function is invoked with itself, it should return true.");
    }

    @Test
    public void givenMatchScoreAndNullObject_whenEquals_thenFalseIsReturned() {
        Match match = Match.createMatch("Germany", "France");

        assertFalse(match.equals(null), 
            "equals function should return false while comparing null with concrete object.");
    }

    @Test
    public void givenMatchAndMatchScore_whenEquals_thenFalseIsReturned() {
        Match match = Match.createMatch("Mexico", "Canada");
        MatchScore matchScore = MatchScore.createWithDefaultScores();

        assertFalse(match.equals(matchScore), 
            "equals function should return false while comparing Match with MatchScore classes.");
    }

    @Test
    public void givenCreatedMatch_whenGetHomeTeamName_thenTheConstructedVariableIsReturned() {
        Match match = Match.createMatch("Germany", "France");

        assertEquals("Germany", match.getHomeTeamName());
    }

    @Test
    public void givenCreatedMatch_whenGetAwayTeamName_thenTheConstructedVariableIsReturned() {
        Match match = Match.createMatch("Uruguay", "Italy");

        assertEquals("Italy", match.getAwayTeamName());
    }

    @Test
    public void givenCreatedMatch_whenGetStartTime_thenCurrentTimeShouldBeAheadOfStartTime() {
        Match match = Match.createMatch("Argentina", "Australia");
        boolean isAhead = LocalDateTime.now().compareTo(match.getStartTime()) > 0;

        assertTrue(isAhead, "Current time should be ahead of start time start time of match object.");
    }

    @Test
    public void givenMatch_whenHashCode_thenCorrectHashValueIsReturned() {
        Match match = Match.createMatch("Argentina", "Australia");
        final int hashCode = -1365875291;

        assertEquals(hashCode, match.hashCode());
    }

    @Test
    public void givenCreatedMatch_whenGetMatchScore_thenTheMatchScoreWithZerosIsReturned() {
        Match match = Match.createMatch("Germany", "France");
        MatchScore matchScoreWithDefault = MatchScore.createWithDefaultScores();

        final var matchScore = match.getMatchScore();
        assertEquals(0, matchScore.getHomeTeamScore());
        assertEquals(0, matchScore.getAwayTeamScore());
        assertEquals(matchScoreWithDefault, matchScore);
    }
}
