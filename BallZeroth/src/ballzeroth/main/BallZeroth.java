package ballzeroth.main;

import java.awt.*;
import java.net.*;
import java.util.*;
import javax.swing.JFrame;

/*
VICTOR CARVALHO
MARTIN ALEXANDER
FELIPE ANDREY
3 SEMESTRE - JOGOS DIGITAIS
 */
public class BallZeroth extends JFrame {

    public static String title = "BallZeroth!";
    public static Dimension size = new Dimension(800, 600);

    public void init() throws MalformedURLException {

        ArrayList<Sounds> sounds = new ArrayList();
        loadSounds(sounds);

        this.setLayout(new GridLayout(1, 1, 0, 0));
        Screen screen = new Screen(this, sounds); // Objeto que extende a classe JPanel
        this.add(screen);

        this.setVisible(true);
    }

    public static void loadSounds(ArrayList<Sounds> sounds) throws MalformedURLException {
        sounds.add(new Sounds(SpriteIDs.mainMusic));
        sounds.add(new Sounds(SpriteIDs.nature));
        sounds.add(new Sounds(SpriteIDs.wave));
        sounds.add(new Sounds(SpriteIDs.zugzug));

    }

    // Utilize "this." para verificar as funções extendidas pelo JFrame
    public BallZeroth() throws MalformedURLException {

        this.setTitle(title);
        this.setSize(size);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
    }

    public static void main(String[] args) throws MalformedURLException {

        BallZeroth game = new BallZeroth();
    }
}
