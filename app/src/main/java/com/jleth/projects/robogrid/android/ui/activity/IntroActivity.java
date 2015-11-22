package com.jleth.projects.robogrid.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.jleth.projects.robogrid.android.R;
import com.jleth.projects.robogrid.android.data.sound.PlaybackController;
import com.jleth.projects.robogrid.android.data.sound.SoundManager;
import com.jleth.projects.robogrid.android.data.sound.Sounds;
import com.jleth.projects.robogrid.android.data.storage.Prefs;
import com.jleth.projects.robogrid.android.model.Size;

/**
 * Entry point for this app
 */
public class IntroActivity extends AppCompatActivity {
    private static final int REQ_CODE_INTERACT = 9001;

    private static final String TAG = "IntroActivity";

    private PlaybackController mPlaybackController;
    private IntroPage mPage;
    private Prefs mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);

        initPlaybackController();
        initPrefs();
        initContent();

        setupPageListener();

        if (savedInstanceState == null) {
            animateIntro();
        }
    }

    private void initPrefs() {
        mPrefs = Prefs.getInstance(this);
    }

    private void initPlaybackController() {
        mPlaybackController = new SoundManager.Builder(this, Sounds.SOUND_MAP).build();
    }

    private void initContent() {
        mPage = (IntroPage) findViewById(R.id.mainContent);
        mPage.setInitialSelection(mPrefs.getLastGridSize());
    }

    private void setupPageListener() {
        mPage.setListener(new IntroPage.Listener() {
            @Override
            public void onImageClick() {
                mPlaybackController.play(Sounds.SOUND_ID_SHAKE);
                mPage.animateAttentionImage();
            }

            @Override
            public void onStartClick(@NonNull final Size size) {
                mPage.animateDisappearContent(new Runnable() {
                    @Override
                    public void run() {
                        mPrefs.saveLastGridSize(size);
                        startActivityForResult(StartLocationActivity.newIntent(mPage.getContext(), size), REQ_CODE_INTERACT);
                        overridePendingTransition(0, 0);
                    }
                });

            }
        });
    }

    private void animateIntro() {
        mPage.animateRevealContent(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_INTERACT) {
            mPage.animateRevealContent(null);
        }
    }
}
