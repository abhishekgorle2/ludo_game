package com.tdevelopments.ludo_game.Characters.Ludo.ui;

import android.util.Log;

import com.tdevelopments.ludo_game.Characters.Ludo.Piece.Piece;
import com.tdevelopments.ludo_game.LudoActivity;

public class MovePiece {

    public static void move(LudoActivity.LudoGameView game) {

        if(game.t == LudoActivity.Turn.RED)
        {
            Piece[] pieces = game.playerRed.getRedPiecePositions();
            for (int i = 0; i < pieces.length; i++)
            {
                Piece p = pieces[i];
                if(p.getMove())
                {
                    if(p.UpdatePosition())
                    {
                        boolean clear = true;
                        p.setMove(false);
                        game.isMoving = false;
                        game.toMove = false;
                        if(game.initGreen) {
                            for (int j = 0; j < 4; j++)
                            {
                                Piece p1 = game.playerGreen.getRedPiecePositions()[j];
                                if(p.getCollision().intersect(p1.getCollision()))
                                {
                                    if(!p.getIsStar(p.getTarget()))
                                    {
                                        p1.setHome(true);
                                        game.toHome = true;
                                        clear = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if(game.initBlue)
                        {
                            for (int j = 0; j < 4; j++)
                            {
                                Piece p1 = game.playerBlue.getRedPiecePositions()[j];
                                if(p.getCollision().intersect(p1.getCollision()))
                                {
                                    if(!p.getIsStar(p.getTarget()))
                                    {
                                        p1.setHome(true);
                                        game.toHome = true;
                                        clear = false;
                                        break;
                                    }
                                }
                            }
                        }

                        if(game.initYellow)
                        {
                            for (int j = 0; j < 4; j++)
                            {
                                Piece p1 = game.playerYellow.getRedPiecePositions()[j];
                                if(p.getCollision().intersect(p1.getCollision()))
                                {
                                    if(!p.getIsStar(p.getTarget()))
                                    {
                                        p1.setHome(true);
                                        game.toHome = true;
                                        clear = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if(clear) {
                            if(p.getComplete())
                            {
                                Log.d("LudoActivity","Piece Complete");
                                int com = 0;
                                for (int j = 0; j < 4; j++) {
                                    if(pieces[j].getComplete())
                                        com++;
                                }

                                if(com == 4) {
                                    game.playerRed.setWin(true);
                                    game.checkGameOver();
                                }
                                else {
                                    game.SetNextTurn();
                                }

                            }
                            else
                                game.SetNextTurn();
                        }
                        else
                        {
                            game.playerRed.setIsPass();
                        }
                    }
                    break;
                }
            }
        }
        //abhi
        else if(game.t == LudoActivity.Turn.GREEN)
        {
            Piece[] pieces = game.playerGreen.getRedPiecePositions();
            for (int i = 0; i < pieces.length; i++)
            {
                Piece p = pieces[i];
                if(p.getMove())
                {
                    if(p.UpdatePosition())
                    {
                        boolean clear = true;
                        p.setMove(false);
                        game.isMoving = false;
                        game.toMove = false;
                        if(game.initRed) {
                            for (int j = 0; j < 4; j++)
                            {
                                Piece p1 = game.playerRed.getRedPiecePositions()[j];
                                if(p.getCollision().intersect(p1.getCollision()))
                                {
                                    if(!p.getIsStar(p.getTarget()))
                                    {
                                        p1.setHome(true);
                                        game.toHome = true;
                                        clear = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if(game.initBlue)
                        {
                            for (int j = 0; j < 4; j++)
                            {
                                Piece p1 = game.playerBlue.getRedPiecePositions()[j];
                                if(p.getCollision().intersect(p1.getCollision()))
                                {
                                    if(!p.getIsStar(p.getTarget()))
                                    {
                                        p1.setHome(true);
                                        game.toHome = true;
                                        clear = false;
                                        break;
                                    }
                                }
                            }
                        }

                        if(game.initYellow)
                        {
                            for (int j = 0; j < 4; j++)
                            {
                                Piece p1 = game.playerYellow.getRedPiecePositions()[j];
                                if(p.getCollision().intersect(p1.getCollision()))
                                {
                                    if(!p.getIsStar(p.getTarget()))
                                    {
                                        p1.setHome(true);
                                        game.toHome = true;
                                        clear = false;
                                        break;
                                    }
                                }
                            }
                        }

                        if(clear) {
                            if(p.getComplete())
                            {
                                Log.d("LudoActivity","Piece Complete");
                                int com = 0;
                                for (int j = 0; j < 4; j++) {
                                    if(pieces[j].getComplete())
                                        com++;
                                }

                                if(com == 4) {
                                    game.playerGreen.setWin(true);
                                    game.checkGameOver();
                                }
                                else {
                                    game.SetNextTurn();
                                }

                            }
                            else
                                game.SetNextTurn();
                        }
                        else
                        {
                            game.playerGreen.setIsPass();
                        }
                    }
                    break;
                }
            }
        }

        else if(game.t == LudoActivity.Turn.BLUE)
        {
            Piece[] pieces = game.playerBlue.getRedPiecePositions();
            for (int i = 0; i < pieces.length; i++)
            {
                Piece p = pieces[i];
                if(p.getMove())
                {
                    if(p.UpdatePosition())
                    {
                        boolean clear = true;
                        p.setMove(false);
                        game.isMoving = false;
                        game.toMove = false;
                        if(game.initRed) {
                            for (int j = 0; j < 4; j++)
                            {
                                Piece p1 = game.playerRed.getRedPiecePositions()[j];
                                if(p.getCollision().intersect(p1.getCollision()))
                                {
                                    if(!p.getIsStar(p.getTarget()))
                                    {
                                        p1.setHome(true);
                                        game.toHome = true;
                                        clear = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if(game.initGreen)
                        {
                            for (int j = 0; j < 4; j++)
                            {
                                Piece p1 = game.playerGreen.getRedPiecePositions()[j];
                                if(p.getCollision().intersect(p1.getCollision()))
                                {
                                    if(!p.getIsStar(p.getTarget()))
                                    {
                                        p1.setHome(true);
                                        game.toHome = true;
                                        clear = false;
                                        break;
                                    }
                                }
                            }
                        }

                        if(game.initYellow)
                        {
                            for (int j = 0; j < 4; j++)
                            {
                                Piece p1 = game.playerYellow.getRedPiecePositions()[j];
                                if(p.getCollision().intersect(p1.getCollision()))
                                {
                                    if(!p.getIsStar(p.getTarget()))
                                    {
                                        p1.setHome(true);
                                        game.toHome = true;
                                        clear = false;
                                        break;
                                    }
                                }
                            }
                        }

                        if(clear) {
                            if(p.getComplete())
                            {
                                Log.d("LudoActivity","Piece Complete");
                                int com = 0;
                                for (int j = 0; j < 4; j++) {
                                    if(pieces[j].getComplete())
                                        com++;
                                }

                                if(com == 4) {
                                    game.playerBlue.setWin(true);
                                    game.checkGameOver();
                                }
                                else {
                                    game.SetNextTurn();
                                }

                            }
                            else
                                game.SetNextTurn();
                        }
                        else
                        {
                            game.playerBlue.setIsPass();
                        }
                    }
                    break;
                }
            }
        }

        else if(game.t == LudoActivity.Turn.YELLOW)
        {
            Piece[] pieces = game.playerYellow.getRedPiecePositions();
            for (int i = 0; i < pieces.length; i++)
            {
                Piece p = pieces[i];
                if(p.getMove())
                {
                    if(p.UpdatePosition())
                    {
                        p.setMove(false);
                        boolean clear = true;
                        game.toMove = false;
                        game.isMoving = false;

                        if(game.initRed) {
                            for (int j = 0; j < 4; j++)
                            {
                                Piece p1 = game.playerRed.getRedPiecePositions()[j];
                                if(p.getCollision().intersect(p1.getCollision()))
                                {
                                    if(!p.getIsStar(p.getTarget()))
                                    {
                                        p1.setHome(true);
                                        game.toHome = true;
                                        clear = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if(game.initBlue)
                        {
                            for (int j = 0; j < 4; j++)
                            {
                                Piece p1 = game.playerBlue.getRedPiecePositions()[j];
                                if(p.getCollision().intersect(p1.getCollision()))
                                {
                                    if(!p.getIsStar(p.getTarget()))
                                    {
                                        p1.setHome(true);
                                        game.toHome = true;
                                        clear = false;
                                        break;
                                    }
                                }
                            }
                        }

                        if(game.initGreen)
                        {
                            for (int j = 0; j < 4; j++)
                            {
                                Piece p1 = game.playerGreen.getRedPiecePositions()[j];
                                if(p.getCollision().intersect(p1.getCollision()))
                                {
                                    if(!p.getIsStar(p.getTarget()))
                                    {
                                        p1.setHome(true);
                                        game.toHome = true;
                                        clear = false;
                                        break;
                                    }
                                }
                            }
                        }

                        if(clear) {
                            if(p.getComplete())
                            {
                                Log.d("LudoActivity","Piece Complete");
                                int com = 0;
                                for (int j = 0; j < 4; j++) {
                                    if(pieces[j].getComplete())
                                        com++;
                                }

                                if(com == 4) {
                                    game.playerYellow.setWin(true);
                                    game.checkGameOver();
                                }
                                else {
                                    game.SetNextTurn();
                                }

                            }
                            else
                                game.SetNextTurn();
                        }
                        else
                        {
                            game.playerYellow.setIsPass();
                        }
                    }
                    break;
                }
            }
        }
    }
}
