/*
 * Created by Ruslan Primak on 2/28/17 6:34 PM
 * Copyright (c) 2017. All rights reserved.
 *
 * Last modified 2/28/17 6:16 PM
 */

package com.example.android.scorekeeper;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements TeamFragment.FragmentButtonOnClickListener {

    // references to implementations of the ResettableOnClickListener in fragments for teams
    private TeamFragment mTeamA;
    private TeamFragment mTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get listeners for fragments
        FragmentManager fm = getSupportFragmentManager();
        mTeamA = (TeamFragment) fm.findFragmentById(R.id.team_a_frag);
        mTeamB = (TeamFragment) fm.findFragmentById(R.id.team_b_frag);
    }


    /**
     * Broadcast OnButtonClick event from one fragment through other
     *
     * @param view - button clicked
     */
    @Override
    public void onButtonClick(View view) {
        mTeamA.anotherButtonClicked(view);
        mTeamB.anotherButtonClicked(view);
    }

    @Override
    public void onItemSelected(int position) {
        mTeamA.setForbiddenItem(position);
        mTeamB.setForbiddenItem(position);
    }


    /**
     * Reset scores for teams
     *
     * @param view sends reset
     */
    public void onResetClick(View view) {
        onButtonClick(view);

        mTeamA.resetScore();
        mTeamB.resetScore();
    }
}
