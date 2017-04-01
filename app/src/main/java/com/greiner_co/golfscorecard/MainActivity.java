package com.greiner_co.golfscorecard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int[] myHoleScoreViews = {R.id.playerHole1, R.id.playerHole2, R.id.playerHole3, R.id.playerHole4, R.id.playerHole5, R.id.playerHole6, R.id.playerHole7, R.id.playerHole8, R.id.playerHole9, R.id.playerHole10, R.id.playerHole11, R.id.playerHole12, R.id.playerHole13, R.id.playerHole14, R.id.playerHole15, R.id.playerHole16, R.id.playerHole17, R.id.playerHole18};
    private final int[] myHoleParViews = {R.id.parHole1, R.id.parHole2, R.id.parHole3, R.id.parHole4, R.id.parHole5, R.id.parHole6, R.id.parHole7, R.id.parHole8, R.id.parHole9, R.id.parHole10, R.id.parHole11, R.id.parHole12, R.id.parHole13, R.id.parHole14, R.id.parHole15, R.id.parHole16, R.id.parHole17, R.id.parHole18};
    private int playerTotalScore = 0;
    private int[] myHoleValues = new int[18];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset(null);
    }

    private void displayTotalForPlayer() {
        TextView totalScoreView = (TextView) findViewById(R.id.playerScoreTotal);
        totalScoreView.setText(String.valueOf(playerTotalScore));
    }

    private void displayScoreForHole(int score, int viewID, int textColor) {
        TextView view = (TextView) findViewById(viewID);
        view.setText(String.valueOf(score));
        view.setTextColor(textColor);
    }

    public void reset(@SuppressWarnings("UnusedParameters") View v) {
        playerTotalScore = 0;
        for (int myHoleView : myHoleScoreViews) {
            displayScoreForHole(0, myHoleView, Color.BLACK);
        }
        myHoleValues = new int[18];
        displayTotalForPlayer();
    }

    private void updateViews(int hole, String operation) {
        hole = hole - 1;
        int textColor = Color.BLACK;
        int holeScore = 0;
        if (operation.equals("+")) {
            holeScore = ++myHoleValues[hole];
            myHoleValues[hole] = holeScore;
            playerTotalScore++;
        } else if (operation.equals("-") && myHoleValues[hole] > 0) {
            holeScore = --myHoleValues[hole];
            playerTotalScore--;
        }
        TextView parView = (TextView) findViewById(myHoleParViews[hole]);
        int holePar = Integer.parseInt(parView.getText().toString());
        if (holePar > holeScore && holeScore != 0) {
            textColor = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);
        } else if (holePar < holeScore) {
            textColor = ResourcesCompat.getColor(getResources(), R.color.colorMinus, null);
        }
        displayScoreForHole(holeScore, myHoleScoreViews[hole], textColor);
        displayTotalForPlayer();
        
    }

    public void addForHole1(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(1, operation);
    }

    public void minusForHole1(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(1, operation);
    }

    public void addForHole2(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(2, operation);
    }

    public void minusForHole2(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(2, operation);
    }

    public void addForHole3(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(3, operation);
    }

    public void minusForHole3(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(3, operation);
    }

    public void addForHole4(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(4, operation);
    }

    public void minusForHole4(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(4, operation);
    }

    public void addForHole5(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(5, operation);
    }

    public void minusForHole5(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(5, operation);
    }

    public void addForHole6(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(6, operation);
    }

    public void minusForHole6(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(6, operation);
    }

    public void addForHole7(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(7, operation);
    }

    public void minusForHole7(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(7, operation);
    }

    public void addForHole8(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(8, operation);
    }

    public void minusForHole8(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(8, operation);
    }

    public void addForHole9(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(9, operation);
    }

    public void minusForHole9(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(9, operation);
    }

    public void addForHole10(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(10, operation);
    }

    public void minusForHole10(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(10, operation);
    }

    public void addForHole11(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(11, operation);
    }

    public void minusForHole11(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(11, operation);
    }

    public void addForHole12(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(12, operation);
    }

    public void minusForHole12(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(12, operation);
    }

    public void addForHole13(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(13, operation);
    }

    public void minusForHole13(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(13, operation);
    }

    public void addForHole14(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(14, operation);
    }

    public void minusForHole14(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(14, operation);
    }

    public void addForHole15(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(15, operation);
    }

    public void minusForHole15(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(15, operation);
    }

    public void addForHole16(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(16, operation);
    }

    public void minusForHole16(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(16, operation);
    }

    public void addForHole17(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(17, operation);
    }

    public void minusForHole17(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(17, operation);
    }

    public void addForHole18(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(18, operation);
    }

    public void minusForHole18(View v) {
        String operation = ((TextView) v).getText().toString();
        updateViews(18, operation);
    }

}
