package com.gameshor.maze;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by felix on 17/02/18.
 */

public class MazeView extends View {
    private GameManger gameManger;
    public MazeView(Context context,GameManger gameManger) {
        super(context);
        this.gameManger=gameManger;
        gameManger.setView(this);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        gameManger.draw(canvas);
     }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        gameManger.setScreenSize(w,h);
    }
}
