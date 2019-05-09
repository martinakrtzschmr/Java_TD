package ballzeroth;

import javax.swing.JFrame;
import java.awt.*;

public class BallZeroth extends JFrame {
    public static String title = "BallZeroth!";
    public static Dimension size = new Dimension(800, 600);
    
    public void init () {
        
    }
    
    public BallZeroth () {
        setTitle(title);
        setSize(size);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setVisible(true);
        
        init();
    }
    
    public static void main(String[] args) {
        BallZeroth game = new BallZeroth();
    }
}
