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

    public ArrayList<Sounds> sounds = loadSounds();

    public void init() {
        this.setLayout(new GridLayout(1, 1, 0, 0));
        Screen screen = new Screen(this); // Objeto que extende a classe JPanel
        this.add(screen);

        this.setVisible(true);
    }

    public static ArrayList<Sounds> loadSounds() throws MalformedURLException {
        ArrayList<Sounds> sounds = new ArrayList(); //exemplo para teste
        sounds.add(new Sounds(SpriteIDs.mainMusic));
        return sounds;
    }

    // Utilize "this." para verificar as funções extendidas pelo JFrame
    public BallZeroth() throws MalformedURLException {

        sounds.get(0).playSongs();

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
