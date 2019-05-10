package ballzeroth;

import java.awt.*; // Importa todas as classes AWT para o projeto
import java.awt.image.*; // Importa todas as classes Image para o projeto
import javax.swing.*; // Importa todas as classes SWING para o projeto
import java.io.*;

public class Screen extends JPanel implements Runnable {
    public Thread gameLoop = new Thread(this);
    
    private boolean running = false;
    
    public static Image[] tileset_ground = new Image[6];
    public static Image[] tileset_air = new Image[6];
    public static Map map;
    public static MapConstruct mapConstruct;
    public static int screenWidth, screenHeight;
    public static boolean start = true;
    private ImageIcon image;
    
    public Screen() {
        setBackground(Color.BLUE);
        running = true;
        gameLoop.start();
    }
    
    // Paint and repaint the screen
    public void paintComponent (Graphics g) {
        if(start) {
            start();
            start = false;
        }
        g.clearRect(0, 0, getWidth(), getHeight());
        map.draw(g); // Draw the map and update it
    }
    
    public void start () {
        screenWidth = getWidth(); // function comes from JPanel extension
        screenHeight = getHeight(); // function comes from JPanel extension
        
        map = new Map();
        
        for (int i = 0; i < tileset_ground.length; i++) {
            image = new ImageIcon(getClass().getClassLoader().getResource(SpriteIDs.imagesDIR));
            tileset_ground[i] = image.getImage();
            tileset_ground[i] = createImage( new FilteredImageSource(tileset_ground[i].getSource(), new CropImageFilter(0, 64 * i, 64, 64)) );
        }
        
        mapConstruct.loadMap(new File(SpriteIDs.mapsDIR + "map.gamemap"));
    }
    
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
    
        while(running) {
            if(!start){
                
                
            }
            
            repaint();
            
            try {
                Thread.sleep(1); // Wait for a second?
            } catch (Exception e) {
                
            }
        }
    }
}
