package ballzeroth.main;

import java.awt.*; // Importa todas as classes AWT para o projeto
import java.awt.image.*; // Importa todas as classes Image para o projeto
import javax.swing.*; // Importa todas as classes SWING para o projeto
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Screen extends JPanel implements Runnable {

    public static int screenWidth, screenHeight;
    private boolean running = false;
    public static boolean start = true;
    public static boolean debug = true;

    public Thread gameLoop = new Thread(this);

    public static Image[] tileset_ground = new Image[100];
    public static Image[] tileset_res = new Image[100];
    public static Image[] tileset_enemies = new Image[100];

    public static Map map;
    public static Store store;

    private ImageIcon image;
    public static Point mouse = new Point(0, 0);

    public static Enemy[] enemies = new Enemy[100];
    private int spawnTime = 2000;
    private int frames = 0;

    public static int health = 6;
    public static int coins = 100;
    public static int kill = 0, killToWin = 0, level = 1;

    private ArrayList<Sounds> sounds;

    public Screen(Frame frame, ArrayList<Sounds> sounds) {
        this.sounds = sounds;

        this.sounds.get(0).playSongs();
        frame.addMouseListener(new handler());
        frame.addMouseMotionListener(new handler());

        running = true;
        gameLoop.start();
    }

    // Paint and repaint the screen
    public void paintComponent(Graphics g) {
        if (start) {
            try {
                screenWidth = getWidth(); // function comes from JPanel extension
                screenHeight = getHeight(); // function comes from JPanel extension

                map = new Map(8, 12, sounds);
                store = new Store(this.health, this.coins);

                start();
                start = false;
            } catch (IOException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        g.setColor(new Color(50, 50, 50));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(0, 0, 0));

        map.draw(g); // Draw the map and update it

        for (int i = 0; i < enemies.length; i++) { // Draw the enemies
            if (enemies[i].inGame) {
                enemies[i].draw(g);
            }
        }

        store.draw(g); // Draw shop and update it

        if (Screen.store.healthPoints < 1) {
            g.setColor(new Color(255, 0, 0));
            g.fillRect(0, 0, screenWidth, screenHeight);
            g.setColor(new Color(255, 255, 255));
            g.setFont(new Font("Times New Roman", Font.BOLD, 14));
            g.drawString("PELA HORDA, ZUGZUG", screenWidth/2, screenHeight/2);
        }
    }

    public void start() throws FileNotFoundException, IOException {
        try {
            String linha = "";
            int counter = 0;

            FileReader file = new FileReader(SpriteIDs.mapsDIR);
            System.out.println("");
            BufferedReader sc = new BufferedReader(file);

            while ((linha = sc.readLine()) != null) {
                String caracteres[] = linha.split(" ");

                for (int i = 0; i < caracteres.length; i++) {
                    System.out.print(caracteres[i] + " ");
                }
                System.out.println("");

                for (int x = 0; x < map.worldWidth; x++) {
                    map.block[counter][x].terrainID = Integer.parseInt(caracteres[x]);
                }

                counter++;
            }

            // Render background tiles based on mapOne instructions
            for (int i = 0; i < tileset_ground.length; i++) {
                image = new ImageIcon(SpriteIDs.imagesDIR);
                //image = new ImageIcon(getClass().getClassLoader().getResource(SpriteIDs.imagesDIR));
                tileset_ground[i] = image.getImage();
                tileset_ground[i] = createImage(new FilteredImageSource(tileset_ground[i].getSource(), new CropImageFilter(0, 64 * i, 64, 64)));
            }

            tileset_res[0] = new ImageIcon(SpriteIDs.buttonDIR).getImage();
            tileset_res[1] = new ImageIcon(SpriteIDs.hearthDIR).getImage();
            tileset_res[2] = new ImageIcon(SpriteIDs.coinDIR).getImage();

            tileset_enemies[0] = new ImageIcon(SpriteIDs.enemyDIR).getImage();

            for (int i = 0; i < enemies.length; i++) {
                enemies[i] = new Enemy();
            }

            sc.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void enemySpawner() {
        // Tempo para spawnar um inimigo.
        if (frames >= spawnTime) {
            for (int i = 0; i < enemies.length; i++) {
                if (!enemies[i].inGame) {
                    enemies[i].spawn(i, 64, 1);
                    break;
                }
            }
            
            this.sounds.get(3).onceTime();

            frames = 0;
        } else {
            frames += 1;
        }
    }

    public void run() {
        while (running) {

            if (!start) {

                map.physics();
                enemySpawner();

                for (int i = 0; i < enemies.length; i++) {
                    if (enemies[i].inGame) {
                        enemies[i].physics();
                    }
                }
            }

            repaint();

            try {
                Thread.sleep(1); // Wait for a second?
            } catch (Exception e) {

            }
        }
    }
}
