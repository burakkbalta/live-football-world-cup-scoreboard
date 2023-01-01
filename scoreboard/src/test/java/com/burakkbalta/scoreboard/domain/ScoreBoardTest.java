package com.burakkbalta.scoreboard.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.burakkbalta.scoreboard.interfaces.IScoreBoard;

public class ScoreBoardTest {

    private IScoreBoard scoreBoard = null;

    @BeforeEach
    void setUp() {
        scoreBoard = new ScoreBoard();    
    }

    private static Stream<Arguments> matchesAndMatchIdsProvider() {
        return Stream.of(
            Arguments.of(Match.createMatch("Mexico", "Canada"), 0),
            Arguments.of(Match.createMatch("Spain", "Brazil"), 1),
            Arguments.of(Match.createMatch("Germany", "France"), 2),
            Arguments.of(Match.createMatch("Uruguay", "Italy"), 3),
            Arguments.of(Match.createMatch("Argentina", "Australia"), 4)
        );
    }

    @ParameterizedTest(name="Match #{index}")
    @MethodSource("matchesAndMatchIdsProvider")
    void givenScoreBoardWithDataProvider_whenStartGame_ThenMatchIdIsReturnedSequentially(Match match, 
            int correspondingMatchId) {
        var homeTeam = match.getHomeTeamName();
        var awayTeam = match.getAwayTeamName();
        var matchId = scoreBoard.startGame(homeTeam, awayTeam);

        assertEquals(correspondingMatchId, matchId);
    }

    @Test
    void givenMultipleMatchesOnScoreBoard_whenStartGame_ThenTotalNumberOfLiveMatchIsReturned() {
        var matches = Stream.of(
            Match.createMatch("Mexico", "Canada"),
            Match.createMatch("Spain", "Brazil"),
            Match.createMatch("Germany", "France"),
            Match.createMatch("Uruguay", "Italy"),
            Match.createMatch("Argentina", "Australia")
        );

        var matchCountWrapper = new Object(){ int matchCount = 0; };

        matches.forEach(match -> {
            scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName());
            matchCountWrapper.matchCount++;
        });

        assertEquals(matchCountWrapper.matchCount, ((ScoreBoard)scoreBoard).getLiveMatches().size());
    }

}
