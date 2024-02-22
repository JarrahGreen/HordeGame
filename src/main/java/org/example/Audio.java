/*
package org.example;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;





public class Audio {
    Clip clip;


    AudioInputStream audioInputStream;
    static public String filePath;

    public Audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        clip = AudioSystem.getClip();

        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);


    }

    public static void runFirst() {
        try {
            filePath = "";
            Audio audioPlayer = new Audio();
            audioPlayer.play();

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

}
*/