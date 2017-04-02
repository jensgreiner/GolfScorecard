package com.greiner_co.golfscorecard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String HOLE_VALUES = "scoreOfHoles";
    private static final String PLAYER_TOTAL_SCORE = "totalPlayerScore";
    private static final String HOLE_SCORE_VIEWS = "holeScoreViews";
    private static final String HOLE_PAR_VIEWS = "holeParViews";
    private static final String HOLE_SCORE_VIEW_COLORS = "holeScoreViewColors";
    private int[] myHoleScoreViews = {R.id.playerHole1, R.id.playerHole2, R.id.playerHole3, R.id.playerHole4, R.id.playerHole5, R.id.playerHole6, R.id.playerHole7, R.id.playerHole8, R.id.playerHole9, R.id.playerHole10, R.id.playerHole11, R.id.playerHole12, R.id.playerHole13, R.id.playerHole14, R.id.playerHole15, R.id.playerHole16, R.id.playerHole17, R.id.playerHole18};
    private int[] myHoleParViews = {R.id.parHole1, R.id.parHole2, R.id.parHole3, R.id.parHole4, R.id.parHole5, R.id.parHole6, R.id.parHole7, R.id.parHole8, R.id.parHole9, R.id.parHole10, R.id.parHole11, R.id.parHole12, R.id.parHole13, R.id.parHole14, R.id.parHole15, R.id.parHole16, R.id.parHole17, R.id.parHole18};
    private int playerTotalScore = 0;
    private int[] myHoleValues = new int[18];
    private int[] myHoleScoreViewColors = new int[18];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            myHoleScoreViews = savedInstanceState.getIntArray(HOLE_SCORE_VIEWS);
            myHoleParViews = savedInstanceState.getIntArray(HOLE_PAR_VIEWS);
            myHoleValues = savedInstanceState.getIntArray(HOLE_VALUES);
            playerTotalScore = savedInstanceState.getInt(PLAYER_TOTAL_SCORE);
            displayTotalForPlayer();
            int color = 0;
            for (int i = 0; i < myHoleScoreViews.length; i++) {
                if (myHoleScoreViewColors != null) {
                    color = myHoleScoreViewColors[i];
                }
                displayScoreForHole(myHoleValues[i], myHoleScoreViews[i], color);
            }
        } else {
            reset(null);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // save the current state of the counters and ui
        savedInstanceState.putIntArray(HOLE_VALUES, myHoleValues);
        savedInstanceState.putInt(PLAYER_TOTAL_SCORE, playerTotalScore);
        savedInstanceState.putIntArray(HOLE_SCORE_VIEWS, myHoleScoreViews);

        for (int i = 0; i < myHoleScoreViews.length; i++) {
            TextView view = (TextView) findViewById(myHoleScoreViews[i]);
            int color = view.getCurrentTextColor();
            myHoleScoreViewColors[i] = color;
        }
        savedInstanceState.putIntArray(HOLE_SCORE_VIEW_COLORS, myHoleScoreViewColors);

        savedInstanceState.putIntArray(HOLE_PAR_VIEWS, myHoleParViews);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // restore state members from saved instance
        myHoleScoreViews = savedInstanceState.getIntArray(HOLE_SCORE_VIEWS);
        myHoleParViews = savedInstanceState.getIntArray(HOLE_PAR_VIEWS);
        playerTotalScore = savedInstanceState.getInt(PLAYER_TOTAL_SCORE);
        myHoleValues = savedInstanceState.getIntArray(HOLE_VALUES);
        myHoleScoreViewColors = savedInstanceState.getIntArray(HOLE_SCORE_VIEW_COLORS);
        int color = 0;
        for (int i = 0; i < myHoleScoreViews.length; i++) {
            if (myHoleScoreViewColors != null) {
                color = myHoleScoreViewColors[i];
            }
            displayScoreForHole(myHoleValues[i], myHoleScoreViews[i], color);
        }
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
