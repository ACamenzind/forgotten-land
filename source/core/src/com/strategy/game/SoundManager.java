package com.strategy.game;

import com.badlogic.gdx.audio.Sound;

/**
 * Handles all the sounds
 */
public class SoundManager {
    private float masterVolume;
    private boolean muted;

    public SoundManager() {
        this.masterVolume = 1.0f;
    }

    /**
     * Refers to the different sounds.
     */
    public enum SoundType {
        PLACE_BUILDING, FAIL_TO_PLACE_BUILDING,
        BACKGROUND
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public float getMasterVolume() {
        return masterVolume;
    }

    public void setMasterVolume(float masterVolume) {
        this.masterVolume = masterVolume;
    }

    /**
     * Selects the sound to play given the type
     * @param type the sound to play (from the values of the enum)
     */
    public void playSound(SoundType type) {
        switch (type) {
            case PLACE_BUILDING:
                play(Assets.hit, 0.5f, 0.75f, false);
                break;
            case FAIL_TO_PLACE_BUILDING:
                play(Assets.hit, 0.5f, 5f, false);
                break;
            case BACKGROUND:
                play(Assets.bgSound, 0.3f, 1f, true);
                break;
        }
    }

    /**
     * Plays the given sound file with the given properties
     * @param file the file path
     * @param volume the volume (1.0f is the base)
     * @param pitch the pitch/frequency of the sound
     * @param looping sets whether it should loop or not
     */
    private void play(Sound file, float volume, float pitch, boolean looping) {
        Sound sound = file;
        long id = sound.play(volume * masterVolume);
        sound.setPitch(id, pitch);
        sound.setLooping(id, looping);
    }
}
