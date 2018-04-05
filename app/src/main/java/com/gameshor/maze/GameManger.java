package com.gameshor.maze;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felix on 17/02/18.
 */

public class GameManger extends GestureDetector.SimpleOnGestureListener{
    private List<Drawable> drawableList;
    private View view;
    private Player player;
    private Maze maze;
    private Rect rect =new Rect();
    private int size =0;


    public GameManger(){
        player= new Player();
        maze=new Maze(20);
        drawableList=new ArrayList<>();
        drawableList.add(player);
        drawableList.add(maze);
    }
    public void draw(Canvas canvas){
        for (Drawable drawableItem:drawableList){
            drawableItem.draw(canvas,rect);
        }

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int diffx=0,diffy=0;
        diffx=Math.round(e2.getX()-e1.getX());
        diffy=Math.round(e2.getY()-e1.getY());
        player.move(diffx,diffy);
        view.invalidate();

        return super.onFling(e1, e2, velocityX, velocityY);
    }

    public void setView(View view) {
        this.view = view;
    }
    public void  setScreenSize(int width,int high){
        size=Math.min(width,high);
        rect.set((width-size)/2,(high-size)/2,(width+size)/2,(high+size)/2);
    }
}
