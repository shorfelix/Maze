package com.gameshor.maze;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by felix on 22/02/18.
 */

public class Maze implements Drawable {
    private Paint wallPaint;
    private Paint paint02;
    private  final boolean [][] array;
    private final Point end=new Point(1,1);
    private  Point start;
    private int bestScore=0;
    private final int size;
    public Maze(int size){
        this.size=size;
        wallPaint =new Paint(Paint.ANTI_ALIAS_FLAG);
        wallPaint.setColor(Color.GREEN);
        paint02=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint02.setColor(Color.BLACK);
        array = new boolean[size][size];
        generateMaze();
    }
    private  void generateMaze(){
        for (int i=0;i<size;++i){
            for(int j=0;j<size;++j){
                array[i][j]=i%2!=0&&j%2!=0&&j<size-1&&i<size-1;
            }
        }
        Random random =new Random();
        Stack<Point> stack=new Stack<>();
        stack.push(end);
        while(stack.size()>0){
            Point current =stack.peek();
            List<Point>unusedNeighbors=new LinkedList<>();
            //left
            if (current.x>2){
                if(!isUsed(current.x-2,current.y))
                    unusedNeighbors.add(new Point(current.x-2,current.y));
            }
            //right
            if (current.x<size-2){
                if(!isUsed(current.x+2,current.y))
                    unusedNeighbors.add(new Point(current.x+2,current.y));
            }
            //top
            if (current.y>2){
                if(!isUsed(current.x,current.y-2))
                    unusedNeighbors.add(new Point(current.x,current.y-2));
            }
            //down
            if (current.y<size-2){
                if(!isUsed(current.x,current.y+2))
                    unusedNeighbors.add(new Point(current.x,current.y+2));
            }
            if (unusedNeighbors.size()>0){
                int rnd =random.nextInt(unusedNeighbors.size());
                Point direction=unusedNeighbors.get(rnd);
                int diffX=(direction.x-current.x)/2;
                int diffY=(direction.y-current.y)/2;
                array[current.y+diffY][current.x+diffX]=true;
                stack.push(direction);

            }else{
                if (bestScore<stack.size()){
                    bestScore=stack.size();
                    start=current;
                }
                stack.pop();
            }
        }
    }
    private boolean isUsed(int x,int y){
        if (x<0||y<0||x>=size-1||y>=size-1){
            return true;
        }
        return array[y-1][x]||array[y][x-1]||array[y+1][x]||array[y][x+1];
    }
    @Override
    public void draw(Canvas canvas, Rect rect) {
        float cellSize=(float) (rect.right-rect.left)/size;
        for(int i=0;i<size;++i){
            for(int j=0;j<size;++j){
                if(!array[i][j]){
                    float left =j*cellSize+rect.left;
                    float top=i*cellSize+rect.top;
                    canvas.drawRect(left,top,left+cellSize,top+cellSize,wallPaint);
                }
            }
        }
    }
}
