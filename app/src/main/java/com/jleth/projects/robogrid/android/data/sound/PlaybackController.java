package com.jleth.projects.robogrid.android.data.sound;

/**
 * Interface controlling playback of sound clips, provided they are loaded and ready
 */
public interface PlaybackController {

    /**
     * Start playback of the given sound id. If the
     * sound clip for that id is not loaded or somehow cannot be played this method does nothing.
     *
     * @param soundId the key referencing the desired sound clip.
     * @return the streamId of the playing stream, or 0 if stream could not be created
     */
    int play(int soundId);

    /**
     * Pauses a given playback stream id. If the stream is not found or the stream is not playing,
     * this method does nothing.
     *
     * @param streamid stream ID
     */
    void pause(int streamid);

    /**
     * Resumes a given playback stream id. If the stream is not found or the stream is not paused,
     * this method does nothing.
     *
     * @param streamid stream ID
     */
    void resume(int streamid);

    /**
     * Stops a given playback stream id. If the stream is not found or the stream is not playing or
     * paused, this method does nothing.
     *
     * @param streamid stream ID
     */
    void stop(int streamid);
}
