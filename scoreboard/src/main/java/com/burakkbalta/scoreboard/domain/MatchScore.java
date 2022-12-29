package com.burakkbalta.scoreboard.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MatchScore {
    
    private int homeTeamScore;
    private int awayTeamScore;
    
    public static MatchScore createWithDefaultScores() {
        return new MatchScore(0,0);
    }

    public static MatchScore createWithCustomScores(final int homeTeamScore, final int awayTeamScore) {
        return new MatchScore(homeTeamScore, awayTeamScore);
    }

    public MatchScore(final int homeTeamScore, final int awayTeamScore) {
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public int getHomeTeamScore() {
        return this.homeTeamScore;
    }

    public void setHomeTeamScore(final int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return this.awayTeamScore;
    }

    public void setAwayTeamScore(final int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    @Override
    public int hashCode() {
        final int firstPrime = 17;
        final int secondPrime = 37;
        return new HashCodeBuilder(firstPrime, secondPrime)
                    .append(homeTeamScore)
                    .append(awayTeamScore)
                    .toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MatchScore other = (MatchScore) obj;
        return new EqualsBuilder()
                    .append(homeTeamScore, other.homeTeamScore)
                    .append(awayTeamScore, other.awayTeamScore)
                    .isEquals();
    }    
}
