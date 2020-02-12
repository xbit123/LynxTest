package com.example.lynxtest.game;

import static com.example.lynxtest.game.GameConstants.MAZE_HEIGHT;
import static com.example.lynxtest.game.GameConstants.MAZE_WIDTH;
import static com.example.lynxtest.game.CellType.FREE;
import static com.example.lynxtest.game.CellType.VISITED;

class Maze {
    CellType[][] cells;

    Maze(int h, int w) {
        this.cells = new CellType[h][w];
    }

    //Checks if the cell is in bounds
    Boolean isIn(RC RC) {
        return RC.getCol() >= 0 && RC.getRow() >= 0 &&
                RC.getCol() < MAZE_WIDTH &&
                RC.getRow() < MAZE_HEIGHT;
    }

    Boolean isFree(RC rc) {
        return cells[rc.getRow()][rc.getCol()] == FREE;
    }

    private Boolean isVisited(RC rc) {
        return cells[rc.getRow()][rc.getCol()] == VISITED;
    }

    //Checks if the cell has visited neighbours
    Boolean hasVisitedNeighbour(RC rc) {
        for (RC dir : GameConstants.dirs) {
            RC neighbour = new RC(rc.getRow() + dir.getRow(), rc.getCol() + dir.getCol());
            if (isIn(neighbour) && isVisited(neighbour)) return true;
        }
        return false;
    }

}
