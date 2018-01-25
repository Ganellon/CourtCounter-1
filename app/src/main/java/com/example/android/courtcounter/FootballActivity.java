package com.example.android.courtcounter;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class FootballActivity extends AppCompatActivity {

    private boolean teamAInPossession = true;
    private int scoreTeamA = 0;
    private int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);

        getSupportActionBar().setTitle("Football");

        //Set actionBar bar color
        ColorDrawable primary = new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary_football));
        getSupportActionBar().setBackgroundDrawable(primary);

        //Set status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark_football));
        }

        // Show back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        updateDisplay();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                resetGame();
                return true;
            case android.R.id.home:
                goBackToMainActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goBackToMainActivity() {
        // Switch to the Main Activity
        Intent showMainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(showMainActivity);
    }

    public void changePossession(View view) {
        teamAInPossession = !teamAInPossession;
        updateDisplay();
    }

    public void touchdown(View view) {
        updateScore(6);
    }

    public void extraPoint(View view) {
        updateScore(1);
    }

    public void twoPointConversion(View view) {
        updateScore(2);
    }

    public void fieldGoal(View view) {
        updateScore(3);
    }

    public void safety(View view) {
        updateScore(2);
    }

    public void resetGame() {
        scoreTeamA = 0;
        scoreTeamB = 0;
        teamAInPossession = true;
        updateDisplay();
    }

    public void updateScore(int points) {
        if (teamAInPossession == true) {
            scoreTeamA += points;
        } else if (teamAInPossession == false) {
            scoreTeamB += points;
        }

        updateDisplay();
    }

    // Update all TextViews
    public void updateDisplay() {
        // Indicate which team is in possession
        TextView teamA_possessionTextView = (TextView) findViewById(R.id.football_team_a_possession);
        TextView teamB_possessionTextView = (TextView) findViewById(R.id.football_team_b_possession);
        if (teamAInPossession) {
            teamA_possessionTextView.setVisibility(View.VISIBLE);
            teamB_possessionTextView.setVisibility(View.INVISIBLE);
        } else {
            teamA_possessionTextView.setVisibility(View.INVISIBLE);
            teamB_possessionTextView.setVisibility(View.VISIBLE);
        }

        // Team A Score
        TextView scoreView_A = (TextView) findViewById(R.id.football_team_a_score);
        scoreView_A.setText(String.valueOf(scoreTeamA));

        // Team B Score
        TextView scoreView_B = (TextView) findViewById(R.id.football_team_b_score);
        scoreView_B.setText(String.valueOf(scoreTeamB));
    }
}