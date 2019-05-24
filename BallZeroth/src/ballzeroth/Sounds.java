package ballzeroth;

import java.io.*;
import java.net.*;
import javax.swing.*;


/**
 *
 * reference http://journals.ecs.soton.ac.uk/java/tutorial/applet/ui/sound.html
 * 
 */
public class Sounds extends JApplet{
    private java.applet.AudioClip audio;
    private URL song;
    
    public Sounds (String path) throws MalformedURLException{
        song = new File(path).toURI().toURL();
        audio = java.applet.Applet.newAudioClip(song);
    }
    
    public void onceTime(){
        audio.play();
    }
    
    public void stopSongs(){
        audio.stop();
    }
    
    public void playSongs(){
        audio.loop();
    }
    
}
