package com.example.lynxtest.game;

public class GameConstants {
    public static final int MAZE_WIDTH = 9;
    public static final int MAZE_HEIGHT = 9;
    static final int START_R = MAZE_HEIGHT - 1;
    static final int START_C = MAZE_WIDTH / 2;
    static final int END_R = 0;
    static final int END_C = MAZE_WIDTH / 2;
    //All possible directions we can go
    static final RC[] dirs = {
            new RC(1, 0),
            new RC(0, 1),
            new RC(-1, 0),
            new RC(0, -1)};
}
