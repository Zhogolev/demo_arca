package com.zhogolev.ballsjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.zhogolev.ballsjava.models.Arcanoid;
import com.zhogolev.ballsjava.models.Ball;

/**
 * Created by konsz on 11.01.2018.
 */

public class GameView extends SurfaceView implements Runnable {

    public static int maxX = 20; // размер по горизонтали
    public static int maxY = 28; // размер по вертикали
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private Thread gameThread;
    private Thread arcanoidThread;
    private Thread ballThread;

    private boolean gameRunning = true;
    private boolean firstRun = true;

    private Arcanoid arcanoid;
    private Ball ball;

    public GameView(Context context) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();

        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            checkCollision();

        }
    }

    private void update() {
        if (!firstRun) {
            arcanoidThread = new Thread(arcanoid);
            ballThread = new Thread(ball);

            arcanoidThread.start();
            ballThread.start();
        }
    }

    private void checkCollision() {
        if (arcanoid == null)
            return;
        if (ball.isCollision(arcanoid.getX(), arcanoid.getY(), arcanoid.getWeight(), arcanoid.getHeight())) {
            ball.collision();
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            if (firstRun) { // инициализация при первом запуске
                firstRun = false;
                unitW = surfaceHolder.getSurfaceFrame().width() / maxX; // вычисляем число пикселей в юните
                unitH = surfaceHolder.getSurfaceFrame().height() / maxY;
                // добавляем мяч
                ball = new Ball(getContext(), 3, 3, 7, 7, (float) 0.2);
                // добавляем арканойд
                arcanoid = new Arcanoid(getContext(), 6, 2, maxX/2, maxY - ball.getHeight());//
            }

            canvas = surfaceHolder.lockCanvas(); // закрываем canvas
            canvas.drawColor(Color.BLACK); // заполняем фон чёрным

            ball.draw(paint, canvas); // рисуем корабль
            arcanoid.draw(paint, canvas);

            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }
}
