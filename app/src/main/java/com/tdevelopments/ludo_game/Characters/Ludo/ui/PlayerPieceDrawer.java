package com.tdevelopments.ludo_game.Characters.Ludo.ui;

import android.graphics.Color;
import com.tdevelopments.ludo_game.Characters.Ludo.Piece.Piece;
import com.tdevelopments.ludo_game.LudoActivity;

public class PlayerPieceDrawer {
    public static void drawPice(LudoActivity.LudoGameView game) {

            if(game.playerRed != null)
            {
                Piece[] pieces = game.playerRed.getRedPiecePositions();
                for (int i = 0; i < pieces.length; i++)
                {
                    Piece p = pieces[i];
                    game.paint.setColor(Color.parseColor(game.redPieceColor));
                    game.canvas.drawBitmap(game.bmpRedPiece,p.getX()-game.bmpRedPiece.getWidth()/2,p.getY()-game.bmpRedPiece.getHeight(),null);
                }

                if(game.t == LudoActivity.Turn.RED)
                {
                    if(game.placeToMove == 0) {
                        game.getNextNo(6);
                    }
                    else {
                        game.getNextNo(game.placeToMove);
                    }
                }
            }

            if(game.playerYellow != null)
            {
                Piece[] pieces = game.playerYellow.getRedPiecePositions();
                for (int i = 0; i < pieces.length; i++)
                {
                    Piece p = pieces[i];
                    game.paint.setColor(Color.parseColor(game.yellowPieceColor));
                    game.canvas.drawBitmap(game.bmpYellowPiece,p.getX()-game.bmpYellowPiece.getWidth()/2,p.getY()-game.bmpYellowPiece.getHeight(),null);
                }

                if(game.t == LudoActivity.Turn.YELLOW)
                {
                    if(game.placeToMove == 0) {
                        game.getNextNo(6);
                    }
                    else {
                        game.getNextNo(game.placeToMove);
                    }
                }
            }

            if(game.playerBlue != null)
            {
                Piece[] pieces = game.playerBlue.getRedPiecePositions();
                for (int i = 0; i < pieces.length; i++)
                {
                    Piece p = pieces[i];
                    game.paint.setColor(Color.parseColor(game.bluePieceColor));
                    game.canvas.drawBitmap(game.bmpBluePiece,p.getX()-game.bmpBluePiece.getWidth()/2,p.getY()-game.bmpBluePiece.getHeight(),null);
                }

                if(game.t == LudoActivity.Turn.BLUE)
                {
                    if(game.placeToMove == 0) {
                        game.getNextNo(6);
                    }
                    else {
                        game.getNextNo(game.placeToMove);
                    }
                }
            }

            if(game.playerGreen != null)
            {
                Piece[] pieces = game.playerGreen.getRedPiecePositions();
                for (int i = 0; i < game.green.length; i++)
                {
                    Piece p = pieces[i];
                    game.paint.setColor(Color.parseColor(game.greenPieceColor));
                    game.canvas.drawBitmap(game.bmpGreenPiece,p.getX()-game.bmpGreenPiece.getWidth()/2,p.getY()-game.bmpGreenPiece.getHeight(),null);
                }

                if(game.t == LudoActivity.Turn.GREEN)
                {
                    if(game.placeToMove == 0) {
                        game.getNextNo(6);
                    }
                    else {
                        game.getNextNo(game.placeToMove);
                    }
                }
            }

            if(game.toSuffle == false && game.toMove == false && game.toHome == false && game.nextDrawTime > 0)
                game.nextDrawTime--;

            if(game.toSuffle == false && game.toMove == false && game.toHome == false && game.nextDrawTime <= 0) {
                game.SetNextTurn();
            }
    }
}
