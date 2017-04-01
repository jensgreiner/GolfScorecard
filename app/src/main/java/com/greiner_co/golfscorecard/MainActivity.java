package com.greiner_co.golfscorecard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int[] myHoleScoreViews = {R.id.playerHole1, R.id.playerHole2, R.id.playerHole3, R.id.playerHole4, R.id.playerHole5, R.id.playerHole6, R.id.playerHole7, R.id.playerHole8, R.id.playerHole9, R.id.playerHole10, R.id.playerHole11, R.id.playerHole12, R.id.playerHole13, R.id.playerHole14, R.id.playerHole15, R.id.playerHole16, R.id.playerHole17, R.id.playerHole18};
    private int playerTotalScore = 0;
    private int[] myHoleValues = new int[18];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset(null);
    }

    private void displayTotalForPlayer(int score) {
        TextView totalScoreView = (TextView) findViewById(R.id.playerScoreTotal);
        totalScoreView.setText(String.valueOf(playerTotalScore));
    }

    private void displayScoreForHole(int score, int viewID) {
        TextView view = (TextView) findViewById(viewID);
        view.setText(String.valueOf(score));
    }

    public void reset(View v) {
        for (int myHoleView : myHoleScoreViews) {
            TextView view = (TextView) findViewById(myHoleView);
            view.setText(String.valueOf(0));
            view.setTextColor(Color.BLACK);
        }

        displayTotalForPlayer(0);
    }

    /*
    private void reset() {

        TextView view = (TextView) findViewById(R.id.playerHole1);
        view.setText(String.valueOf(0));
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole2);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole3);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole4);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole5);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole6);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole7);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole8);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole9);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole10);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole11);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole12);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole13);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole14);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole15);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole16);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole17);
        view.setText(0);
        view.setTextColor(Color.BLACK);
        view = (TextView) findViewById(R.id.playerHole18);
        view.setText(0);
        view.setTextColor(Color.BLACK);

        displayTotalForPlayer(0);
    }
*/
    public void addForHole1(View v) {
        TextView holeScoreView = (TextView) findViewById(myHoleScoreViews[0]);
        myHoleValues[0] = ++myHoleValues[0];
        playerTotalScore++;

        holeScoreView.setText(String.valueOf(myHoleValues[0]));
        displayTotalForPlayer(playerTotalScore);
    }
}
