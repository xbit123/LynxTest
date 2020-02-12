package com.example.lynxtest.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.lynxtest.game.GameConstants;
import com.example.lynxtest.game.GameAdapter;
import com.example.lynxtest.game.RC;

public class GameView extends View {
    private OnCellClickListener onCellClickListener;
    private GameAdapter gameAdapter;
    private Point desiredWH;
    private final Rect destRect = new Rect();

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public interface OnCellClickListener {
        void onCellClick(RC rc);
    }

    public void setCellClickListener(OnCellClickListener onCellClickListener) {
        this.onCellClickListener = onCellClickListener;
    }

    public void setGameAdapter(GameAdapter gameAdapter) {
        this.gameAdapter = gameAdapter;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();

        if (eventAction == MotionEvent.ACTION_DOWN) {
            int x = (int)event.getX();
            int y = (int)event.getY();
            if (x < desiredWH.x && y < desiredWH.y)
                onCellClickListener.onCellClick(gameAdapter.coordsToCell(x, y));
        }

        invalidate();
        //return that the event was handled
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        desiredWH = gameAdapter.getDesiredWH(specWidth, specHeight);
        if (modeWidth != MeasureSpec.EXACTLY && modeHeight != MeasureSpec.EXACTLY) {
            setMeasuredDimension(desiredWH.x, desiredWH.y);
        } else setMeasuredDimension(specWidth, specHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //iterate over all cells
        for (int i = 0; i < GameConstants.MAZE_HEIGHT; i++) {
            for (int j = 0; j < GameConstants.MAZE_WIDTH; j++) {
                //draw cell
                Point topLeft = gameAdapter.getTopLeftOfCell(i, j);
                Bitmap bmp = gameAdapter.getBitmap(i, j);
                destRect.set(topLeft.x, topLeft.y, topLeft.x + gameAdapter.getCellLength(), topLeft.y + gameAdapter.getCellLength());

                canvas.drawBitmap(bmp, null, destRect,null);
            }
        }
    }
}
