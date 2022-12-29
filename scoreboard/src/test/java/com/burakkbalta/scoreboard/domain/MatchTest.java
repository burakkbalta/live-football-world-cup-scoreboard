package com.burakkbalta.scoreboard.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class MatchTest {
    @Test
    public void whenCreateMatch_thenCorrectMatchIsReturned() {
        Match match = Match.createMatch("Mexico", "Canada");

        assertEquals(Match.class, match.getClass());
        assertEquals("Mexico", match.getHomeTeamName());
        assertEquals("Canada", match.getAwayTeamName());
    }

    @Test
    public void givenTwoEqualsMatch_whenEquals_thenTrueIsReturned() {
        Match match = Match.createMatch("Spain", "Brazil");
        Match matchIdentical = Match.createMatch("Spain", "Brazil");

        assertTrue("equals function should be returned true since objects are same."
                , match.equals(matchIdentical));
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

        assertTrue("Current time should be ahead of start time start time of match object", isAhead);
    }

    @Test
    public void givenMatchScore_whenHashCode_thenCorrectHashValueIsReturned() {
        Match match = Match.createMatch("Argentina", "Australia");
        final int hashCode = 2052527372;

        assertEquals(hashCode, match.hashCode());

    }
}
