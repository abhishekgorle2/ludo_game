package com.tdevelopments.ludo_game.Characters.Ludo.ui;

import static com.tdevelopments.ludo_game.LudoActivity.romPath;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.Log;

import com.tdevelopments.ludo_game.Characters.Ludo.Player.Player;
import com.tdevelopments.ludo_game.Characters.PathPostion;
import com.tdevelopments.ludo_game.Characters.Position;
import com.tdevelopments.ludo_game.LudoActivity;

public class MapMaker {
    public static void generateMaper(LudoActivity.LudoGameView game){
        game.bmpMap = Bitmap.createBitmap(game.ScreenWidth,game.ScreenHeight, Bitmap.Config.ARGB_8888);
        Canvas map = new Canvas(game.bmpMap);

        float r = 40.0f;
        int blockWidthHight = (int) (game.ScreenHeight * (r / 100f));
        int left = (game.ScreenWidth / 2) - (game.ScreenHeight / 2);
        Log.d("LudoActivity", "block height " + blockWidthHight);

        game.paint.setColor(Color.BLACK);
        map.drawRect(left - 2 , 0, blockWidthHight + left - 2, blockWidthHight, game.paint);
        game.paint.setColor(Color.RED);
        map.drawRect(left, 2, blockWidthHight - 2 + left, blockWidthHight - 2, game.paint);

        int redBorderWidth = (int) ((float) blockWidthHight * (20f / 100f));
        Log.d("LudoActivity", "redBorder height " + redBorderWidth);

        game.paint.setColor(Color.BLACK);
        map.drawRect(left + redBorderWidth,
                redBorderWidth + 2,
                blockWidthHight - 2 + left - redBorderWidth,
                blockWidthHight - 2 - redBorderWidth, game.paint);

        game.paint.setColor(Color.WHITE);

        map.drawRect(left + redBorderWidth + 2,
                redBorderWidth + 4,
                blockWidthHight - 2 + left - redBorderWidth - 2,
                blockWidthHight - 4 - redBorderWidth, game.paint);

        int whiteAreaWidth = (blockWidthHight - redBorderWidth) - (redBorderWidth + 4);
        Log.d("LudoActivity", "white area " + whiteAreaWidth);

        int circleWidth = (int) ((float) whiteAreaWidth * (25f / 100f));
        game.CircleSize = circleWidth - 4;
        Log.d("LudoActivity", "circle width " + circleWidth);

        //RED Circle
        int leftX = (left + redBorderWidth + 2 + circleWidth - 2);
        int leftY = (redBorderWidth + 4 + circleWidth - 2);

        game.red = new Position[4];
        Position p = new Position(leftX, leftY);
        game.red[0] = p;

        game.paint.setColor(Color.BLACK);
        map.drawCircle((float) leftX, (float) leftY, circleWidth / 2, game.paint);

        game.paint.setColor(Color.RED);

        map.drawCircle((float) leftX, (float) leftY, (circleWidth - 4) / 2, game.paint);

        game.paint.setColor(Color.BLACK);
        int rightX = left + blockWidthHight - redBorderWidth - 2 - circleWidth;
        int rightY = redBorderWidth + 4 + circleWidth - 2;

        p = new Position(rightX, rightY);
        game.red[1] = p;
        map.drawCircle((float) rightX,
                (float) rightY, circleWidth / 2, game.paint);

        game.paint.setColor(Color.RED);

        map.drawCircle((float) rightX,
                (float) rightY, (circleWidth - 4) / 2, game.paint);

        game.paint.setColor(Color.BLACK);

        int bottomLeftY = blockWidthHight - redBorderWidth - 2 - circleWidth;

        p = new Position(leftX, bottomLeftY);
        game.red[2] = p;
        map.drawCircle((float) leftX, (float) bottomLeftY, circleWidth / 2, game.paint);

        game.paint.setColor(Color.RED);

        map.drawCircle((float) leftX, (float) bottomLeftY, (circleWidth - 4) / 2, game.paint);

        p = new Position(rightX, bottomLeftY);
        game.red[3] = p;
        game.paint.setColor(Color.BLACK);
        map.drawCircle((float) rightX, (float) bottomLeftY, circleWidth / 2, game.paint);
        game.paint.setColor(Color.RED);
        map.drawCircle((float) rightX, (float) bottomLeftY, (circleWidth - 4) / 2, game.paint);

        //Bottom Part(Yellow)

        int topY = game.ScreenHeight - blockWidthHight;
        game.paint.setColor(Color.BLACK);
        map.drawRect(left - 2, topY, blockWidthHight + left - 2, game.ScreenHeight, game.paint);
        game.paint.setColor(Color.YELLOW);
        map.drawRect(left, topY, blockWidthHight - 2 + left, game.ScreenHeight - 2, game.paint);
        Log.d("LudoActivity", "redBorder height " + redBorderWidth);

        game.paint.setColor(Color.BLACK);
        map.drawRect(left + redBorderWidth,
                topY + redBorderWidth + 2,
                blockWidthHight - 2 + left - redBorderWidth,
                game.ScreenHeight - 2 - redBorderWidth, game.paint);

        game.paint.setColor(Color.WHITE);

        map.drawRect(left + redBorderWidth + 2,
                topY + redBorderWidth + 4,
                blockWidthHight - 2 + left - redBorderWidth - 2,
                game.ScreenHeight - 4 - redBorderWidth, game.paint);

        //YELLOW Circle
        leftX = (left + redBorderWidth + circleWidth);
        leftY = (topY + redBorderWidth + 4 + circleWidth - 2);

        game.yellow = new Position[4];
        p = new Position(leftX, leftY);
        game.yellow[0] = p;

        game.paint.setColor(Color.BLACK);
        map.drawCircle((float) leftX, (float) leftY, circleWidth / 2, game.paint);

        game.paint.setColor(Color.YELLOW);

        map.drawCircle((float) leftX, (float) leftY, (circleWidth - 4) / 2, game.paint);

        game.paint.setColor(Color.BLACK);
        rightX = left + blockWidthHight - redBorderWidth - 2 - circleWidth;
        rightY = topY + redBorderWidth + 4 + circleWidth - 2;

        p = new Position(rightX, rightY);
        game.yellow[1] = p;
        map.drawCircle((float) rightX,
                (float) rightY, circleWidth / 2, game.paint);

        game.paint.setColor(Color.YELLOW);

        map.drawCircle((float) rightX,
                (float) rightY, (circleWidth - 4) / 2, game.paint);

        game.paint.setColor(Color.BLACK);

        bottomLeftY = game.ScreenHeight - redBorderWidth - 2 - circleWidth;

        p = new Position(leftX, bottomLeftY);
        game.yellow[2] = p;
        map.drawCircle((float) leftX, (float) bottomLeftY, circleWidth / 2, game.paint);

        game.paint.setColor(Color.YELLOW);

        map.drawCircle((float) leftX, (float) bottomLeftY, (circleWidth - 4) / 2, game.paint);

        p = new Position(rightX, bottomLeftY);
        game.yellow[3] = p;
        game.paint.setColor(Color.BLACK);
        map.drawCircle((float) rightX, (float) bottomLeftY, circleWidth / 2, game.paint);
        game.paint.setColor(Color.YELLOW);
        map.drawCircle((float) rightX, (float) bottomLeftY, (circleWidth - 4) / 2, game.paint);


        //Top right (GREEN)

        int topRightX = game.ScreenHeight - blockWidthHight + left;
        game.paint.setColor(Color.BLACK);
        map.drawRect(topRightX - 2, 0, game.ScreenHeight + left, blockWidthHight, game.paint);
        game.paint.setColor(Color.GREEN);
        map.drawRect(topRightX + 2, 2, game.ScreenHeight + left - 2, blockWidthHight - 2, game.paint);

        game.paint.setColor(Color.BLACK);
        map.drawRect(topRightX + redBorderWidth,
                redBorderWidth + 2,
                left + game.ScreenHeight - redBorderWidth,
                blockWidthHight - 2 - redBorderWidth, game.paint);

        game.paint.setColor(Color.WHITE);

        map.drawRect(topRightX + redBorderWidth + 2,
                redBorderWidth + 4,
                left + game.ScreenHeight - redBorderWidth - 2,
                blockWidthHight - 4 - redBorderWidth, game.paint);


        //GREEN Circle
        leftX = (topRightX + redBorderWidth + 2 + circleWidth - 2);
        leftY = (redBorderWidth + 4 + circleWidth - 2);

        game.green = new Position[4];
        p = new Position(leftX, leftY);
        game.green[0] = p;

        game.paint.setColor(Color.BLACK);
        map.drawCircle((float) leftX, (float) leftY, circleWidth / 2, game.paint);

        game.paint.setColor(Color.GREEN);

        map.drawCircle((float) leftX, (float) leftY, (circleWidth - 4) / 2, game.paint);

        game.paint.setColor(Color.BLACK);
        rightX = topRightX + blockWidthHight - redBorderWidth - 2 - circleWidth;
        rightY = redBorderWidth + 4 + circleWidth - 2;

        p = new Position(rightX, rightY);
        game.green[1] = p;
        map.drawCircle((float) rightX,
                (float) rightY, circleWidth / 2, game.paint);

        game.paint.setColor(Color.GREEN);

        map.drawCircle((float) rightX,
                (float) rightY, (circleWidth - 4) / 2, game.paint);

        game.paint.setColor(Color.BLACK);

        bottomLeftY = blockWidthHight - redBorderWidth - 2 - circleWidth;

        p = new Position(leftX, bottomLeftY);
        game.green[2] = p;
        map.drawCircle((float) leftX, (float) bottomLeftY, circleWidth / 2, game.paint);

        game.paint.setColor(Color.GREEN);

        map.drawCircle((float) leftX, (float) bottomLeftY, (circleWidth - 4) / 2, game.paint);

        p = new Position(rightX, bottomLeftY);
        game.green[3] = p;
        game.paint.setColor(Color.BLACK);
        map.drawCircle((float) rightX, (float) bottomLeftY, circleWidth / 2, game.paint);
        game.paint.setColor(Color.GREEN);
        map.drawCircle((float) rightX, (float) bottomLeftY, (circleWidth - 4) / 2, game.paint);


        //BottomRight (Blue)

        game.paint.setColor(Color.BLACK);
        map.drawRect(topRightX - 2, topY, game.ScreenHeight + left, game.ScreenHeight, game.paint);
        game.paint.setColor(Color.BLUE);
        map.drawRect(topRightX + 2, topY - 2, game.ScreenHeight + left - 2, game.ScreenHeight - 2, game.paint);

        game.paint.setColor(Color.BLACK);
        map.drawRect(topRightX + redBorderWidth,
                topY + redBorderWidth + 2,
                left + game.ScreenHeight - redBorderWidth,
                game.ScreenHeight - 2 - redBorderWidth, game.paint);

        game.paint.setColor(Color.WHITE);

        map.drawRect(topRightX + redBorderWidth + 2,
                topY + redBorderWidth + 4,
                left + game.ScreenHeight - redBorderWidth - 2,
                game.ScreenHeight - 4 - redBorderWidth, game.paint);

        //BLUE Circle
        leftX = (topRightX + redBorderWidth + circleWidth);
        leftY = (topY + redBorderWidth + 4 + circleWidth - 2);

        game.blue = new Position[4];
        p = new Position(leftX, leftY);
        game.blue[0] = p;

        game.paint.setColor(Color.BLACK);
        map.drawCircle((float) leftX, (float) leftY, circleWidth / 2, game.paint);

        game.paint.setColor(Color.BLUE);

        map.drawCircle((float) leftX, (float) leftY, (circleWidth - 4) / 2, game.paint);

        game.paint.setColor(Color.BLACK);
        rightX = topRightX + blockWidthHight - redBorderWidth - 2 - circleWidth;
        rightY = topY + redBorderWidth + 4 + circleWidth - 2;

        p = new Position(rightX, rightY);
        game.blue[1] = p;
        map.drawCircle((float) rightX, (float) rightY, circleWidth / 2, game.paint);

        game.paint.setColor(Color.BLUE);

        map.drawCircle((float) rightX, (float) rightY, (circleWidth - 4) / 2, game.paint);

        game.paint.setColor(Color.BLACK);

        bottomLeftY = game.ScreenHeight - redBorderWidth - 2 - circleWidth;

        p = new Position(leftX, bottomLeftY);
        game.blue[2] = p;
        map.drawCircle((float) leftX, (float) bottomLeftY, circleWidth / 2, game.paint);

        game.paint.setColor(Color.BLUE);

        map.drawCircle((float) leftX, (float) bottomLeftY, (circleWidth - 4) / 2, game.paint);

        p = new Position(rightX, bottomLeftY);
        game.blue[3] = p;
        game.paint.setColor(Color.BLACK);
        map.drawCircle((float) rightX, (float) bottomLeftY, circleWidth / 2, game.paint);
        game.paint.setColor(Color.BLUE);
        map.drawCircle((float) rightX, (float) bottomLeftY, (circleWidth - 4) / 2, game.paint);

        //Draw Path Now

        int pathWidth = (int) ((float) game.ScreenHeight * (20f / 100f));
        Log.d("LudoActivity", "Path Width : " + pathWidth);
        int siglePathWidth = pathWidth / 3;

        Bitmap scaledBmp = Bitmap.createScaledBitmap(game.bmpStar, siglePathWidth, siglePathWidth, true);
        Bitmap scaledBmpWhite = Bitmap.createScaledBitmap(game.bmpStarWhite, siglePathWidth, siglePathWidth, true);


        //path between red and yellow
        game.redPath = new Position[6];
        for (int i = 0; i < 3; i++) {
            int pathY = blockWidthHight + (siglePathWidth * i);
            int pathX = left;
            for (int j = 0; j < 6; j++) {
                game.paint.setColor(Color.BLACK);
                map.drawRect(pathX - 2, pathY, pathX + siglePathWidth,
                        pathY + siglePathWidth, game.paint);

                if ((i == 0 && j == 1)) {
                    game.paint.setColor(Color.RED);
                    map.drawRect(pathX, pathY, pathX + siglePathWidth,
                            pathY + siglePathWidth - 2, game.paint);
                    map.drawBitmap(scaledBmpWhite, (float) pathX - 2, (float) pathY, null);

                } else if ((i == 1 && j > 0)) {
                    game.paint.setColor(Color.RED);
                    map.drawRect(pathX, pathY, pathX + siglePathWidth,
                            pathY + siglePathWidth - 2, game.paint);

                    if (j == 5)
                        game.redPath[5] = new Position(pathX + siglePathWidth, pathY);
                    p = new Position(pathX, pathY);
                    game.redPath[j - 1] = p;

                } else {
                    game.paint.setColor(Color.WHITE);
                    map.drawRect(pathX, pathY, pathX + siglePathWidth,
                            pathY + siglePathWidth - 2, game.paint);
                }

                if (i == 2 && j == 1)
                    map.drawBitmap(scaledBmp, pathX, pathY, null);

                switch (i) {
                    case 0:
                        if (j == 1) {
                            romPath[1] = new PathPostion(pathX, pathY, true);
                        } else {
                            romPath[j] = new PathPostion(pathX, pathY, false);
                        }
                        break;
                    case 1:
                        if (j == 0)
                            romPath[51] = new PathPostion(pathX, pathY, false);
                        break;
                    case 2:
                        if (j == 1)
                            romPath[50 - j] = new PathPostion(pathX, pathY, true);
                        else
                            romPath[50 - j] = new PathPostion(pathX, pathY, false);
                        break;
                }

                pathX += siglePathWidth;
            }
        }

        //path between green and red
        game.greenPath = new Position[6];
        for (int i = 0; i < 3; i++) {
            int pathY = 0; //blockWidthHight + (siglePathWidth * i);
            int pathX = left + blockWidthHight + (siglePathWidth * i);
            for (int j = 0; j < 6; j++) {
                game.paint.setColor(Color.BLACK);
                map.drawRect(pathX - 2, pathY, pathX + siglePathWidth, pathY + siglePathWidth, game.paint);

                if ((i == 1 && j > 0)) {
                    game.paint.setColor(Color.GREEN);
                    map.drawRect(pathX, pathY + 2, pathX + siglePathWidth,
                            pathY + siglePathWidth, game.paint);
                    if (j == 5)
                        game.greenPath[5] = new Position(pathX, pathY + siglePathWidth);

                    game.greenPath[j - 1] = new Position(pathX, pathY);

                } else if (i == 2 && j == 1) {
                    game.paint.setColor(Color.GREEN);
                    map.drawRect(pathX, pathY + 2, pathX + siglePathWidth,
                            pathY + siglePathWidth, game.paint);

                    map.drawBitmap(scaledBmpWhite, (float) pathX - 2, (float) pathY, null);
                } else {
                    game.paint.setColor(Color.WHITE);
                    map.drawRect(pathX, pathY + 2, pathX + siglePathWidth,
                            pathY + siglePathWidth, game.paint);
                }

                if (i == 0 && j == 1) {
                    map.drawBitmap(scaledBmp, pathX, pathY, null);
                }

                switch (i) {
                    case 0:
                        if (j == 1)
                            romPath[11 - j] = new PathPostion(pathX, pathY, true);
                        else
                            romPath[11 - j] = new PathPostion(pathX, pathY, false);
                        break;
                    case 1:
                        if (j == 0)
                            romPath[12] = new PathPostion(pathX, pathY, false);
                        break;
                    case 2:
                        if (j == 1)
                            romPath[13 + j] = new PathPostion(pathX, pathY, true);
                        else
                            romPath[13 + j] = new PathPostion(pathX, pathY, false);
                        break;
                }

                pathY += siglePathWidth;
            }
        }

        //path between green and blue
        game.bluePath = new Position[6];
        for (int i = 0; i < 3; i++) {
            int pathY = blockWidthHight + (siglePathWidth * i);
            int pathX = left + game.ScreenHeight - siglePathWidth;
            for (int j = 0; j < 6; j++) {
                game.paint.setColor(Color.BLACK);
                map.drawRect(pathX, pathY, pathX + siglePathWidth, pathY + siglePathWidth, game.paint);

                if ((i == 1 && j > 0)) {
                    game.paint.setColor(Color.BLUE);
                    map.drawRect(pathX, pathY, pathX + siglePathWidth - 2,
                            pathY + siglePathWidth - 2, game.paint);
                    p = new Position(pathX, pathY);
                    game.bluePath[j - 1] = p;

                    if (j == 5)
                        game.bluePath[5] = new Position(pathX - siglePathWidth, pathY);
                } else if (i == 2 && j == 1) {
                    game.paint.setColor(Color.BLUE);
                    map.drawRect(pathX, pathY, pathX + siglePathWidth - 2,
                            pathY + siglePathWidth - 2, game.paint);
                    map.drawBitmap(scaledBmpWhite, (float) pathX - 2, (float) pathY, null);
                } else {
                    game.paint.setColor(Color.WHITE);
                    map.drawRect(pathX, pathY, pathX + siglePathWidth - 2,
                            pathY + siglePathWidth - 2, game.paint);
                }

                if (i == 0 && j == 1)
                    map.drawBitmap(scaledBmp, pathX, pathY, null);

                switch (i) {
                    case 0:
                        if (j == 1)
                            romPath[24 - j] = new PathPostion(pathX, pathY, true);
                        else
                            romPath[24 - j] = new PathPostion(pathX, pathY, false);
                        break;
                    case 1:
                        if (j == 0)
                            romPath[25] = new PathPostion(pathX, pathY, false);
                        break;
                    case 2:
                        if (j == 1)
                            romPath[26 + j] = new PathPostion(pathX, pathY, true);
                        else
                            romPath[26 + j] = new PathPostion(pathX, pathY, false);
                        break;
                }
                pathX -= siglePathWidth;
            }
        }

        //path between yellow and blue
        game.yellowPath = new Position[6];
        for (int i = 0; i < 3; i++) {
            int pathY = game.ScreenHeight - blockWidthHight; //blockWidthHight + (siglePathWidth * i);
            int pathX = left + blockWidthHight + (siglePathWidth * i);
            for (int j = 0; j < 6; j++) {
                game.paint.setColor(Color.BLACK);
                map.drawRect(pathX - 2, pathY, pathX + siglePathWidth, pathY + siglePathWidth, game.paint);

                if ((i == 0 && j == 4)) {
                    game.paint.setColor(Color.YELLOW);
                    map.drawRect(pathX, pathY, pathX + siglePathWidth,
                            pathY + siglePathWidth - 2, game.paint);
                    map.drawBitmap(scaledBmpWhite, (float) pathX - 2, (float) pathY, null);

                } else if (i == 1 && j < 5) {
                    Log.d("LudoActivity","In Yellow");
                    Log.d("LudoActivity","i is " + i + " & j is " + j);
                    game.paint.setColor(Color.YELLOW);
                    map.drawRect(pathX, pathY, pathX + siglePathWidth,
                            pathY + siglePathWidth - 2, game.paint);
                    if (j == 0) {
                        game.yellowPath[5] = new Position(pathX, pathY - siglePathWidth);
                    }

                    game.yellowPath[4 - j] = new Position(pathX, pathY);
                } else {
                    game.paint.setColor(Color.WHITE);
                    map.drawRect(pathX, pathY, pathX + siglePathWidth,
                            pathY + siglePathWidth - 2, game.paint);
                }

                if (i == 2 && j == 4)
                    map.drawBitmap(scaledBmp, pathX, pathY, null);

                switch (i) {
                    case 0:
                        if (j == 4)
                            romPath[44 - j] = new PathPostion(pathX, pathY, true);
                        else
                            romPath[44 - j] = new PathPostion(pathX, pathY, false);
                        break;
                    case 1:
                        if (j == 5)
                            romPath[38] = new PathPostion(pathX, pathY, false);
                        break;
                    case 2:
                        if (j == 4)
                            romPath[32 + j] = new PathPostion(pathX, pathY, true);
                        else
                            romPath[32 + j] = new PathPostion(pathX, pathY, false);
                }
                pathY += siglePathWidth;
            }
        }

        for (int i = 0; i < game.greenPath.length; i++)
        {
            Log.d("LudoActivity","Green path " + i + " : " + game.greenPath[i].toString());
        }

        for (int i = 0; i < game.yellowPath.length; i++)
        {
            if(game.yellowPath[i] == null)
                Log.d("LudoActivity","Yellow path at " + i + " is null");
            else
                Log.d("LudoActivity","Yellow path " + i + " : " + game.yellowPath[i].toString());
        }

        if(game.initRed) {
            if (game.playerRed == null) {
                game.playerRed = new Player(1, 50, game.red, siglePathWidth,game.redPath,game.redPieceColor);
                game.playerRed.setArrows(new int[]{game.red[0].getX(),game.red[1].getX(),game.red[2].getX(),game.red[3].getX()},
                        new int[]{game.red[0].getY(),game.red[1].getY(),game.red[2].getY(),game.red[3].getY()},game.CircleSize);
            }
        }

        if(game.initYellow)
        {
            if(game.playerYellow == null) {
                Log.d("LudoActivity","Yellow Path");
                game.playerYellow = new Player(40, 38, game.yellow, siglePathWidth,game.yellowPath,game.yellowPieceColor);
                game.playerYellow.setArrows(new int[]{game.yellow[0].getX(),game.yellow[1].getX(),game.yellow[2].getX(),game.yellow[3].getX()},
                        new int[]{game.yellow[0].getY(),game.yellow[1].getY(),game.yellow[2].getY(),game.yellow[3].getY()},game.CircleSize);
            }
        }

        if(game.initGreen)
        {
            if(game.playerGreen == null) {
                game.playerGreen = new Player(14, 12, game.green, siglePathWidth,game.greenPath,game.greenPieceColor);
                game.playerGreen.setArrows(new int[]{game.green[0].getX(),game.green[1].getX(),game.green[2].getX(),game.green[3].getX()},
                        new int[]{game.green[0].getY(),game.green[1].getY(),game.green[2].getY(),game.green[3].getY()},game.CircleSize);
            }
        }

        if(game.initBlue)
        {
            if(game.playerBlue == null) {
                game.playerBlue = new Player(27, 25, game.blue, siglePathWidth,game.bluePath,game.bluePieceColor);
                game.playerBlue.setArrows(new int[]{game.blue[0].getX(),game.blue[1].getX(),game.blue[2].getX(),game.blue[3].getX()},
                        new int[]{game.blue[0].getY(),game.blue[1].getY(),game.blue[2].getY(),game.blue[3].getY()},game.CircleSize);
            }
        }

        game.paint.setColor(Color.BLACK);
        map.drawRect(left + blockWidthHight, blockWidthHight,
                left + blockWidthHight + pathWidth, blockWidthHight + pathWidth, game.paint);
//                        game.paint.setColor(Color.RED);
//                        map.drawRect(50 + blockWidthHight+2,blockWidthHight+2,
//                        50 + blockWidthHight + pathWidth-2,blockWidthHight + pathWidth-2,game.paint);

        game.paint.setColor(Color.RED);
        Path path = new Path();
        path.moveTo(left + blockWidthHight + 2, blockWidthHight + 2);
        path.lineTo(left + blockWidthHight + 2, blockWidthHight + pathWidth - 2);
        path.lineTo(left + blockWidthHight + (pathWidth / 2), blockWidthHight + (pathWidth / 2));
        path.lineTo(left + blockWidthHight + 2, blockWidthHight + 2);
        path.close();
        map.drawPath(path, game.paint);

        game.paint.setColor(Color.BLUE);

        path = new Path();
        path.moveTo(left + blockWidthHight + pathWidth - 2, blockWidthHight + 2);
        path.lineTo(left + blockWidthHight + pathWidth - 2, blockWidthHight + pathWidth - 2);
        path.lineTo(left + blockWidthHight + (pathWidth / 2), blockWidthHight + (pathWidth / 2));
        path.lineTo(left + blockWidthHight + pathWidth - 2, blockWidthHight + 2);
        path.close();

        map.drawPath(path, game.paint);

        game.paint.setColor(Color.GREEN);
        path = new Path();
        path.moveTo(left + blockWidthHight + 2, blockWidthHight + 2);
        path.lineTo(left + blockWidthHight + pathWidth - 2, blockWidthHight + 2);
        path.lineTo(left + blockWidthHight + (pathWidth / 2), blockWidthHight + (pathWidth / 2));
        path.lineTo(left + blockWidthHight + 2, blockWidthHight + 2);
        path.close();

        map.drawPath(path, game.paint);

        game.paint.setColor(Color.YELLOW);
        path = new Path();
        path.moveTo(left + blockWidthHight + 2, blockWidthHight + pathWidth - 2);
        path.lineTo(left + blockWidthHight + pathWidth - 2, blockWidthHight + pathWidth - 2);
        path.lineTo(left + blockWidthHight + (pathWidth / 2), blockWidthHight + (pathWidth / 2));
        path.lineTo(left + blockWidthHight + 2, blockWidthHight + pathWidth - 2);
        path.close();

        map.drawPath(path, game.paint);
        map.save();

        //Generating pieces with Colors
        game.bmpRedPiece =  Bitmap.createBitmap(siglePathWidth,siglePathWidth, Bitmap.Config.ARGB_8888);

        Canvas redPiece = new Canvas(game.bmpRedPiece);
        //redPiece.drawColor(Color.GRAY);
        int pieceWidth = (int)(siglePathWidth * (80f/100f));
        int pieceHeight = siglePathWidth;

        int x = siglePathWidth /2 - (pieceWidth / 2);
        int y = 0;

        game.paint.setColor(Color.parseColor(game.redPieceColor));

        redPiece.drawCircle(siglePathWidth /2,pieceWidth/2,pieceWidth / 2,game.paint);
        path = new Path();
        path.moveTo(x,pieceWidth/2);
        path.lineTo(siglePathWidth /2,pieceHeight);
        path.lineTo(x+pieceWidth,pieceWidth/2);
        path.close();
        redPiece.drawPath(path,game.paint);
        game.paint.setColor(Color.WHITE);
        redPiece.drawCircle(siglePathWidth /2,pieceWidth/2,10,game.paint);
        redPiece.save();

        game.bmpBluePiece = Bitmap.createBitmap(siglePathWidth,siglePathWidth, Bitmap.Config.ARGB_8888);

        Canvas bluePiece = new Canvas(game.bmpBluePiece);
        game.paint.setColor(Color.parseColor(game.bluePieceColor));
        bluePiece.drawCircle(siglePathWidth /2,pieceWidth/2,pieceWidth / 2,game.paint);
        bluePiece.drawPath(path,game.paint);
        game.paint.setColor(Color.WHITE);
        bluePiece.drawCircle(siglePathWidth /2,pieceWidth/2,10,game.paint);
        bluePiece.save();

        game.bmpYellowPiece = Bitmap.createBitmap(siglePathWidth,siglePathWidth, Bitmap.Config.ARGB_8888);

        Canvas yellowPiece = new Canvas(game.bmpYellowPiece);
        game.paint.setColor(Color.parseColor(game.yellowPieceColor));
        yellowPiece.drawCircle(siglePathWidth /2,pieceWidth/2,pieceWidth / 2,game.paint);
        yellowPiece.drawPath(path,game.paint);
        game.paint.setColor(Color.WHITE);
        yellowPiece.drawCircle(siglePathWidth /2,pieceWidth/2,10,game.paint);
        yellowPiece.save();

        game.bmpGreenPiece = Bitmap.createBitmap(siglePathWidth,siglePathWidth, Bitmap.Config.ARGB_8888);

        Canvas greenPiece = new Canvas(game.bmpGreenPiece);
        game.paint.setColor(Color.parseColor(game.greenPieceColor));
        greenPiece.drawCircle(siglePathWidth /2,pieceWidth/2,pieceWidth / 2,game.paint);
        greenPiece.drawPath(path,game.paint);
        game.paint.setColor(Color.WHITE);
        greenPiece.drawCircle(siglePathWidth /2,pieceWidth/2,10,game.paint);
        greenPiece.save();
    }
}
