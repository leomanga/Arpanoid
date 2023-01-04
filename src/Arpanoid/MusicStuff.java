
package Arpanoid;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class MusicStuff {
    Clip clip;
    public void playMusic(String musicLocation){
	try{
            File musicPath = new File("musiche\\"+musicLocation);
            if(musicPath.exists()){
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
		clip = AudioSystem.getClip();
		clip.open(audioInput);
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(-20);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
	}
	catch(Exception ex){
	    ex.printStackTrace();
	}
    }
    void stoppaMusica(){
        clip.stop();
    }
    
    public static void playSound(String nome, int sound){
        
        if(nome == "boing")
            nome = nome+((int)(Math.random()*5)+1);
        else if(nome == "rotturaBlocco")
            nome = nome+((int)(Math.random()*2)+1);
        else if(nome == "bordi")
            nome = nome+((int)(Math.random()*2)+1);
        try{
            File musicPath = new File("suoni\\"+nome+".wav");
            if(musicPath.exists()){
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
		Clip suono = AudioSystem.getClip();
		suono.open(audioInput);
                FloatControl volume = (FloatControl) suono.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(sound);
		suono.start();
            }
	}
	catch(Exception ex){
	    System.out.println("Errore");
	}
    }
}
