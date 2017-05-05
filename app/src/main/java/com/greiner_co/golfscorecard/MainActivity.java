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
            resetScore(null);
        }
    }

    /**
     * Added to save the state of the app before e.g. a switch to landscape or vice versa
     *
     * @param savedInstanceState is the bundle the current state is saved too
     */
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

    /**
     * Part of the state saving activities
     *
     * @param savedInstanceState is the bundle the current state is retrieved from
     */
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

    /**
     * Sets the players total score into the TextView and displays it
     */
    private void displayTotalForPlayer() {
        TextView totalScoreView = (TextView) findViewById(R.id.playerScoreTotal);
        totalScoreView.setText(String.valueOf(playerTotalScore));
    }

    /**
     * Sets the given text value and the given color of a given textView
     * @param score of the current hole
     * @param viewID represents the textView of the current
     * @param textColor to show whether a score is above or below the par of the current hole
     */
    private void displayScoreForHole(int score, int viewID, int textColor) {
        TextView view = (TextView) findViewById(viewID);
        view.setText(String.valueOf(score));
        view.setTextColor(textColor);
    }

    /**
     * Resets all the player score TextViews and the total score TextView to zero and sets the text color back to black
     */
    public void resetScore(@SuppressWarnings("UnusedParameters") View v) {
        playerTotalScore = 0;
        for (int myHoleView : myHoleScoreViews) {
            displayScoreForHole(0, myHoleView, Color.BLACK);
        }
        myHoleValues = new int[18];
        displayTotalForPlayer();
    }

    /**
     * Collects some data for updating the score views for a single hole and total score textView
     * @param hole represents the current hole
     * @param operation tells whether the user added some points or removed points to current hole
     */
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

    /**
     * Handle the button click's either on plus or minus button to increase/decrease the scores per hole & total player score
     *
     * @param v represents the button clicked
     */
    public void buttonClicked(View v) {
        String operation = ((TextView) v).getText().toString();
        int holeNumber = 0;

        switch (v.getId()) {
            case R.id.buttonPlusHole1:
            case R.id.buttonMinusHole1:
                holeNumber = 1;
                break;
            case R.id.buttonPlusHole2:
            case R.id.buttonMinusHole2:
                holeNumber = 2;
                break;
            case R.id.buttonPlusHole3:
            case R.id.buttonMinusHole3:
                holeNumber = 3;
                break;
            case R.id.buttonPlusHole4:
            case R.id.buttonMinusHole4:
                holeNumber = 4;
                break;
            case R.id.buttonPlusHole5:
            case R.id.buttonMinusHole5:
                holeNumber = 5;
                break;
            case R.id.buttonPlusHole6:
            case R.id.buttonMinusHole6:
                holeNumber = 6;
                break;
            case R.id.buttonPlusHole7:
            case R.id.buttonMinusHole7:
                holeNumber = 7;
                break;
            case R.id.buttonPlusHole8:
            case R.id.buttonMinusHole8:
                holeNumber = 8;
                break;
            case R.id.buttonPlusHole9:
            case R.id.buttonMinusHole9:
                holeNumber = 9;
                break;
            case R.id.buttonPlusHole10:
            case R.id.buttonMinusHole10:
                holeNumber = 10;
                break;
            case R.id.buttonPlusHole11:
            case R.id.buttonMinusHole11:
                holeNumber = 11;
                break;
            case R.id.buttonPlusHole12:
            case R.id.buttonMinusHole12:
                holeNumber = 12;
                break;
            case R.id.buttonPlusHole13:
            case R.id.buttonMinusHole13:
                holeNumber = 13;
                break;
            case R.id.buttonPlusHole14:
            case R.id.buttonMinusHole14:
                holeNumber = 14;
                break;
            case R.id.buttonPlusHole15:
            case R.id.buttonMinusHole15:
                holeNumber = 15;
                break;
            case R.id.buttonPlusHole16:
            case R.id.buttonMinusHole16:
                holeNumber = 16;
                break;
            case R.id.buttonPlusHole17:
            case R.id.buttonMinusHole17:
                holeNumber = 17;
                break;
            case R.id.buttonPlusHole18:
            case R.id.buttonMinusHole18:
                holeNumber = 18;
                break;
        }

        updateViews(holeNumber, operation);
    }
}
