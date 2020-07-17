package com.example.user.tictactoe2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import static com.example.user.tictactoe2.R.id.board;

public class MainActivity extends AppCompatActivity {

    private BoardView boardView;

    private GameEngine gameEngine;

    private AlertDialog.Builder alertdialogbuilder;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boardView = (BoardView) findViewById(R.id.board);

        gameEngine = new GameEngine();

        boardView.setGameEngine(gameEngine);
        boardView.setMainActivity(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new_game) {
            newGame();
        }

        return super.onOptionsItemSelected(item);
    }

    public void gameEnded(char c) {
        String msg = (c == 'T') ? "Game Over. Draw" : "Game Over. " + c + " win";

        new AlertDialog.Builder(this).setTitle("Tic Tac Toe").
                setMessage(msg).
                setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        newGame();
                    }
                }).show();
    }

    private void newGame() {
        gameEngine.newGame();
        boardView.invalidate();
    }

    public void onBackPressed() {

        alertdialogbuilder = new AlertDialog.Builder(MainActivity.this);

        alertdialogbuilder.setTitle(R.string.Title_Text);

        alertdialogbuilder.setMessage(R.string.Message_Text);

        alertdialogbuilder.setIcon(R.drawable.warning);

        alertdialogbuilder.setCancelable(false);

        alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });

        alertdialogbuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this,"You have clicked cancel button.",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = alertdialogbuilder.create();
        alertDialog.show();
    }
}
