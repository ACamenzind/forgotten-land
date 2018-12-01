package com.strategy.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

/**
 * Handles all the sounds
 */
public class SoundManager implements Disposable{
    private float masterVolume;
    private boolean muted;
    private ArrayList<Sound> loopingSounds;
    private long loopingID;
    private static final float BASE_EFFECT_VOLUME = 0.5f;
    private static final float BASE_BACKG_VOLUME = 0.3f;

    private static SoundManager instance = null;
    public static SoundManager instance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    private SoundManager() {
        this.masterVolume = 0.6f;
        this.loopingSounds = new ArrayList<Sound>();
    }

    /**
     * Refers to the different sounds.
     */
    public enum SoundType {
        PLACE_BUILDING, FAIL_TO_PLACE_BUILDING,
        BACKGROUND, BUILDING_DEMOLITION
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
        for (Sound sound : loopingSounds) {
            sound.setVolume(loopingID, BASE_BACKG_VOLUME * masterVolume);
        }
    }

    /**
     * Selects the sound to play given the type
     * @param type the sound to play (from the values of the enum)
     */
    public void playSound(SoundType type) {
        switch (type) {
            case PLACE_BUILDING:
                play(Assets.hit, BASE_EFFECT_VOLUME, 0.75f, false);
                break;
            case FAIL_TO_PLACE_BUILDING:
                play(Assets.hit, BASE_EFFECT_VOLUME, 5f, false);
                break;
            case BACKGROUND:
                play(Assets.bgSound, BASE_BACKG_VOLUME, 1f, true);
                break;
            case BUILDING_DEMOLITION:
                play(Assets.demolition, 0.6f, 1f, false);
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
        if (looping) {
            loopingSounds.add(sound);
            loopingID = id;
        }
    }

    @Override
    public void dispose() {
        // Dispose all the looping sounds
        for (Sound sound : loopingSounds) {
            sound.dispose();
        }
    }
}
