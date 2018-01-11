package com.zhogolev.ballsjava.models;

import android.content.Context;

import com.zhogolev.ballsjava.GameView;
import com.zhogolev.ballsjava.R;

/**
 * Created by konsz on 11.01.2018.
 */

public class Ball extends GameBody {
    private float dx = 0;
    private float dy = 0;

    public Ball(Context context, float weight, float height, float startX, float startY, float speed) {
        super(weight, height, startX, startY);

        this.dx = (-1 + 2 * (int) Math.random()) * speed;
        this.dy = (-1 + 2 * (int) Math.random()) * speed;

        this.bitmapId = R.drawable.ball; // определяем начальные параметры
        init(context);
    }

    @Override
    public void run() {
        update();
    }

    @Override
    public void update() { // перемещаем корабль в зависимости от нажатой кнопки
        if (x >= GameView.maxX - weight || x <= 0) {
            dx = -dx;
        }
        if (y >= GameView.maxY - height || y <= 0) {
            dy = -dy;
        }

        x += dx;
        y += dy;
    }

    public void collision() {
        dy = -dy;
    }

    public boolean isCollision(float shipX, float shipY, float shipWeight, float shipHeght) {
        return !(((x + weight) < shipX) || (x > (shipX + shipWeight)) || ((y + height) < shipY) || (y > (shipY + shipHeght)));
        // return false;
    }
}
