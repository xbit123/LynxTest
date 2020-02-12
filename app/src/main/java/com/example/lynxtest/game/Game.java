package com.example.lynxtest.game;

import static com.example.lynxtest.game.GameConstants.END_C;
import static com.example.lynxtest.game.GameConstants.END_R;
import static com.example.lynxtest.game.GameConstants.MAZE_HEIGHT;
import static com.example.lynxtest.game.GameConstants.MAZE_WIDTH;
import static com.example.lynxtest.game.CellType.VISITED;
import static com.example.lynxtest.game.ClickResponse.DEATH;
import static com.example.lynxtest.game.ClickResponse.NOTHING;
import static com.example.lynxtest.game.ClickResponse.VISIT;
import static com.example.lynxtest.game.ClickResponse.WIN;

public class Game {
    private Maze maze = new Maze(MAZE_HEIGHT, MAZE_WIDTH);
    private GameState gameState;
    private final MazeGenerator mazeGenerator = new MazeGenerator();

    //called when the game cell gets clicked
    public ClickResponse click(RC rc) {
        switch (maze.cells[rc.getRow()][rc.getCol()]) {
            case VISITED:
                return NOTHING;
            case SNAKE: case SCORPION:
                return DEATH;
            default:
                //if the cell doesn't have any visited neighbours, then we can't go there
                if (maze.hasVisitedNeighbour(rc)) {
                    maze.cells[rc.getRow()][rc.getCol()] = VISITED;
                    if (rc.getRow() == END_R && rc.getCol() == END_C) return WIN;
                    else return VISIT;
                }
                else return NOTHING;
        }
    }

    //Game init
    public void init() {
        gameState = GameState.RUNNING;
        maze = mazeGenerator.generateNewMaze(maze);
    }

    Maze getMaze() {
        return maze;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setWin() {
        gameState = GameState.WIN;
    }

    public void setDeath() {
        gameState = GameState.DEATH;
    }
}
