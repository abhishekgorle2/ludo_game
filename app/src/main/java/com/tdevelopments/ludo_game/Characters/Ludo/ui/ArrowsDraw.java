package com.tdevelopments.ludo_game.Characters.Ludo.ui;

import android.graphics.Color;

import com.tdevelopments.ludo_game.Characters.Ludo.Piece.Piece;
import com.tdevelopments.ludo_game.LudoActivity;

public class ArrowsDraw {
    public static void arrow(LudoActivity.LudoGameView game){
        if(game.t == LudoActivity.Turn.RED)
        {
            if(game.placeToMove == 6) {
                Piece[] pieces = game.playerRed.getRedPiecePositions();
                for (int i = 0; i < pieces.length; i++) {
                    if (pieces[i].getIsKilled()) {
                        game.playerRed.getArrows(i).setY();
                        game.paint.setColor(Color.parseColor("#be5c46"));
                        game.canvas.drawPath(game.playerRed.getArrows(i).getPath(), game.paint);
                    }
                }
            }
        }
        if(game.t == LudoActivity.Turn.GREEN)
        {
            if(game.placeToMove == 6) {
                Piece[] pieces = game.playerGreen.getRedPiecePositions();
                for (int i = 0; i < pieces.length; i++) {
                    if (pieces[i].getIsKilled()) {
                        game.playerGreen.getArrows(i).setY();
                        game.paint.setColor(Color.parseColor("#2f7d24"));
                        game.canvas.drawPath(game.playerGreen.getArrows(i).getPath(), game.paint);
                    }
                }
            }
        }
        if(game.t == LudoActivity.Turn.BLUE)
        {
            if(game.placeToMove == 6) {
                Piece[] pieces = game.playerBlue.getRedPiecePositions();
                for (int i = 0; i < pieces.length; i++) {
                    if (pieces[i].getIsKilled()) {
                        game.playerBlue.getArrows(i).setY();
                        game.paint.setColor(Color.parseColor("#051850"));;
                        game.canvas.drawPath(game.playerBlue.getArrows(i).getPath(), game.paint);
                    }
                }
            }
        }
        if(game.t == LudoActivity.Turn.YELLOW)
        {
            if(game.placeToMove == 6) {
                Piece[] pieces = game.playerYellow.getRedPiecePositions();
                for (int i = 0; i < pieces.length; i++) {
                    if (pieces[i].getIsKilled()) {
                        game.playerYellow.getArrows(i).setY();
                        game.paint.setColor(Color.parseColor("#9b942c"));
                        game.canvas.drawPath(game.playerYellow.getArrows(i).getPath(), game.paint);
                    }
                }
            }
        }
    }
}
