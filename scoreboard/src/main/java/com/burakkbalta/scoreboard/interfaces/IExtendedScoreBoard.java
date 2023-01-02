package com.burakkbalta.scoreboard.interfaces;

public interface IExtendedScoreBoard extends IScoreBoard {
    String getSummaryInOrderByStartTime(final boolean isAscending);
    String getSummaryInOrderByTotalScore(final boolean isAscending);
    String getSummaryForAllMatchesInOrderByTotalScore(final boolean isAscending);
    String getSummaryForFinishedMatchesInOrderByTotalScore(final boolean isAscending);
}
