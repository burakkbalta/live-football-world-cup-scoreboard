package com.burakkbalta.scoreboard.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.burakkbalta.scoreboard.interfaces.IScoreBoard;

@TestMethodOrder(OrderAnnotation.class)
public class ScoreBoardTest {

    private IScoreBoard scoreBoard = null;
    private Stream<Match> matches;

    @BeforeEach
    void setUp() {
        scoreBoard = new ScoreBoard();    
        matches = Stream.of(
            Match.createMatch("Mexico", "Canada"),
            Match.createMatch("Spain", "Brazil"),
            Match.createMatch("Germany", "France"),
            Match.createMatch("Uruguay", "Italy"),
            Match.createMatch("Argentina", "Australia")
        );
    }

    @AfterEach
    void tearDown() {
        scoreBoard = null;
        matches = null;
    }

    private static Stream<Arguments> matchesAndMatchIdsProvider() {
        return Stream.of(
            Arguments.of(Match.createMatch("Mexico", "Canada"), 0),
            Arguments.of(Match.createMatch("Spain", "Brazil"), 0),
            Arguments.of(Match.createMatch("Germany", "France"), 0),
            Arguments.of(Match.createMatch("Uruguay", "Italy"), 0),
            Arguments.of(Match.createMatch("Argentina", "Australia"), 0)
        );
    }

    @ParameterizedTest(name="Match #{index}")
    @MethodSource("matchesAndMatchIdsProvider")
    @Order(1)
    public void givenScoreBoardWithDataProvider_whenStartGame_ThenMatchIdShouldBeZeroInEachExecution(Match match, 
            int correspondingMatchId) {
        var homeTeam = match.getHomeTeamName();
        var awayTeam = match.getAwayTeamName();
        var matchId = scoreBoard.startGame(homeTeam, awayTeam);

        assertEquals(correspondingMatchId, matchId);
    }

    @Test
    @Order(2)
    public void givenMultipleMatchesOnScoreBoard_whenStartGame_ThenTotalNumberOfLiveMatchesIsReturned() {
        var matchCountWrapper = new Object(){ int matchCount = 0; };

        matches.forEach(match -> {
            assertEquals(matchCountWrapper.matchCount, 
                    scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName()));
            matchCountWrapper.matchCount++;
        });

        assertEquals(matchCountWrapper.matchCount, ((ScoreBoard)scoreBoard).getLiveMatches().size());
    }

    @Test
    @Order(3)
    public void givenMultipleMatchesOnScoreBoard_whenFinishGamesWithEvenMatchIds_ThenNumberOfLiveMatchesIsReturned() {
        var matchCountWrapper = new Object(){ int matchCount = 0; };

        matches.forEach(match -> {
            scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName());
            matchCountWrapper.matchCount++;
        });

        IntStream.range(0, matchCountWrapper.matchCount)
            .filter(matchId -> {
                return matchId % 2 == 0;
            })
            .forEach(evenMatchId -> {
                assertTrue(scoreBoard.finishGame(evenMatchId));
                matchCountWrapper.matchCount--;
            });
        
        assertEquals(matchCountWrapper.matchCount, ((ScoreBoard)scoreBoard).getLiveMatches().size());    
    }

    @Test
    @Order(4)
    public void givenMultipleMatchesOnScoreBoard_whenFinishGamesWithUnexistedMatchIds_ThenFalseIsReturnedForEachCall() {
        var matchCountWrapper = new Object(){ int matchCount = 0; };

        matches.forEach(match -> {
            scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName());
            matchCountWrapper.matchCount++;
        });

        IntStream.range(5, 10)
            .forEach(matchId -> {
                final boolean isFinished = scoreBoard.finishGame(matchId);
                matchCountWrapper.matchCount = isFinished 
                    ? matchCountWrapper.matchCount-- 
                    : matchCountWrapper.matchCount;
                assertFalse(isFinished);
            });
        assertEquals(matchCountWrapper.matchCount, ((ScoreBoard)scoreBoard).getLiveMatches().size()); 
    }

    @Test
    @Order(5)
    public void givenMultipleMatchesOnScoreBoard_whenMatchScoresAreUpdated_ThenTrueIsReturnedForEachCall() {
        var matchCountWrapper = new Object(){ int matchCount = 0; };

        matches.forEach(match -> {
            assertEquals(matchCountWrapper.matchCount, 
                    scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName()));
            matchCountWrapper.matchCount++;
        });

        Map<Integer, MatchScore> updatedMatchScoresMatchIdMap = Map.of(
            0, MatchScore.createWithCustomScores(0, 5),
            1, MatchScore.createWithCustomScores(10, 2),
            2, MatchScore.createWithCustomScores(2, 2),
            3, MatchScore.createWithCustomScores(6, 6),
            4, MatchScore.createWithCustomScores(3, 1)
        );

        updatedMatchScoresMatchIdMap.forEach((matchId, updatedMatchScore) -> {
            assertTrue(scoreBoard.updateScore(matchId, 
                    updatedMatchScore.getHomeTeamScore(), updatedMatchScore.getAwayTeamScore()));
        });

        ((ScoreBoard)scoreBoard).getLiveMatches().forEach((matchId, match) -> {
            assertEquals(updatedMatchScoresMatchIdMap.get(matchId), match.getMatchScore());
        });
    }

    @Test
    @Order(6)
    public void givenScoreBoard_whenMatchScoresForNonExistentMatchesAreUpdated_ThenFalseIsReturnedForEachCall() {
        var matchCountWrapper = new Object(){ int matchCount = 0; };

        matches.forEach(match -> {
            assertEquals(matchCountWrapper.matchCount, 
                    scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName()));
            matchCountWrapper.matchCount++;
        });

        Map<Integer, MatchScore> updatedMatchScoresMatchIdMap = Map.of(
            5, MatchScore.createWithCustomScores(0, 5),
            6, MatchScore.createWithCustomScores(10, 2),
            7, MatchScore.createWithCustomScores(2, 2),
            8, MatchScore.createWithCustomScores(6, 6),
            9, MatchScore.createWithCustomScores(3, 1)
        );

        updatedMatchScoresMatchIdMap.forEach((matchId, updatedMatchScore) -> {
            assertFalse(scoreBoard.updateScore(matchId, 
                    updatedMatchScore.getHomeTeamScore(), updatedMatchScore.getAwayTeamScore()));
        });
    }

}
