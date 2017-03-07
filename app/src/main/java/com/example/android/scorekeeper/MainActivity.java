/*
 * Created by Ruslan Primak on 2/28/17 6:34 PM
 * Copyright (c) 2017. All rights reserved.
 *
 * Last modified 2/28/17 6:16 PM
 */

package com.example.android.scorekeeper;

import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity
        implements TeamFragment.FragmentButtonOnClickListener {

    // references to the fragments for teams
    private TeamFragment mTeamA;
    private TeamFragment mTeamB;

    private ViewGroup mSceneRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSceneRoot = (ViewGroup) findViewById(R.id.activity_main);

        // get fragments
        FragmentManager fm = getSupportFragmentManager();
        mTeamA = (TeamFragment) fm.findFragmentById(R.id.team_a_frag);
        mTeamB = (TeamFragment) fm.findFragmentById(R.id.team_b_frag);
    }


    /**
     * Broadcast OnButtonClick event among fragments
     *
     * @param view - button clicked
     */
    @Override
    public void onButtonClick(View view) {
        TransitionManager.beginDelayedTransition(mSceneRoot);
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
