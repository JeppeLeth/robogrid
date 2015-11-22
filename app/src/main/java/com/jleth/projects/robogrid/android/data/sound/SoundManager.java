package com.jleth.projects.robogrid.android.data.sound;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.SparseIntArray;

/**
 * A manager for sound clip playback control
 */
public class SoundManager implements PlaybackController {

    private static final int DEFAULT_STREAM_PRIORITY = 1;

    private static final int INVALID_SAMPLE_ID = -1;

    private final SparseIntArray mSoundIds;

    private final SoundPool mSoundPool;

    protected SoundManager(SoundPool soundPool, SparseIntArray soundIds) {
        mSoundPool = soundPool;
        mSoundIds = soundIds;
    }

    @Override
    public int play(int soundId) {
        if (isInitialized()) {
            int sampleId = mSoundIds.get(soundId, INVALID_SAMPLE_ID);
            if (sampleId != INVALID_SAMPLE_ID) {
                return mSoundPool.play(sampleId, 1.0f, 1.0f, DEFAULT_STREAM_PRIORITY, 0, 1.0f);
            }
        }
        return 0;
    }

    @Override
    public void pause(int streamid) {
        if (isInitialized()) {
            mSoundPool.pause(streamid);
        }
    }

    @Override
    public void resume(int streamid) {
        if (isInitialized()) {
            mSoundPool.resume(streamid);
        }
    }

    @Override
    public void stop(int streamid) {
        if (isInitialized()) {
            mSoundPool.stop(streamid);
        }
    }

    private boolean isInitialized() {
        if (mSoundPool == null || mSoundIds == null) {
            return false;
        }
        return true;
    }


    /**
     * Builds a {@link SoundManager} based on given sound resource mapping
     */
    public static class Builder {
        private static final int DEFAULT_MAX_STREAMS = 2;
        private static final int SOUND_PRIORITY_COMPATIBILITY = 1;

        private final SparseIntArray mResMap;
        private int mMaxStreams = DEFAULT_MAX_STREAMS;
        private SoundPool mSoundPool;
        private Context mContext;

        public Builder(Context context, SparseIntArray resMap) {
            mContext = context;
            mResMap = resMap;
        }

        public Builder setMaxStreams(int maxStreams) {
            mMaxStreams = maxStreams;
            return this;
        }

        public Builder setSoundPool(SoundPool pool) {
            mSoundPool = pool;
            return this;
        }

        public SoundManager build() {
            /* default is a notification type pool */
            if (mSoundPool == null) {
                mSoundPool = createNotificationsSoundPool(mMaxStreams);
            }

            SparseIntArray soundIds = loadSounds(mContext, mSoundPool, mResMap);

            return new SoundManager(mSoundPool, soundIds);
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private SoundPool createNotificationsSoundPool(int maxStreams) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                AudioAttributes attr = new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                        .build();
                return new SoundPool.Builder()
                                .setAudioAttributes(attr)
                                .setMaxStreams(maxStreams).build();
            } else {
                //noinspection deprecation
                return new SoundPool(maxStreams, AudioManager.STREAM_NOTIFICATION, 0);
            }
        }

        private SparseIntArray loadSounds(Context context, SoundPool pool, SparseIntArray resMap) {
            if (context == null || pool == null || resMap == null) {
                return null;
            }

            /* load and map sound clips */
            SparseIntArray soundIds = new SparseIntArray();

            int key = 0;
            for(int i = 0; i < resMap.size(); i++) {
                key = resMap.keyAt(i);
                int res = resMap.get(key);
                int soundId = pool.load(context, res, SOUND_PRIORITY_COMPATIBILITY);

                soundIds.put(key, soundId);
            }
            return soundIds;
        }
    }
}