package com.example.lynxtest.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.lynxtest.game.GameAdapter;
import com.example.lynxtest.R;
import com.example.lynxtest.game.ClickResponse;
import com.example.lynxtest.game.Game;
import com.example.lynxtest.game.RC;
import com.example.lynxtest.ui.dialog.GameEndDialog;
import com.example.lynxtest.ui.view.GameView;
import com.example.lynxtest.ui.viewmodel.GameViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {
    @BindView(R.id.gv_main)
    GameView gameView;

    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        Game game = gameViewModel.getGame();
        if (game == null) {
            game = new Game();
            gameViewModel.setGame(game);
            game.init();
        }
        switch (game.getGameState()) {
            case DEATH:
                showDeath();
                break;
            case WIN:
                showWin();
                break;
        }

        GameAdapter gameAdapter = new GameAdapter(game, getResources());
        gameView.setGameAdapter(gameAdapter);
        final Game fgame = gameViewModel.getGame();
        gameView.setCellClickListener((RC rc) -> {
            ClickResponse result = fgame.click(rc);
            switch (result) {
                case DEATH:
                    fgame.setDeath();
                    showDeath();
                    break;
                case WIN:
                    fgame.setWin();
                    showWin();
                    break;
            }
        });
    }

    private void showDeath() {
        String msg = getString(R.string.death);
        GameEndDialog gameEndDialog = new GameEndDialog(this, msg);
        gameEndDialog.show();
        gameEndDialog.setOnDismissListener(
                (DialogInterface dialog) -> {
                    gameViewModel.getGame().init();
                    gameView.invalidate();
                }
        );
    }

    private void showWin() {
        String msg = getString(R.string.win);
        GameEndDialog gameEndDialog = new GameEndDialog(this, msg);
        gameEndDialog.show();
        gameEndDialog.setOnDismissListener(
                (DialogInterface dialog) -> {
                    gameViewModel.getGame().init();
                    gameView.invalidate();
                }
        );
    }
}
