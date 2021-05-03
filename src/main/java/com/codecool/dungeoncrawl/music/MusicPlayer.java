package com.codecool.dungeoncrawl.music;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;

public class MusicPlayer {

    public static void playMusic() {
        AudioClip mediaPlayer;
        File file = new File("src/main/resources/track1.mp3");
        Media h = new Media(file.toURI().toString());
        mediaPlayer = new AudioClip(h.getSource());
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();
    }

    public static void stopMusic() {
        AudioClip mediaPlayer;
        File file = new File("src/main/resources/track1.mp3");
        Media h = new Media(file.toURI().toString());
        mediaPlayer = new AudioClip(h.getSource());
        mediaPlayer.setVolume(0.2);
        mediaPlayer.stop();
    }

    public static void playDrinkingSound(){
        AudioClip mediaPlayer;
        File file = new File("src/main/resources/drinking.mp3");
        Media h = new Media(file.toURI().toString());
        mediaPlayer = new AudioClip(h.getSource());
        mediaPlayer.play();
    }

    public static void playAttackSound(){
        AudioClip mediaPlayer;
        File file = new File("src/main/resources/attack.mp3");
        Media h = new Media(file.toURI().toString());
        mediaPlayer = new AudioClip(h.getSource());
        mediaPlayer.play();
    }

//    public static void playWalkingSound(){
//        AudioClip mediaPlayer;
//        File file = new File("src/main/resources/walking.mp3");
//        Media h = new Media(file.toURI().toString());
//        mediaPlayer = new AudioClip(h.getSource());
//        mediaPlayer.setVolume(0.5);
//        mediaPlayer.play();
//    }

    public static void playEatingSound(){
        AudioClip mediaPlayer;
        File file = new File("src/main/resources/eating.mp3");
        Media h = new Media(file.toURI().toString());
        mediaPlayer = new AudioClip(h.getSource());
        mediaPlayer.play();
    }

    public static void playUnlockSound(){
        AudioClip mediaPlayer;
        File file = new File("src/main/resources/unlock.mp3");
        Media h = new Media(file.toURI().toString());
        mediaPlayer = new AudioClip(h.getSource());
        mediaPlayer.play();
    }

    public static void playKeySound(){
        AudioClip mediaPlayer;
        File file = new File("src/main/resources/key.mp3");
        Media h = new Media(file.toURI().toString());
        mediaPlayer = new AudioClip(h.getSource());
        mediaPlayer.play();
    }

    public static void playUpgradeSound(){
        AudioClip mediaPlayer;
        File file = new File("src/main/resources/upgrade.mp3");
        Media h = new Media(file.toURI().toString());
        mediaPlayer = new AudioClip(h.getSource());
        mediaPlayer.play();
    }

    public static void playGlassSound(){
        AudioClip mediaPlayer;
        File file = new File("src/main/resources/glass.mp3");
        Media h = new Media(file.toURI().toString());
        mediaPlayer = new AudioClip(h.getSource());
        mediaPlayer.play();
    }

    public static void playEndGameSound() {
        AudioClip mediaPlayer;
        File file = new File("src/main/resources/gameOver.mp3");
        Media h = new Media(file.toURI().toString());
        mediaPlayer = new AudioClip(h.getSource());
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();
    }
}