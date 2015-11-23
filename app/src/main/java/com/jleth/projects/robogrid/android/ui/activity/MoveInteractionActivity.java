package com.jleth.projects.robogrid.android.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jleth.projects.robogrid.android.R;
import com.jleth.projects.robogrid.android.model.Location;
import com.jleth.projects.robogrid.android.model.Size;

/**
 * Begin to let the user move the object around in the grid via 3 controls
 */
public class MoveInteractionActivity extends AppCompatActivity {

    private static final String TAG = "MoveInteractionActivity";
    private static final String ARGS_GRID_SIZE = "ARGS_GRID_SIZE";
    private static final String ARGS_LOCATION = "ARGS_LOCATION";

    private MoveInteractionPage mPage;
    private Size mGridSize;
    private Location mLocation;


    public static Intent newIntent(Context context, Size gridSize, Location startLocation) {
        Intent i = new Intent(context, MoveInteractionActivity.class);
        i.putExtra(ARGS_GRID_SIZE, gridSize);
        i.putExtra(ARGS_LOCATION, startLocation);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_move_interaction);

        extractBundle(savedInstanceState != null ? savedInstanceState : getIntent().getExtras());

        initContent();
        hide();

        setupPageListener();

        if (savedInstanceState == null) {
            animateIntro();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        hide();
    }

    private void extractBundle(Bundle extras) {
        mGridSize = extras.getParcelable(ARGS_GRID_SIZE);
        mLocation = extras.getParcelable(ARGS_LOCATION);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(ARGS_GRID_SIZE, mGridSize);
        outState.putParcelable(ARGS_LOCATION, mLocation);
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mPage.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void initContent() {
        mPage = (MoveInteractionPage) findViewById(R.id.mainContent);
        mPage.setGridSize(mGridSize);
        mPage.updateLocation(mLocation);
    }

    private void setupPageListener() {
        mPage.setListener(new MoveInteractionPage.Listener() {
            @Override
            public void onTurnLeft() {

            }

            @Override
            public void onTurnRight() {

            }

            @Override
            public void onForward() {

            }
        });
    }

    private void animateIntro() {
        mPage.animateRevealContent(null);
    }

}
