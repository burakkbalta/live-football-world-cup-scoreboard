package com.burakkbalta.scoreboard.domain;

import com.burakkbalta.scoreboard.enums.ScoreBoardTypes;
import com.burakkbalta.scoreboard.interfaces.IScoreBoard;

public class ScoreBoardFactory {
    
    public static IScoreBoard getScoreBoard(ScoreBoardTypes type) {
        IScoreBoard scoreBoard = null;
        switch(type) {
            case Default:
                scoreBoard = new ScoreBoard(); 
                break;
            case Extended:
                scoreBoard = new ExtendedScoreBoard(); 
                break;
            default:
                scoreBoard = new ScoreBoard(); 
                break;
        }

        return scoreBoard;
    }
}
