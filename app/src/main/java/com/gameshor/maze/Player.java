package com.gameshor.maze;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by felix on 17/02/18.
 */

public class Player implements Drawable {
    private Point point;

    private Paint paint01;
    private Paint paint02;
    public Player(){
        point=new Point(50,50);
        paint01=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint01.setColor(Color.GREEN);
        paint02=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint02.setColor(Color.BLACK);
    }
    public void move(int diffx,int diffy){
        point.x+=diffx;
        point.y+=diffy;
        Log.i("GM","Alert !!!!!"+String.format("diffx = %d diffy = %d",diffx,diffy));


    }
    @Override
    public void draw(Canvas canvas, Rect r) {
        canvas.drawCircle(point.x+1,point.y+1,50,paint01);
    }
}
