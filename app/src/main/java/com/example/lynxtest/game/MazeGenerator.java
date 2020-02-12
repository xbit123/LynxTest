package com.example.lynxtest.game;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.lynxtest.game.GameConstants.dirs;
import static com.example.lynxtest.game.GameConstants.END_C;
import static com.example.lynxtest.game.GameConstants.END_R;
import static com.example.lynxtest.game.GameConstants.START_C;
import static com.example.lynxtest.game.GameConstants.START_R;
import static com.example.lynxtest.game.CellType.SCORPION;
import static com.example.lynxtest.game.CellType.SNAKE;


class MazeGenerator {
    //DFS generation of maze
    private void dfsGen(Maze maze, RC curr) {
        //In a loop count the number of traps nearby and number of free cells
        ArrayList<RC> traps = new ArrayList<>();
        int freeNear = 0;
        for (RC dir: dirs) {
            RC neighbour = new RC(curr.getRow() + dir.getRow(), curr.getCol() + dir.getCol());
            if (maze.isIn(neighbour)) {
                if (maze.isFree(neighbour)) freeNear++;
                else traps.add(neighbour);
            }
        }
        //If only 1 free cell is near (the one that we came from) then continue with this path
        if (freeNear <= 1) {
            //Make this cell "free"
            maze.cells[curr.getRow()][curr.getCol()] = CellType.FREE;
            //Select a random direction and try to go there
            Collections.shuffle(traps);
            for (RC trap : traps) {
                dfsGen(maze, trap);
            }
        }
    }

    //If the end cell is a trap then making it a free cell would make it reachable from the start
    private void connectEnd(Maze maze) {
        maze.cells[END_R][END_C] = CellType.FREE;
    }

    //Make start a "visited" cell
    private void putStart(Maze maze) {
        maze.cells[START_R][START_C] = CellType.VISITED;
    }

    //Fill the whole maze with traps
    private void fillTraps(Maze maze) {
        CellType[][] cells = maze.cells;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = Math.random() > 0.5 ? SNAKE : SCORPION;
            }
        }
    }

    //Generates new maze
    Maze generateNewMaze(Maze maze) {
        fillTraps(maze);
        dfsGen(maze, new RC(START_R, START_C));
        connectEnd(maze);
        putStart(maze);
        return maze;
    }
}
