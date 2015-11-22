package com.jleth.projects.robogrid.android.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.jleth.projects.robogrid.android.R;
import com.jleth.projects.robogrid.android.model.Location;
import com.jleth.projects.robogrid.android.model.Size;

/**
 * Set start location and direction before beginning
 */
public class StartLocationActivity extends AppCompatActivity {

    private static final String TAG = "StartLocationActivity";
    private static final String ARGS_GRID_SIZE = "ARGS_GRID_SIZE";

    private StartLocationPage mPage;
    private Size mGridSize;


    public static Intent newIntent(Context context, Size gridSize) {
        Intent i = new Intent(context, StartLocationActivity.class);
        i.putExtra(ARGS_GRID_SIZE, gridSize);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_location);

        extractIntent();
        hideActionBar();

        initContent();

        setupPageListener();

        if (savedInstanceState == null) {
            animateIntro();
        }
    }

    private void extractIntent() {
        mGridSize = getIntent().getParcelableExtra(ARGS_GRID_SIZE);
    }


    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void initContent() {
        mPage = (StartLocationPage) findViewById(R.id.mainContent);
        mPage.setGridSize(mGridSize);
    }

    private void setupPageListener() {
        mPage.setListener(new StartLocationPage.Listener(){

            @Override
            public void onLocationChosen(Location location) {

            }
        });
    }

    private void animateIntro() {
        mPage.animateRevealContent(null);
    }

}
