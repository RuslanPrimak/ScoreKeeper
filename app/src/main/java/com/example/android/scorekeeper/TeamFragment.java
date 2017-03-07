/*
 * Created by Ruslan Primak on 2/28/17 6:34 PM
 * Copyright (c) 2017. All rights reserved.
 *
 * Last modified 2/28/17 6:16 PM
 */

package com.example.android.scorekeeper;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass representing module for maintaining a team score
 */
public class TeamFragment extends Fragment {

    // Score points could be gained during game
    private final static int TD_POINTS = 6;     // Touchdown points
    private final static int FG_POINTS = 3;     // Field Goal points
    private final static int SF_POINTS = 2;     // Safety points
    private final static int EPG_POINTS = 1;    // Extra Point Goal points
    private final static int EPTD_POINTS = 2;   // Extra Point Touchdown points
    private final static int EP_CANCEL = 0;     // Points for ExtraPoints Cancel button

    // STATE KEYS
    private final static String KEY_EXG_VISIBILITY = "EXG_VISIBILITY";    // Extra Point View Group visibility
    private final static String KEY_SCORE_POINTS_VALUE = "SCORE_POINTS_VALUE"; // Value of score point
    private final static String KEY_CURRENT_TEAM = "CURRENT_TEAM"; // Selected team
    private final static String KEY_FORBIDDEN_TEAM = "FORBIDDEN_TEAM"; // Team forbidden for selection

    // because drawable resources won't be changed its IDs could be obtained once
    private static List<TeamLogo> mTeamLogoList;
    private int mForbiddenPosition = -1; // Item forbidden for selection (likely selected in
    // another) fragment

    private int mScorePoints = 0; // Score for a team

    private Spinner mTeamSpinner; // reference to the team spinner
    private TextView mTextViewScore; // reference to the Score TextView
    private LinearLayout mLayoutScorePoints; // reference to the Layout with Score Points controls
    private LinearLayout mLayoutExtraPoints; // reference to the Layout with ExtraPoint controls
    private Button mButtonTD; // reference to the touchdown button
    private FragmentButtonOnClickListener mListener; // reference to listener of this fragment events


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentButtonOnClickListener) {
            mListener = (FragmentButtonOnClickListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement ScoreButtonOnClickListener.");
        }


        // retrieve resource IDs fro drawables if needed
        initDrawableIDs();
    }

    /**
     * Method sets OnClickListenter for button is responsible for adding score points
     *
     * @param container - view contains the button
     * @param btnID     - resource id of the button
     * @param points    - specified points to be added on button click event
     */
    private void setScoreButtonOnClickListener(final View container, final int btnID,
                                               final int points) {
        Button button = (Button) container.findViewById(btnID);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Score changes enabled only if a team is selected
                if (mTeamSpinner.getSelectedItemPosition() > 0) {
                    updateScorePoints(mScorePoints + points);
                    mListener.onButtonClick(view);
                } else {
                    Toast.makeText(container.getContext(), R.string.toast_team_not_selected,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Provide initializations during creating fragment view
     *
     * @param inflater           - LayoutInflater
     * @param container          - container for fragment
     * @param savedInstanceState - saved data
     * @return - created view of fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team, container, false);

        // get reference to the implementation of the text_view_score
        mTextViewScore = (TextView) view.findViewById(R.id.text_view_score);

        // get reference to the layout contains Extra Points control
        mLayoutScorePoints = (LinearLayout) view.findViewById(R.id.layoutMainButtons);

        // get reference to the layout contains Extra Points control
        mLayoutExtraPoints = (LinearLayout) view.findViewById(R.id.layoutExtraPoints);

        // get reference to the button is responsible for touchdown score
        mButtonTD = (Button) view.findViewById(R.id.btnTouchdown);

        // Apply the adapter to the spinner
        final TeamSpinnerAdapter teamListAdapter = new TeamSpinnerAdapter(getActivity(),
                android.R.layout.simple_spinner_item, mTeamLogoList);

        mTeamSpinner = (Spinner) view.findViewById(R.id.spinner_team);
        mTeamSpinner.setAdapter(teamListAdapter);
        mTeamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mListener.onItemSelected(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // populate the buttons onClick methods
        setScoreButtonOnClickListener(view, R.id.btnTouchdown, TD_POINTS);
        setScoreButtonOnClickListener(view, R.id.btnFieldGoal, FG_POINTS);
        setScoreButtonOnClickListener(view, R.id.btnSafety, SF_POINTS);
        setScoreButtonOnClickListener(view, R.id.btnExtraPointGoal, EPG_POINTS);
        setScoreButtonOnClickListener(view, R.id.btnExtraPointTouchdown, EPTD_POINTS);
        setScoreButtonOnClickListener(view, R.id.btnExtraPointCancel, EP_CANCEL);

        return view;
    }


    /**
     * Updates TextView with actual mScorePoints
     */
    private void updateScorePoints(int points) {
        mScorePoints = points;
        mTextViewScore.setText(String.valueOf(mScorePoints));
    }


    /**
     * Sets visibility for layouts of points controls
     *
     * @param isVisible - boolean value of visibility of Extra Points Layout, visibility of
     *                  Main Score Points layout is opposite
     */
    private void setPointLayoutsVisibility(boolean isVisible) {
        if (isVisible) {
            mLayoutScorePoints.setVisibility(View.GONE);
            mLayoutExtraPoints.setVisibility(View.VISIBLE);
        } else {
            mLayoutExtraPoints.setVisibility(View.GONE);
            mLayoutScorePoints.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Initialise if it needed resource IDs for drawables
     */
    private void initDrawableIDs() {
        if (mTeamLogoList == null) {
            // Get typed arrays for predefined drawables (logos and wordmarks)
            final TypedArray arrayLogos = getResources().obtainTypedArray(R.array.logos_list);
            final TypedArray arrayWMarks = getResources().obtainTypedArray(R.array.wordmarks_list);

            // Fill array of resources IDs to be converted into list for ArrayAdapter
            final TeamLogo[] teamArray = new TeamLogo[arrayLogos.length()];
            for (int i = 0; i < teamArray.length; i++) {
                teamArray[i] = new TeamLogo(arrayLogos.getResourceId(i, 0),
                        arrayWMarks.getResourceId(i, 0));
            }

            arrayLogos.recycle();
            arrayWMarks.recycle();
            mTeamLogoList = new ArrayList<>(Arrays.asList(teamArray));
            mTeamLogoList.add(0, null); // Add a room for the hint item
        }
    }

    /**
     * Save state during lifecycle
     *
     * @param outState - bundle of data
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_EXG_VISIBILITY, mLayoutExtraPoints.getVisibility());
        outState.putInt(KEY_SCORE_POINTS_VALUE, mScorePoints);
        outState.putInt(KEY_CURRENT_TEAM, mTeamSpinner.getSelectedItemPosition());
        outState.putInt(KEY_FORBIDDEN_TEAM, mForbiddenPosition);
    }

    /**
     * Restore state of fragment
     *
     * @param savedInstanceState - bundle of data
     */
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            setPointLayoutsVisibility(savedInstanceState.getInt(KEY_EXG_VISIBILITY) == View.VISIBLE);
            updateScorePoints(savedInstanceState.getInt(KEY_SCORE_POINTS_VALUE, 0));

            final int selection = savedInstanceState.getInt(KEY_CURRENT_TEAM, AdapterView.INVALID_POSITION);
            if (selection != AdapterView.INVALID_POSITION)
                mTeamSpinner.setSelection(selection);

            mForbiddenPosition = savedInstanceState.getInt(KEY_FORBIDDEN_TEAM, AdapterView.INVALID_POSITION);
        }
    }

    /**
     * Reacts on events when another button (own buttons are considered in the same way)
     * Main purpose is to set visibility of the button panels
     *
     * @param view - which button is pressed
     */
    public void anotherButtonClicked(View view) {
        setPointLayoutsVisibility(view == mButtonTD);
    }

    /**
     * Resets Score Points
     */
    public void resetScore() {
        updateScorePoints(0);
    }

    /**
     * Set item position forbidden for selection
     *
     * @param itemPosition - item to be forbidden
     */
    public void setForbiddenItem(int itemPosition) {
        // skip because it is supposed to this has been source
        if (mTeamSpinner.getSelectedItemPosition() != itemPosition)
            mForbiddenPosition = itemPosition;
    }

    /**
     * Interface listener for events in fragment
     */
    interface FragmentButtonOnClickListener {
        void onButtonClick(View view); // on button clicked

        void onItemSelected(int position); // when item has been selected
    }

    /**
     * Class for holding pair of logo and wordmark resource IDs of team
     */
    private class TeamLogo {
        private int logoID;
        private int wordmarkID;

        TeamLogo(int logoID, int wordmarkID) {
            this.logoID = logoID;
            this.wordmarkID = wordmarkID;
        }

        int getLogoID() {
            return logoID;
        }

        int getWordmarkID() {
            return wordmarkID;
        }
    }

    /**
     * Adapter for maintaining TeamLogo objects as spinner items
     */
    private class TeamSpinnerAdapter extends ArrayAdapter<TeamLogo> {
        private final int layoutMainItemID = R.layout.team_spinner_item;
        private final int layoutHintItemID = R.layout.team_spinner_hint_item;

        TeamSpinnerAdapter(Context context, int resource, List<TeamLogo> objects) {
            super(context, resource, objects);
        }

        /**
         * Generalized method for retrieving view for spinner item
         *
         * @param position    - selected position
         * @param convertView - existing view
         * @param parent      - parent for resulting view
         * @param isDropDown  - is this view to be used in DropDown list
         * @return view for spinner item
         */
        private View getViewItem(int position, View convertView, ViewGroup parent,
                                 boolean isDropDown) {
            final View view;


            if (position != 0) {
                view = (convertView != null) && (convertView.getId() == R.id.team_spinner_item) ?
                        convertView :
                        LayoutInflater.from(getContext()).inflate(layoutMainItemID, parent, false);
            } else {
                view = (convertView != null) && (convertView.getId() == R.id.team_spinner_hint_item) ?
                        convertView :
                        LayoutInflater.from(getContext()).inflate(layoutHintItemID, parent, false);
            }

            // Update images for drawables of default spinner item
            if (view.getId() == R.id.team_spinner_item) {
                // Set images according to position
                TeamLogo tm = getItem(position);
                if (tm != null) {
                    ((ImageView) view.findViewById(R.id.team_spinner_item_image_view_logo))
                            .setImageResource(tm.getLogoID());
                    ((ImageView) view.findViewById(R.id.team_spinner_item_image_view_wordmark))
                            .setImageResource(tm.getWordmarkID());
                }
            }

            if (isDropDown) {
                if ((position == 0) || (position == mForbiddenPosition)) // disabled items
                    view.setBackgroundColor(0x24000000);
                else if (position == mTeamSpinner.getSelectedItemPosition()) { // selected item
                    view.setBackgroundColor(0x48ffff00);
                } else {
                    view.setBackgroundColor(0xffffffff);
                }
            }

            return view;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            return getViewItem(position, convertView, parent, false);
        }

        @Override
        public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
            return getViewItem(position, convertView, parent, true);
        }

        @Override
        public boolean isEnabled(int position) {
            return !((position == 0) || (position == mForbiddenPosition)) && super.isEnabled(position);
        }
    }
}
