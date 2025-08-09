package com.tdevelopments.ludo_game.Characters.Ludo.ui;

import com.tdevelopments.ludo_game.LudoActivity;

public class NextTurn {

    public static void setNextTurn(LudoActivity.LudoGameView game) {

        if (!game.gameFinished) {
            if (game.placeToMove == 6) {
                game.toSuffle = true;
                game.placeToMove = 0;
                return;
            }
            game.placeToMove = 0;

            if (game.t == LudoActivity.Turn.RED) {
                if (game.playerGreen != null && !game.playerGreen.getWin()) {
                    game.t = LudoActivity.Turn.GREEN;
                    game.toSuffle = true;
                } else if (game.playerBlue != null && !game.playerBlue.getWin()) {
                    game.t = LudoActivity.Turn.BLUE;
                    game.toSuffle = true;
                } else if (game.playerYellow != null && !game.playerYellow.getWin()) {
                    game.t = LudoActivity.Turn.YELLOW;
                    game.toSuffle = true;
                }
            } else if (game.t == LudoActivity.Turn.GREEN) {
                if (game.playerBlue != null && !game.playerBlue.getWin()) { // fixed bug: was checking playerGreen
                    game.t = LudoActivity.Turn.BLUE;
                    game.toSuffle = true;
                } else if (game.playerYellow != null && !game.playerYellow.getWin()) {
                    game.t = LudoActivity.Turn.YELLOW;
                    game.toSuffle = true;
                } else if (game.playerRed != null && !game.playerRed.getWin()) {
                    game.t = LudoActivity.Turn.RED;
                    game.toSuffle = true;
                }
            } else if (game.t == LudoActivity.Turn.BLUE) {
                if (game.playerYellow != null && !game.playerYellow.getWin()) {
                    game.t = LudoActivity.Turn.YELLOW;
                    game.toSuffle = true;
                } else if (game.playerRed != null && !game.playerRed.getWin()) {
                    game.t = LudoActivity.Turn.RED;
                    game.toSuffle = true;
                } else if (game.playerGreen != null && !game.playerGreen.getWin()) {
                    game.t = LudoActivity.Turn.GREEN;
                    game.toSuffle = true;
                }
            } else if (game.t == LudoActivity.Turn.YELLOW) {
                if (game.playerRed != null && !game.playerRed.getWin()) {
                    game.t = LudoActivity.Turn.RED;
                    game.toSuffle = true;
                } else if (game.playerGreen != null && !game.playerGreen.getWin()) {
                    game.t = LudoActivity.Turn.GREEN;
                    game.toSuffle = true;
                } else if (game.playerBlue != null && !game.playerBlue.getWin()) {
                    game.t = LudoActivity.Turn.BLUE;
                    game.toSuffle = true;
                }
            }
        }
    }
}
