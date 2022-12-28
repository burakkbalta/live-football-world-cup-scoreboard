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
        return -1;
    }

    public void setHomeTeamScore(final int homeTeamScore) {
        this.homeTeamScore = -1;
    }

    public int getAwayTeamScore() {
        return -1;
    }

    public void setAwayTeamScore(final int awayTeamScore) {
        this.awayTeamScore = -1;
    }

    @Override
    public int hashCode() {
        return -1;
    }

    @Override
    public boolean equals(final Object obj) {
        return false;
    }    
}
