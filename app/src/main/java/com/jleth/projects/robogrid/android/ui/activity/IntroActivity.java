package com.jleth.projects.robogrid.android.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.intro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_github) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/JeppeLeth/robogrid"));
            startActivity(browserIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
