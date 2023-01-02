package com.burakkbalta.scoreboard.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.burakkbalta.scoreboard.enums.ScoreBoardTypes;
import com.burakkbalta.scoreboard.interfaces.IScoreBoard;

public class ScoreBoardFactoryTest {

    private ScoreBoardFactory scoreBoardFactory = null;

    @BeforeEach
    void setUp() {
        scoreBoardFactory = new ScoreBoardFactory();
    }

    private static Stream<Arguments> scoreBoardsDataProvider() {
        return Stream.of(
            Arguments.of(ScoreBoardFactory.getScoreBoard(ScoreBoardTypes.Default),ScoreBoard.class),
            Arguments.of(ScoreBoardFactory.getScoreBoard(ScoreBoardTypes.Extended),ExtendedScoreBoard.class)           
        );
    }

    @ParameterizedTest(name="Match #{index}")
    @MethodSource("scoreBoardsDataProvider")
    public void testGetMatchComparator(IScoreBoard scoreBoard, Class<? extends IScoreBoard> scoreBoardClass) {
        assertEquals(ScoreBoardFactory.class, scoreBoardFactory.getClass());
        assertEquals(scoreBoardClass, scoreBoard.getClass());
    }
}
