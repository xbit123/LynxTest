package com.example.lynxtest.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

import com.example.lynxtest.R;

import static com.example.lynxtest.game.GameConstants.MAZE_HEIGHT;
import static com.example.lynxtest.game.GameConstants.MAZE_WIDTH;

public class GameAdapter {
    private int cellLength;
    private final Game game;
    private static Bitmap bmpFreeCell;
    private static Bitmap bmpClickedCell;
    private static Bitmap bmpSnake;
    private static Bitmap bmpScorpion;

    public GameAdapter(Game game, Resources resources) {
        this.game = game;
        bmpFreeCell = BitmapFactory.decodeResource(resources, R.drawable.ic_cell);
        bmpClickedCell = BitmapFactory.decodeResource(resources, R.drawable.ic_cell_clicked);
        bmpSnake = BitmapFactory.decodeResource(resources, R.drawable.ic_snake);
        bmpScorpion = BitmapFactory.decodeResource(resources, R.drawable.ic_scorpion);
    }

    public int getCellLength() {
        return cellLength;
    }

    public RC coordsToCell(int x, int y) {
        return new RC(y / cellLength, x / cellLength);
    }

    //Returns the exact gameview width and height that we want
    public Point getDesiredWH(int specWidth, int specHeight) {
        int cellHeightMax = specHeight / MAZE_HEIGHT;
        int cellWidthMax = specWidth / MAZE_WIDTH;
        cellLength = Math.min(cellHeightMax, cellWidthMax);
        return new Point(cellLength * MAZE_WIDTH, cellLength * MAZE_HEIGHT);
    }

    public Point getTopLeftOfCell(int r, int c) {
        return new Point(c * cellLength, r * cellLength);
    }

    //Returns loaded bitmap
    public Bitmap getBitmap(int r, int c) {
        switch (game.getMaze().cells[r][c]) {
            case VISITED:
                return bmpClickedCell;
            case SNAKE:
                return bmpSnake;
            case SCORPION:
                return bmpScorpion;
            default:
                return bmpFreeCell;
        }
    }
}
