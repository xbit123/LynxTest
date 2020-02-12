package com.example.lynxtest.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.lynxtest.game.Game;

public class GameViewModel extends ViewModel {
    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
