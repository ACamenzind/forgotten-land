package com.strategy.game;

import com.badlogic.gdx.audio.Sound;

/**
 * Handles all the sounds
 */
public class SoundManager {
    private float masterVolume;

    public SoundManager() {
        this.masterVolume = 1.0f;
    }


    public enum SoundType {
        PLACE_BUILDING, FAIL_TO_PLACE_BUILDING,
        BACKGROUND
    }

    public float getMasterVolume() {
        return masterVolume;
    }

    public void setMasterVolume(float masterVolume) {
        this.masterVolume = masterVolume;
    }

    public void playSound(SoundType type) {
        Sound sound;
        long id;
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

    private void play(Sound file, float volume, float pitch, boolean looping) {
        Sound sound = file;
        long id = sound.play(volume * masterVolume);
        sound.setPitch(id, pitch);
        sound.setLooping(id, looping);
    }
}
