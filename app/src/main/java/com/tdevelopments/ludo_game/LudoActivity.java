package com.tdevelopments.ludo_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.tdevelopments.ludo_game.Characters.Ludo.Piece.Piece;
import com.tdevelopments.ludo_game.Characters.Ludo.Player.Player;
import com.tdevelopments.ludo_game.Characters.Ludo.ui.ArrowsDraw;
import com.tdevelopments.ludo_game.Characters.Ludo.ui.HomeMove;
import com.tdevelopments.ludo_game.Characters.Ludo.ui.MapMaker;
import com.tdevelopments.ludo_game.Characters.Ludo.ui.MoveChecker;
import com.tdevelopments.ludo_game.Characters.Ludo.ui.MovePiece;
import com.tdevelopments.ludo_game.Characters.Ludo.ui.NextTurn;
import com.tdevelopments.ludo_game.Characters.Ludo.ui.PlayerPieceDrawer;
import com.tdevelopments.ludo_game.Characters.PathPostion;
import com.tdevelopments.ludo_game.Characters.Position;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import fi.iki.elonen.NanoHTTPD;

public class LudoActivity extends AppCompatActivity implements MoveListener {
    public LudoGameView ludoGameView;

    public static PathPostion[] romPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        int playerCount = getIntent().getIntExtra("PLAYER_COUNT", 4); // default to 4

        PieceChoice[] choices;

        if (playerCount == 2) {
            choices = new PieceChoice[]{
                    new PieceChoice(1), // Red
                    new PieceChoice(3)  // Blue
            };
        } else if (playerCount == 3) {
            choices = new PieceChoice[]{
                    new PieceChoice(1), // Red
                    new PieceChoice(2), // Green
                    new PieceChoice(3)  // Blue
            };
        } else {
            choices = new PieceChoice[]{
                    new PieceChoice(1), // Red
                    new PieceChoice(2), // Green
                    new PieceChoice(3), // Blue
                    new PieceChoice(4)  // Yellow
            };
        }

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        ludoGameView = new LudoGameView(this, size.x, size.y, choices);
        setContentView(ludoGameView);
        Log.d("LudoServer", "Calling startServer...");

        MoveReceiver moveReceiver = new MoveReceiver("192.168.1.3", this);
        moveReceiver.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ludoGameView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ludoGameView.onPause();
    }

    public enum Turn{
        RED,
        GREEN,
        BLUE,
        YELLOW;
    }

    class PieceChoice{
        public int PlayerId;

        public PieceChoice(int id)
        {
            this.PlayerId = id;
        }
    }

    public class LudoGameView extends SurfaceView implements Runnable{
        public PlayerPieceDrawer playerPieceDrawer;
        public Context context;
        public Thread gameThread;
        public SurfaceHolder holder;
        public Canvas canvas;
        public Paint paint;

        public int ScreenWidth, ScreenHeight;
        public boolean isPlaying, gameFinished = false;

        public Position[] red,blue,yellow,green;

        public Position[] redPath, bluePath,yellowPath,greenPath;

        public int CircleSize;

        public Bitmap bmpStar,bmpStarWhite, bmpMap, bmpDice;

        public Bitmap bmpRedPiece,bmpGreenPiece,bmpYellowPiece,bmpBluePiece;

        public Player playerRed, playerBlue,playerGreen,playerYellow;

        public boolean initRed,initBlue,initGreen,initYellow,moveRed,moveGreen,moveYellow,moveBlue;
        public int placeToMove = 0;

        public String redPieceColor = "#710f01",
                bluePieceColor = "#515DDA",
                greenPieceColor = "#094702",
                yellowPieceColor = "#b5b00b";

        public Turn t;

        public int suffleDice = 30;
        public int nextDrawTime = 0,totalPlayers = 0;
        public boolean isShuffling = false, isMoving = false, toSuffle = false, toMove = false, isFactSet = false, toHome = false;

        public Rect rectRed,rectBlue,rectYellow,rectGreen;
        public String winText = "";
        String currentPlayerColor;
        Map<String, Integer> pendingMoves = new HashMap<>();

        public LudoGameView(Context context, AttributeSet attrs) {
            super(context, attrs);
            // Leave your initialization for later in an init() method
        }
        public void init(int screenX, int screenY, PieceChoice[] choices) {
            this.context = getContext();
            this.ScreenWidth = screenX;
            this.ScreenHeight = screenY;

            holder = getHolder();
            paint = new Paint();
            bmpStar = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_star_icon);
            bmpStarWhite = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_star_icon_white);
            bmpDice = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
            romPath = new PathPostion[52];
            moveRed = true;

            rectRed = new Rect(50, 50, 200, 200);
            rectBlue = new Rect(ScreenWidth - 200, ScreenHeight - 200, ScreenWidth - 50, ScreenHeight - 50);
            rectGreen = new Rect(ScreenWidth - 200, 50, ScreenWidth - 50, 200);
            rectYellow = new Rect(50, ScreenHeight - 200, 200, ScreenHeight - 50);

            totalPlayers = choices.length;

            for (int i = 0; i < choices.length; i++) {
                PieceChoice p = choices[i];
                if (p.PlayerId == 1)
                    initRed = true;
                else if (p.PlayerId == 2)
                    initGreen = true;
                else if (p.PlayerId == 3)
                    initBlue = true;
                else if (p.PlayerId == 4)
                    initYellow = true;

                if (i == 0) {
                    if (p.PlayerId == 1) {
                        t = Turn.RED;
                        currentPlayerColor = "red";
                    } else if (p.PlayerId == 2) {
                        t = Turn.GREEN;
                        currentPlayerColor = "green";
                    } else if (p.PlayerId == 3) {
                        t = Turn.BLUE;
                        currentPlayerColor = "blue";
                    } else if (p.PlayerId == 4) {
                        t = Turn.YELLOW;
                        currentPlayerColor = "yellow";
                    }
                }
            }
            toSuffle = true;
        }

        public LudoGameView(Context context, int screenX, int screenY, PieceChoice[] choices) {
            super(context);
            this.context = context;
            this.ScreenWidth = screenX;
            this.ScreenHeight = screenY;

            holder = getHolder();
            paint = new Paint();
            bmpStar = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_star_icon);
            bmpStarWhite = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_star_icon_white);
            bmpDice = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
            romPath = new PathPostion[52];
            moveRed = true;

            rectRed = new Rect(50,50,200,200);
            rectBlue = new Rect(ScreenWidth - 200, ScreenHeight - 200, ScreenWidth - 50, ScreenHeight - 50);
            rectGreen = new Rect(ScreenWidth - 200, 50, ScreenWidth - 50, 200);
            rectYellow = new Rect(50, ScreenHeight - 200,200,ScreenHeight - 50);

            totalPlayers = choices.length;

            for (int i = 0; i < choices.length; i++)
            {
                PieceChoice p = choices[i];
                Log.d("LudoActivity","choice : " + p.PlayerId);

                if(p.PlayerId == 1)
                    initRed = true;
                else if(p.PlayerId == 2)
                    initGreen = true;
                else if(p.PlayerId == 3)
                    initBlue = true;
                else if(p.PlayerId == 4)
                    initYellow = true;

                if(i == 0) {
                    if(p.PlayerId == 1) {
                        t = Turn.RED;
                        currentPlayerColor = "red";
                    }
                    else if(p.PlayerId == 2){
                        t = Turn.GREEN;
                        currentPlayerColor = "green";
                    }
                    else if(p.PlayerId == 3){
                        t = Turn.BLUE;
                        currentPlayerColor = "blue";
                    }
                    else if(p.PlayerId == 4){
                        t = Turn.YELLOW;
                        currentPlayerColor = "yellow";
                    }
                }
            }
            toSuffle = true;
        }

        @Override
        public void run() {
            while (isPlaying) {
                update();
                Draw();
                control();
            }
        }

        void update(){
            if(bmpMap == null) {
                generateMap();
            }
            if(isShuffling)
                SuffleDice();
            else if(isMoving) {
                MovePiece();
            }
            else if(toHome) {
                MoveToHome();
            }
        }

        void generateMap(){
            MapMaker.generateMaper(ludoGameView);
        }

        void Draw(){
            if(holder.getSurface().isValid()) {
                canvas = holder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                canvas.drawBitmap(bmpMap,0,0,null);
                if (t == Turn.RED)
                    canvas.drawBitmap(bmpDice, 50, 50, null);
                else if (t == Turn.GREEN)
                    canvas.drawBitmap(bmpDice, ScreenWidth - 200, 50, null);
                else if (t == Turn.BLUE)
                    canvas.drawBitmap(bmpDice, ScreenWidth - 200, ScreenHeight - 200, null);
                else
                    canvas.drawBitmap(bmpDice, 50, ScreenHeight - 200, null);
                DrawPlayerPiece();
                //canvas.drawBitmap(bmpRedPiece,ScreenWidth - 60,50,null);
                DrawArrows();
                if(gameFinished)
                {
                    paint.setTextSize(150);
                    paint.setTextAlign(Paint.Align.CENTER);
                    canvas.drawText(winText,canvas.getWidth() / 2,canvas.getHeight() / 2,paint);
                }
                holder.unlockCanvasAndPost(canvas);
            }
        }

        public void MoveToHome(){
            HomeMove.moveHome(ludoGameView);
        }
        public void MovePiece() {
            MovePiece.move(ludoGameView);
        }

        public void checkGameOver() {
            if(totalPlayers == 2) {
                if(t == Turn.RED) {
                    winText = "Player RED wins!!!";
                }
                else if(t == Turn.BLUE)
                    winText = "Player BLUE wins!!!";
                else if(t == Turn.GREEN)
                    winText = "Player GREEN wins!!!";
                else if(t == Turn.YELLOW)
                    winText = "Player YELLOW wins!!!";

            }
            else
            {
                int f = 0;
                int l = 0;

                if(initRed && playerRed.getWin())
                    f++;
                else
                    l = 1;

                if(initGreen && playerGreen.getWin())
                    f++;
                else
                    l = 2;

                if(initBlue && playerBlue.getWin())
                    f++;
                else
                    l = 3;

                if(initYellow && playerYellow.getWin())
                    f++;
                else
                    l = 4;

                if(f == totalPlayers - 1) {

                    switch (l)
                    {
                        case 1:
                            winText = "Player RED Lost";
                            break;
                        case 2:
                            winText = "Player GREEN Lost";
                            break;
                        case 3:
                            winText = "Player BLUE Lost";
                            break;
                        case 4:
                            winText = "Player YELLOW Lost";
                    }
                }

            }
            gameFinished = true;
        }

        public void SetNextTurn() {
            NextTurn.setNextTurn(this);
        }

        //setdice
        public void SuffleDice() {
            Random r = new Random();
            int next = r.nextInt(6) + 1;

            if (suffleDice <= 0) {
                suffleDice = 100;
                isShuffling = false;
                toSuffle = false;

                int diceValueToUse;

                // Convert turn enum to string
                String turnColor = null;
                if (t == Turn.RED) turnColor = "red";
                else if (t == Turn.GREEN) turnColor = "green";
                else if (t == Turn.BLUE) turnColor = "blue";
                else if (t == Turn.YELLOW) turnColor = "yellow";

                // Check if controller move is available for this turn
                if (turnColor != null && pendingMoves.containsKey(turnColor)) {
                    diceValueToUse = pendingMoves.get(turnColor);
                    Log.d("LudoGameView", turnColor + " controller value: " + diceValueToUse + " for color: " + turnColor);
                    pendingMoves.remove(turnColor); // Clear after using
                } else {
                    diceValueToUse = next;
                    Log.d("LudoGameView", turnColor + " random value: " + next);
                }

                placeToMove = getNextNo(diceValueToUse);
                Log.d("LudoGameView", "placeToMove set to: " + placeToMove);

                checkCanMove();
            } else {
                getNextNo(next); // animation
                suffleDice--;
                Log.d("LudoGameView", "Shuffling... count: " + suffleDice);
            }
        }
        public void checkCanMove() {
            MoveChecker.checkCanMoveForPlayer(this);
        }
        public int getNextNo(int next) {

            bmpDice = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bmpDice);
            if(t == Turn.RED)
                paint.setColor(Color.RED);
            else if(t == Turn.BLUE)
                paint.setColor(Color.BLUE);
            else if(t == Turn.GREEN)
                paint.setColor(Color.GREEN);
            else if (t == Turn.YELLOW)
                paint.setColor(Color.YELLOW);

            c.drawRoundRect(new RectF(0,0,100,100),6,6,paint);
            paint.setColor(Color.WHITE);

            switch (next)
            {
                case 1:
                    c.drawCircle(50,50,10,paint);
                    break;
                case 2:
                    c.drawCircle(20,20,10,paint);
                    c.drawCircle(80,80,10,paint);
                    break;
                case 3:
                    c.drawCircle(20,20,10,paint);
                    c.drawCircle(50,50,10,paint);
                    c.drawCircle(80,80,10,paint);
                    break;
                case 4:
                    c.drawCircle(30,30,10,paint);
                    c.drawCircle(70,70,10,paint);
                    c.drawCircle(30,70,10,paint);
                    c.drawCircle(70,30,10,paint);
                    break;
                case 5:
                    c.drawCircle(20,20,10,paint);
                    c.drawCircle(80,80,10,paint);
                    c.drawCircle(50,50,10,paint);
                    c.drawCircle(20,80,10,paint);
                    c.drawCircle(80,20,10,paint);
                    break;
                case 6:
                    c.drawCircle(20,20,10,paint);
                    c.drawCircle(20,50,10,paint);
                    c.drawCircle(20,80,10,paint);
                    c.drawCircle(80,20,10,paint);
                    c.drawCircle(80,80,10,paint);
                    c.drawCircle(80,50,10,paint);
                    break;
            }

            return next;
        }
        public void DrawPlayerPiece() {
            PlayerPieceDrawer.drawPice(ludoGameView);
        }
        public void DrawArrows(){
            ArrowsDraw.arrow(ludoGameView);
        }
        public void onResume() {
            isPlaying = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
        public void onPause() {
            isPlaying = false;
            try {
                gameThread.join();
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        void control(){
            try {
                gameThread.sleep(17);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {

            if(gameFinished == false) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_UP:
                        if (toSuffle) {
                            if (!toHome) {
                                if (t == Turn.RED) {
                                    if (rectRed.contains((int) event.getX(), (int) event.getY())) {
                                        isShuffling = true;
                                    }
                                } else if (t == Turn.YELLOW) {
                                    if (rectYellow.contains((int) event.getX(), (int) event.getY())) {
                                        isShuffling = true;
                                    }
                                } else if (t == Turn.GREEN) {
                                    if (rectGreen.contains((int) event.getX(), (int) event.getY())) {
                                        Log.d("LudoActivity", "Touch X : " + event.getX() + " Touch Y : " + event.getY());
                                        isShuffling = true;
                                    }
                                } else if (t == Turn.BLUE) {
                                    if (rectBlue.contains((int) event.getX(), (int) event.getY())) {
                                        isShuffling = true;
                                    }
                                }
                            }
                        } else if (toMove) {
                            if (t == Turn.RED) {
                                if (!isMoving) {
                                    Piece[] pieces = playerRed.getRedPiecePositions();

                                    for (int i = 0; i < pieces.length; i++) {
                                        {
                                            if (pieces[i].getCollision().contains((int) event.getX(), (int) event.getY())) {
                                                Log.d("LudoActivity", "On Piece");
                                                if (pieces[i].getIsKilled() == false || placeToMove == 6) {
                                                    Log.d("LudoActivity", "Click on Yellow");
                                                    if (pieces[i].getComplete() == false) {
                                                        if (playerRed.checkCanMove(i, placeToMove)) {
                                                            isMoving = true;
                                                            pieces[i].setTarget(placeToMove);
                                                            pieces[i].setMove(true);
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (t == Turn.YELLOW) {
                                if (!isMoving) {
                                    Piece[] pieces = playerYellow.getRedPiecePositions();

                                    for (int i = 0; i < pieces.length; i++) {
                                        {
                                            if (pieces[i].getCollision().contains((int) event.getX(), (int) event.getY())) {
                                                Log.d("LudoActivity", "On Piece");
                                                if (pieces[i].getIsKilled() == false || placeToMove == 6) {
                                                    Log.d("LudoActivity", "Click on Yellow");
                                                    if (pieces[i].getComplete() == false) {
                                                        if (playerYellow.checkCanMove(i, placeToMove)) {
                                                            isMoving = true;
                                                            pieces[i].setTarget(placeToMove);
                                                            pieces[i].setMove(true);
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (t == Turn.GREEN) {
                                if (!isMoving) {
                                    Piece[] pieces = playerGreen.getRedPiecePositions();

                                    for (int i = 0; i < pieces.length; i++) {
                                        {
                                            if (pieces[i].getCollision().contains((int) event.getX(), (int) event.getY())) {
                                                Log.d("LudoActivity", "On Piece");
                                                if (pieces[i].getIsKilled() == false || placeToMove == 6) {
                                                    Log.d("LudoActivity", "Click on Green");
                                                    if (pieces[i].getComplete() == false) {
                                                        if (playerGreen.checkCanMove(i, placeToMove)) {
                                                            isMoving = true;
                                                            pieces[i].setTarget(placeToMove);
                                                            pieces[i].setMove(true);
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (t == Turn.BLUE) {
                                if (!isMoving) {
                                    Piece[] pieces = playerBlue.getRedPiecePositions();

                                    for (int i = 0; i < pieces.length; i++) {
                                        {
                                            if (pieces[i].getCollision().contains((int) event.getX(), (int) event.getY())) {
                                                Log.d("LudoActivity", "On Piece");
                                                if (pieces[i].getIsKilled() == false || placeToMove == 6) {
                                                    Log.d("LudoActivity", "Click on blue");
                                                    if (pieces[i].getComplete() == false) {
                                                        if (playerBlue.checkCanMove(i, placeToMove)) {
                                                            isMoving = true;
                                                            pieces[i].setTarget(placeToMove);
                                                            pieces[i].setMove(true);
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else
                            return false;
                        break;
                }
            }
            else
            {
                startActivity(new Intent(context,MainActivity.class));
                return true;
            }
            return  true;
        }
        public void applyMove(String color, int number) {
            Log.d("LudoGameView", "🎯 Move stored for color: " + color + " with number: " + number);
            pendingMoves.put(color.toLowerCase(), number);
        }
    }
    @Override
    public void onMoveReceived(String color, int number) {
        if (ludoGameView != null) {
            ludoGameView.applyMove(color, number);
        }
    }
}
