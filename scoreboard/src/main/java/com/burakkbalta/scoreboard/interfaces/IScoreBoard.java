package com.burakkbalta.scoreboard.interfaces;

import java.util.List;
import java.util.Optional;

import com.burakkbalta.scoreboard.domain.Match;

public interface IScoreBoard {

    // Returns Match Id that refers the started match if entries are correct
    Optional<Integer> startGame(final String homeTeamName, final String awayTeamName);
    boolean finishGame(final int matchId);
    boolean updateScore(final int matchId, final int homeTeamScore, final int awayTeamScore);
    String getSummaryInOrderByTotalScore();

    default String getSummary(List<Match> sortedMatches) {
        StringBuilder summary = new StringBuilder();
        Match match = null;
        for(int i = 1; i<=sortedMatches.size(); i++) {
            match = sortedMatches.get(i-1);
            summary.append(i)
                .append(". ")
                .append(match.getHomeTeamName())
                .append(" ")
                .append(match.getMatchScore().getHomeTeamScore())
                .append(" - ")
                .append(match.getAwayTeamName())
                .append(" ")
                .append(match.getMatchScore().getAwayTeamScore())
                .append("\n");
        }

        return summary.toString();
    }
}
