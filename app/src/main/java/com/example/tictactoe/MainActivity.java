package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button[][] buttons = new Button[3][3];

    private boolean player1_turn = true;

    private int roundCount;

    private int player1_points;
    private int player2_points;

    private TextView player1_score;
    private TextView player2_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1_score = findViewById(R.id.player1_textView);
        player2_score = findViewById(R.id.player2_textView);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                String button_id = "button_" + i + j;

                int resource_id = getResources().getIdentifier(button_id, "id", getPackageName());

                System.out.println(resource_id);

                buttons[i][j] = findViewById(resource_id);

                buttons[i][j].setOnClickListener(this);
            }
        }

        Button reset_button = findViewById(R.id.reset_button);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTheGame();
            }
        });

    }

    @Override
    public void onClick(View v) {
        System.out.println("This is v : " + v);

        if (!((Button) v).getText().toString().equals("")) { //Type casting View v to Button and checking for empty button
            return; // Do nothing
        }

        if (player1_turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        //Declaring Winner or Draw
        if (winStatus()) {
            if (player1_turn) {
                player1_wins();
            } else {
                player2_wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1_turn = !player1_turn;
        }

    }

    public boolean winStatus() {

        String[][] field = new String[3][3]; //Create 2d String array to put button id text in it

        //Getting all buttons into String array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        //Checking all conditions
        //Rows
        for (int i = 0; i < 3; i++) {

            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        //Columns
        for (int i = 0; i < 3; i++) {

            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        //Diagonal 1
        for (int i = 0; i < 3; i++) {

            if (field[0][0].equals(field[1][1])
                    && field[0][0].equals(field[2][2])
                    && !field[0][0].equals("")) {
                return true;
            }
        }

        //Diagonal 2
        for (int i = 0; i < 3; i++) {

            if (field[0][2].equals(field[1][1])
                    && field[0][2].equals(field[2][0])
                    && !field[0][2].equals("")) {
                return true;
            }
        }

        return false;
    }

    private void player1_wins() {
        player1_points++;
        Toast.makeText(this, "Player 1 WINS!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetGame();
    }

    private void player2_wins() {
        player2_points++;
        Toast.makeText(this, "Player 2 WINS!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetGame();
    }

    private void draw() {
        Toast.makeText(this, "This game was a draw!", Toast.LENGTH_SHORT).show();
        resetGame();
    }

    private void updatePointsText() {
        player1_score.setText("Player 1 : " + player1_points);
        player2_score.setText("Player 2 : " + player2_points);
    }

    private void resetGame() {

        //Resetting all buttons to empty String
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1_turn = true;
    }

    private void resetTheGame () {
        player1_points = 0;
        player2_points = 0;
        updatePointsText();
        resetGame();
    }

}