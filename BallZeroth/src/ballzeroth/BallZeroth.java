package ballzeroth;

import javax.swing.JFrame;
import java.awt.*;

public class BallZeroth extends JFrame {
    public static String title = "BallZeroth!";
    public static Dimension size = new Dimension(800, 600);
    
    public void init () {
        this.setLayout(new GridLayout(1,1,0,0));
        Screen screen = new Screen(); // Objeto que extende a classe JPanel
        this.add(screen);
        
        this.setVisible(true);
    }
    
    // Utilize "this." para verificar as funções extendidas pelo JFrame
    public BallZeroth () {
        this.setTitle(title);
        this.setSize(size);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        init();
    }
    
    public static void main(String[] args) {
        BallZeroth game = new BallZeroth();
    }
}
