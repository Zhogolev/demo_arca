package com.zhogolev.ballsjava;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by konsz on 11.01.2018.
 */

public class OnTouchListnerGameView implements View.OnTouchListener {
    public static float newX = (float) 0.;


    public boolean onTouch(View view, MotionEvent motion) {


        switch(motion.getAction()) { // определяем какая кнопка
            case MotionEvent.ACTION_MOVE:

                newX = motion.getX()/1080*20;
                break;
        }
        return true;
    }
}
