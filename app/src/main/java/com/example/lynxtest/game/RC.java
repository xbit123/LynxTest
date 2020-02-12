package com.example.lynxtest.game;

//Class to store cell coordinates
public class RC {
    private int row;
    private int col;

    RC(int row, int col) {
        this.row = row;
        this.col = col;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }
}
