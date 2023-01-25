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

        System.out.println(scoreBoard.updateScore(mexCanId.get(), 0, 5));
        System.out.println(scoreBoard.updateScore(spaBraId.get(), 10, 2));
        System.out.println(scoreBoard.updateScore(gerFraId.get(), 2, 2));
        System.out.println(scoreBoard.updateScore(uruItaId.get(), 6, 6));
        System.out.println(scoreBoard.updateScore(argAusId.get(), 3, 1));

        System.out.println("Summary");
        System.out.println(scoreBoard.getSummaryInOrderByTotalScore());

        System.out.println(scoreBoard.finishGame(mexCanId.get()));
        System.out.println(scoreBoard.finishGame(uruItaId.get()));

        System.out.println("Updated Summary");
        System.out.println(scoreBoard.getSummaryInOrderByTotalScore());
    }
}
