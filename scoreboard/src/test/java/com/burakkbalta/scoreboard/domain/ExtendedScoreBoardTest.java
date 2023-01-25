package com.burakkbalta.scoreboard.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExtendedScoreBoardTest {
    private ExtendedScoreBoard scoreBoard = null;
    private Stream<Match> matches;

    @BeforeEach
    void setUp() {
        scoreBoard = new ExtendedScoreBoard();    
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

    @Test
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
        
        assertEquals(matchCountWrapper.matchCount, scoreBoard.getLiveMatches().size());    
    }

    @Test
    public void givenMultipleMatchesOnScoreBoard_whenFinishGamesWithEvenMatchIds_ThenNumberOfFinishedMatchesIsReturned() {
        var matchCountWrapper = new Object(){ int liveMatchCount, finishedMatchCount = 0; };

        matches.forEach(match -> {
            scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName());
            matchCountWrapper.liveMatchCount++;
        });

        IntStream.range(0, matchCountWrapper.liveMatchCount)
            .filter(matchId -> {
                return matchId % 2 == 0;
            })
            .forEach(evenMatchId -> {
                assertTrue(scoreBoard.finishGame(evenMatchId));
                matchCountWrapper.finishedMatchCount++;
            });
        
        assertEquals(matchCountWrapper.finishedMatchCount, scoreBoard.getFinishedMatches().size());    
    }

    @Test
    public void givenMultipleMatchesOnScoreBoard_whenGetSummaryInOrderByTotalScoreDesc_thenCorrectScoreBoardIsReturned() {
        var matchCountWrapper = new Object(){ int matchCount = 0; };

        matches.forEach(match -> {
            assertEquals(matchCountWrapper.matchCount, 
                    scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName()).get());
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

        StringBuilder summary = new StringBuilder();
        summary.append("1. Spain 10 - Brazil 2\n")
            .append("2. Uruguay 6 - Italy 6\n")
            .append("3. Mexico 0 - Canada 5\n")
            .append("4. Germany 2 - France 2\n")
            .append("5. Argentina 3 - Australia 1\n");

        assertEquals(summary.toString(), scoreBoard.getSummaryInOrderByTotalScore(false));

    }

    @Test
    void givenMultipleMatchesOnScoreBoard_whenGetSummaryInOrderByTotalScoreAsc_thenCorrectScoreBoardIsReturned() {
        var matchCountWrapper = new Object(){ int matchCount = 0; };

        matches.forEach(match -> {
            assertEquals(matchCountWrapper.matchCount, 
                    scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName()).get());
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

        StringBuilder summary = new StringBuilder();
        summary.append("1. Germany 2 - France 2\n")
            .append("2. Argentina 3 - Australia 1\n")
            .append("3. Mexico 0 - Canada 5\n")
            .append("4. Spain 10 - Brazil 2\n")
            .append("5. Uruguay 6 - Italy 6\n");
            
        assertEquals(summary.toString(), scoreBoard.getSummaryInOrderByTotalScore(true));
    }

    @Test
    void givenMultipleMatchesOnScoreBoard_whenGetSummaryInOrderByStartTimeAsc_thenCorrectScoreBoardIsReturned() {
        var matchCountWrapper = new Object(){ int matchCount = 0; };

        matches.forEach(match -> {
            assertEquals(matchCountWrapper.matchCount, 
                    scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName()).get());
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

        StringBuilder summary = new StringBuilder();
        summary.append("1. Mexico 0 - Canada 5\n")
            .append("2. Spain 10 - Brazil 2\n")
            .append("3. Germany 2 - France 2\n")
            .append("4. Uruguay 6 - Italy 6\n")
            .append("5. Argentina 3 - Australia 1\n");

        assertEquals(summary.toString(), scoreBoard.getSummaryInOrderByStartTime(true));
    }

    @Test
    void givenMultipleMatchesOnScoreBoard_whenGetSummaryInOrderByStartTimeDesc_thenCorrectScoreBoardIsReturned() {
        var matchCountWrapper = new Object(){ int matchCount = 0; };

        matches.forEach(match -> {
            assertEquals(matchCountWrapper.matchCount, 
                    scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName()).get());
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

        StringBuilder summary = new StringBuilder();
        summary.append("1. Argentina 3 - Australia 1\n")
            .append("2. Uruguay 6 - Italy 6\n")
            .append("3. Germany 2 - France 2\n")
            .append("4. Spain 10 - Brazil 2\n")
            .append("5. Mexico 0 - Canada 5\n");

        assertEquals(summary.toString(), scoreBoard.getSummaryInOrderByStartTime(false));
    }

    @Test
    void givenMultipleMatchesOnScoreBoard_whenGetSummaryForAllMatchesInOrderByTotalScoreDesc_thenCorrectScoreBoardIsReturned() {
        var matchCountWrapper = new Object(){ int matchCount = 0; };

        matches.forEach(match -> {
            assertEquals(matchCountWrapper.matchCount, 
                    scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName()).get());
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

        IntStream.range(0, matchCountWrapper.matchCount)
            .filter(matchId -> {
                return matchId % 2 == 0;
            })
            .forEach(evenMatchId -> {
                assertTrue(scoreBoard.finishGame(evenMatchId));
                matchCountWrapper.matchCount--;
            });

        StringBuilder summary = new StringBuilder();
        summary.append("1. Spain 10 - Brazil 2\n")
            .append("2. Uruguay 6 - Italy 6\n")
            .append("3. Mexico 0 - Canada 5\n")
            .append("4. Germany 2 - France 2\n")
            .append("5. Argentina 3 - Australia 1\n");

        assertEquals(summary.toString(), scoreBoard.getSummaryForAllMatchesInOrderByTotalScore(false));
    }

    @Test
    void givenMultipleMatchesOnScoreBoard_whenGetSummaryForAllMatchesInOrderByTotalScoreAsc_thenCorrectScoreBoardIsReturned() {
        var matchCountWrapper = new Object(){ int matchCount = 0; };

        matches.forEach(match -> {
            assertEquals(matchCountWrapper.matchCount, 
                    scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName()).get());
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

        IntStream.range(0, matchCountWrapper.matchCount)
            .filter(matchId -> {
                return matchId % 2 == 0;
            })
            .forEach(evenMatchId -> {
                assertTrue(scoreBoard.finishGame(evenMatchId));
                matchCountWrapper.matchCount--;
            });

        StringBuilder summary = new StringBuilder();
        summary.append("1. Germany 2 - France 2\n")
            .append("2. Argentina 3 - Australia 1\n")
            .append("3. Mexico 0 - Canada 5\n")
            .append("4. Spain 10 - Brazil 2\n")
            .append("5. Uruguay 6 - Italy 6\n");
            
        assertEquals(summary.toString(), scoreBoard.getSummaryForAllMatchesInOrderByTotalScore(true));
    }

    @Test
    void givenMultipleMatchesOnScoreBoard_whenGetSummaryForFinishedMatchesInOrderByTotalScoreAsc_thenCorrectScoreBoardIsReturned() {
        var matchCountWrapper = new Object(){ int matchCount = 0; };

        matches.forEach(match -> {
            assertEquals(matchCountWrapper.matchCount, 
                    scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName()).get());
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

        IntStream.range(0, matchCountWrapper.matchCount)
            .filter(matchId -> {
                return matchId % 2 == 0;
            })
            .forEach(evenMatchId -> {
                assertTrue(scoreBoard.finishGame(evenMatchId));
                matchCountWrapper.matchCount--;
            });

        StringBuilder summary = new StringBuilder();
        summary.append("1. Germany 2 - France 2\n")
            .append("2. Argentina 3 - Australia 1\n")
            .append("3. Mexico 0 - Canada 5\n");
        assertEquals(summary.toString(), scoreBoard.getSummaryForFinishedMatchesInOrderByTotalScore(true));
    }

    @Test
    void givenMultipleMatchesOnScoreBoard_whenGetSummaryForFinishedMatchesInOrderByTotalScoreDesc_thenCorrectScoreBoardIsReturned() {
        var matchCountWrapper = new Object(){ int matchCount = 0; };

        matches.forEach(match -> {
            assertEquals(matchCountWrapper.matchCount, 
                    scoreBoard.startGame(match.getHomeTeamName(), match.getAwayTeamName()).get());
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

        IntStream.range(0, matchCountWrapper.matchCount)
            .filter(matchId -> {
                return matchId % 2 == 0;
            })
            .forEach(evenMatchId -> {
                assertTrue(scoreBoard.finishGame(evenMatchId));
                matchCountWrapper.matchCount--;
            });

        StringBuilder summary = new StringBuilder();
        summary.append("1. Mexico 0 - Canada 5\n")
            .append("2. Germany 2 - France 2\n")
            .append("3. Argentina 3 - Australia 1\n");
            
        assertEquals(summary.toString(), scoreBoard.getSummaryForFinishedMatchesInOrderByTotalScore(false));
    }
}
