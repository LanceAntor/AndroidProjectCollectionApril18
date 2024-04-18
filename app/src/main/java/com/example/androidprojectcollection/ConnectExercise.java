package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConnectExercise extends AppCompatActivity implements View.OnClickListener {
    // Define instance variables
    final int colorSet[] = {Color.RED, Color.GREEN};
    public static boolean enabled = true;
    private Button[][] cnBTN = new Button[5][5];

    private String[] names = {"Player 1", "Player 2"};
    private Button btnReset, btn_win;
    private int currentPlayer = 0;
    private int[][] gameBoard = new int[5][5];
    private TextView currentPlayerTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        // Initialize buttons from layout XML
        currentPlayerTextView = findViewById(R.id.currentPlayerTextView);

        // Set click listeners for each button
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                String resIDname = "cnBTN" + (row + 1) + (col + 1);
                int resID = getResources().getIdentifier(resIDname, "id", getPackageName());

                cnBTN[row][col] = findViewById(resID);
                cnBTN[row][col].setOnClickListener(this);
                cnBTN[row][col].setBackgroundColor(Color.BLACK);

                if(row != 0){
                    cnBTN[row][col].setEnabled(false);
                }
            }
        }

        btnReset = findViewById(R.id.btnRestart);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        updateCurrentPlayerTextView();
    }

    @Override
    public void onClick(View v) {
        // Find the clicked button
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (v == cnBTN[row][col]) {
                    // Drop chip in the column
                    if(row == 0){
                        dropChip(col);
                        // Check for win condition
                        checkForWin();
                        checkForDraw();
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private void resetGame() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                gameBoard[row][col] = 0;
                cnBTN[row][col].setBackgroundColor(Color.BLACK);
                cnBTN[row][col].setEnabled(true);
                if(row != 0){
                    cnBTN[row][col].setEnabled(false);
                }
            }
        }
        currentPlayer = 0;
        updateCurrentPlayerTextView();
    }

    private void dropChip(int col) {
        for (int row = 4; row >= 0; row--) {
            if (gameBoard[row][col] == 0) { // If position is empty
                gameBoard[row][col] = currentPlayer + 1; // Set current player's chip
                cnBTN[row][col].setBackgroundColor(colorSet[currentPlayer]); // Set button color
                currentPlayer = (currentPlayer + 1) % 2; // Switch player
                updateCurrentPlayerTextView();
                return;
            }
        }
    }

    private void updateCurrentPlayerTextView() {
        currentPlayerTextView.setText(names[currentPlayer]);
        if (currentPlayer == 0) {
            currentPlayerTextView.setTextColor(Color.RED);
        } else {
            currentPlayerTextView.setTextColor(Color.GREEN);
        }
    }
    private void checkForWin() {
        // Check rows
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 3; col++) {
                if (gameBoard[row][col] != 0 &&
                        gameBoard[row][col] == gameBoard[row][col + 1] &&
                        gameBoard[row][col] == gameBoard[row][col + 2]) {
                    // Three consecutive chips found in the same row
                    announceWinner(gameBoard[row][col]);
                    return;
                }
            }
        }

        // Check columns
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 3; row++) {
                if (gameBoard[row][col] != 0 &&
                        gameBoard[row][col] == gameBoard[row + 1][col] &&
                        gameBoard[row][col] == gameBoard[row + 2][col]) {
                    // Three consecutive chips found in the same column
                    announceWinner(gameBoard[row][col]);
                    return;
                }
            }
        }

        // Check diagonals
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                // Check diagonal from top-left to bottom-right
                if (gameBoard[row][col] != 0 &&
                        gameBoard[row][col] == gameBoard[row + 1][col + 1] &&
                        gameBoard[row][col] == gameBoard[row + 2][col + 2]) {
                    announceWinner(gameBoard[row][col]);
                    return;
                }

                // Check diagonal from top-right to bottom-left
                if (gameBoard[row][col + 2] != 0 &&
                        gameBoard[row][col + 2] == gameBoard[row + 1][col + 1] &&
                        gameBoard[row][col + 2] == gameBoard[row + 2][col]) {
                    announceWinner(gameBoard[row][col + 2]);
                    return;
                }
            }
        }
    }

    private void announceWinner(int player) {
        // Disable all buttons
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                cnBTN[row][col].setEnabled(false);
            }
        }

        // Display winner
        currentPlayerTextView.setText(names[player-1] + "Wins!");
        if(player == 1){
            currentPlayerTextView.setTextColor(Color.RED);
        } else {
            currentPlayerTextView.setTextColor(Color.GREEN);
        }
    }
    private void checkForDraw() {
        boolean isFull = true;

        // Check if any position is still empty
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (gameBoard[row][col] == 0) {
                    isFull = false;
                    break;
                }
            }
        }

        // If all positions are filled and no winner, it's a draw
        if (isFull && !isWinner()) {
            currentPlayerTextView.setText("Draw!");
            currentPlayerTextView.setTextColor(Color.BLACK);
        }
    }

    private boolean isWinner() {
        // Check rows
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 3; col++) {
                if (gameBoard[row][col] != 0 &&
                        gameBoard[row][col] == gameBoard[row][col + 1] &&
                        gameBoard[row][col] == gameBoard[row][col + 2]) {
                    return true; // Three consecutive chips found in the same row
                }
            }
        }

        // Check columns
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 3; row++) {
                if (gameBoard[row][col] != 0 &&
                        gameBoard[row][col] == gameBoard[row + 1][col] &&
                        gameBoard[row][col] == gameBoard[row + 2][col]) {
                    return true; // Three consecutive chips found in the same column
                }
            }
        }

        // Check diagonals
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                // Check diagonal from top-left to bottom-right
                if (gameBoard[row][col] != 0 &&
                        gameBoard[row][col] == gameBoard[row + 1][col + 1] &&
                        gameBoard[row][col] == gameBoard[row + 2][col + 2]) {
                    return true; // Three consecutive chips found in the diagonal
                }

                // Check diagonal from top-right to bottom-left
                if (gameBoard[row][col + 2] != 0 &&
                        gameBoard[row][col + 2] == gameBoard[row + 1][col + 1] &&
                        gameBoard[row][col + 2] == gameBoard[row + 2][col]) {
                    return true; // Three consecutive chips found in the diagonal
                }
            }
        }

        return false; // No winner found
    }

}