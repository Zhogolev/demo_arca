package com.zhogolev.ballsjava.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.zhogolev.ballsjava.GameView;

/**
 * Created by konsz on 11.01.2018.
 */

public class GameBody implements Runnable {

    protected float x; // координаты
    protected float y;
    protected float weight; // ширина
    protected float height; //высота , у меня все прямоугльники, можно конечно сделать
                            //рассчет v3, считать касательные по нормале и.т.д

    protected int bitmapId; // id картинки
    protected Bitmap bitmap; // картинка

    public GameBody(float weight, float height, float startX, float startY) {
        this.x = startX;
        this.y = startY;
        this.weight = weight;
        this.height = height;
    }

   void init(Context context) { // сжимаем картинку до нужных размеров
        //Нужно доделать, чтоб при изменении ориетации экрана менялась растяжка.
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, (int)(weight * GameView.unitW), (int)(height * GameView.unitH), false);
        cBitmap.recycle();
    }

    void update(){ // тут будут вычисляться новые координаты
        //Каждое тело должно иметь координаты на gameView,чтоб
        //его можно было отрисовать
    }
    public void draw(Paint paint, Canvas canvas){ // рисуем картинку
        canvas.drawBitmap(bitmap, x*GameView.unitW, y*GameView.unitH, paint);
    }

    @Override
    public void run() {
        //Пусть каждое тело запускается в своем потоке, тогда
        //не прийдется имплемитировать к каждому наследнику runnable
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }
}
