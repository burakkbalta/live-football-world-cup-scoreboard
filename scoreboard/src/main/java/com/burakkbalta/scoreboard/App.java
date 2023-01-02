package com.burakkbalta.scoreboard;

import com.burakkbalta.scoreboard.domain.ScoreBoardFactory;
import com.burakkbalta.scoreboard.enums.ScoreBoardTypes;
import com.burakkbalta.scoreboard.interfaces.IScoreBoard;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        IScoreBoard scoreBoard = ScoreBoardFactory.getScoreBoard(ScoreBoardTypes.Default);
        var mexCanId = scoreBoard.startGame("Mexico", "Canada");
        var spaBraId = scoreBoard.startGame("Spain", "Brazil");
        var gerFraId = scoreBoard.startGame("Germany", "France");
        var uruItaId = scoreBoard.startGame("Uruguay", "Italy");
        var argAusId = scoreBoard.startGame("Argentina", "Australia");

        System.out.println(scoreBoard.updateScore(mexCanId, 0, 5));
        System.out.println(scoreBoard.updateScore(spaBraId, 10, 2));
        System.out.println(scoreBoard.updateScore(gerFraId, 2, 2));
        System.out.println(scoreBoard.updateScore(uruItaId, 6, 6));
        System.out.println(scoreBoard.updateScore(argAusId, 3, 1));

        System.out.println("Summary");
        System.out.println(scoreBoard.getSummaryInOrderByTotalScore());

        System.out.println(scoreBoard.finishGame(mexCanId));
        System.out.println(scoreBoard.finishGame(uruItaId));

        System.out.println("Updated Summary");
        System.out.println(scoreBoard.getSummaryInOrderByTotalScore());
    }
}
