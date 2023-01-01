package com.burakkbalta.scoreboard.domain;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * This class represents the game.
 * When the game starts, the initial scores is set to 0-0.
 * The start time indicates when the match started to be track.
 */
public class Match {
    
    private final String homeTeamName;
    private final String awayTeamName;
    private final MatchScore matchScore;
    private final LocalDateTime startTime;

    public static Match createMatch(final String homeTeamName, final String awayTeamName) {
        return new Match(homeTeamName, awayTeamName, MatchScore.createWithDefaultScores(), LocalDateTime.now());
    }

    private Match(final String homeTeamName, final String awayTeamName, final MatchScore matchScore, 
            final LocalDateTime startTime) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.matchScore = matchScore;
        this.startTime = startTime;
    }

    public String getHomeTeamName() {
        return this.homeTeamName;
    }

    public String getAwayTeamName() {
        return this.awayTeamName;
    }

    public MatchScore getMatchScore() {
        return this.matchScore;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    @Override
    public int hashCode() {
        final int firstPrime = 17;
        final int secondPrime = 37;
        return new HashCodeBuilder(firstPrime, secondPrime)
                    .append(homeTeamName)
                    .append(awayTeamName)
                    .append(matchScore)
                    .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Match other = (Match) obj;
        return new EqualsBuilder()
                    .append(homeTeamName, other.homeTeamName)
                    .append(awayTeamName, other.awayTeamName)
                    .append(matchScore, other.matchScore)
                    .isEquals();
    }

    

    
    
}
