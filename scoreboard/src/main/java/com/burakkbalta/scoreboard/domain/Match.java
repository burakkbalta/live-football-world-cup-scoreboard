package com.burakkbalta.scoreboard.domain;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Match {
    
    private final String homeTeamName;
    private final String awayTeamName;
    private final LocalDateTime startTime;

    public static Match createMatch(final String homeTeamName, final String awayTeamName) {
        return null;
    }

    private Match(final String homeTeamName, final String awayTeamName, final LocalDateTime startTime) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.startTime = startTime;
    }

    public String getHomeTeamName() {
        return "";
    }

    public String getAwayTeamName() {
        return "";
    }

    public LocalDateTime getStartTime() {
        return LocalDateTime.MIN;
    }

    @Override
    public int hashCode() {
        final int firstPrime = 17;
        final int secondPrime = 37;
        return new HashCodeBuilder(firstPrime, secondPrime)
                    .append(homeTeamName)
                    .append(awayTeamName)
                    .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
       return false;
    }

    

    
    
}
