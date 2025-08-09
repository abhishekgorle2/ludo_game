package com.tdevelopments.ludo_game.Characters.Ludo.ui;

import com.tdevelopments.ludo_game.Characters.Ludo.Piece.Piece;
import com.tdevelopments.ludo_game.LudoActivity;

public class HomeMove {
    public static void moveHome(LudoActivity.LudoGameView game){

        if(game.initRed) {
            Piece[] pieces = game.playerRed.getRedPiecePositions();
            for (int i = 0; i < 4; i++) {
                Piece p = pieces[i];
                if (p.getHome()) {
                    if (p.UpdateToHome()) {
                        game.toHome = false;
                        game.SetNextTurn();
                    }
                    return;
                }
            }
        }

        if(game.initBlue) {
            Piece[] pieces = game.playerBlue.getRedPiecePositions();
            for (int i = 0; i < 4; i++) {
                Piece p = pieces[i];
                if (p.getHome()) {
                    if (p.UpdateToHome()) {
                        game.toHome = false;
                        game.SetNextTurn();
                    }
                    return;
                }
            }
        }

        if(game.initGreen) {
            Piece[] pieces = game.playerGreen.getRedPiecePositions();
            for (int i = 0; i < 4; i++) {
                Piece p = pieces[i];
                if (p.getHome()) {
                    if (p.UpdateToHome()) {
                        game.toHome = false;
                        game.SetNextTurn();
                    }
                    return;
                }
            }
        }

        if(game.initYellow) {
            Piece[] pieces = game.playerYellow.getRedPiecePositions();
            for (int i = 0; i < 4; i++) {
                Piece p = pieces[i];
                if (p.getHome()) {
                    if (p.UpdateToHome()) {
                        game.toHome = false;
                        game.SetNextTurn();
                    }
                    return;
                }
            }
        }
    }
}
