package com.zhogolev.ballsjava.models;

import android.content.Context;

import com.zhogolev.ballsjava.GameView;
import com.zhogolev.ballsjava.OnTouchListnerGameView;
import com.zhogolev.ballsjava.R;

/**
 * Created by konsz on 11.01.2018.
 */

public class Arcanoid extends GameBody {

    public Arcanoid(Context context, float weight, float height, float startX, float startY) {

        super(weight, height, startX, startY); //создаем прототип с начальными данными
        bitmapId = R.drawable.arcanoid; // загружаем картинку
        init(context); // инициализируем планшет(нижняя полоска) арканойд
    }

    @Override
    public void update() { // перемещаем корабль в зависимости от нажатой кнопки
        float currentX = OnTouchListnerGameView.newX;
        if (currentX >= 0 && currentX <= GameView.maxX - weight)
            x = OnTouchListnerGameView.newX;
    }

    @Override
    public void run() {
        update();
    }
}

