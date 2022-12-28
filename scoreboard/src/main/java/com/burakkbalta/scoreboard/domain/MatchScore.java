package com.burakkbalta.scoreboard.domain;

public class MatchScore {
    
    private int homeTeamScore;
    private int awayTeamScore;
    
    public MatchScore() {
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
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
        final int prime = 31;
        int result = 1;
        result = prime * result + homeTeamScore;
        result = prime * result + awayTeamScore;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final MatchScore other = (MatchScore) obj;
        if (homeTeamScore != other.homeTeamScore)
            return false;
        if (awayTeamScore != other.awayTeamScore)
            return false;
        return true;
    }    
}
