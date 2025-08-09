package com.tdevelopments.ludo_game.Characters.Ludo.ui;

import com.tdevelopments.ludo_game.Characters.Ludo.Piece.Piece;
import com.tdevelopments.ludo_game.Characters.Ludo.Player.Player;
import com.tdevelopments.ludo_game.LudoActivity;

public class MoveChecker {

    public static void checkCanMoveForPlayer(LudoActivity.LudoGameView game) {
        LudoActivity.Turn turn = game.t;
        Player currentPlayer = null;

        switch (turn) {
            case RED:
                currentPlayer = game.playerRed;
                break;
            case BLUE:
                currentPlayer = game.playerBlue;
                break;
            case GREEN:
                currentPlayer = game.playerGreen;
                break;
            case YELLOW:
                currentPlayer = game.playerYellow;
                break;
        }

        if (currentPlayer == null) return;

        Piece[] pieces = currentPlayer.getRedPiecePositions();
        int moveCount = 0;
        int id = 0;
        game.toMove = false;

        for (int i = 0; i < pieces.length; i++) {
            if ((!pieces[i].getIsKilled() || game.placeToMove == 6) &&
                    (!pieces[i].getComplete() && currentPlayer.checkCanMove(i, game.placeToMove))) {
                game.toMove = true;
                moveCount++;
                id = i;
            }
        }

        if (!game.toMove) {
            game.nextDrawTime = 30;
        } else if (moveCount == 1) {
            game.isMoving = true;
            if (!pieces[id].getComplete()) {
                if (currentPlayer.checkCanMove(id, game.placeToMove)) {
                    game.isMoving = true;
                    pieces[id].setTarget(game.placeToMove);
                    pieces[id].setMove(true);
                } else {
                    game.SetNextTurn();
                }
            }
        }
    }
}

