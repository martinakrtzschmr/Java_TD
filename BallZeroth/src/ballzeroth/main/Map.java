package ballzeroth.main;

import ballzeroth.AEstrela.AEstrela;
import java.awt.*;
import java.util.ArrayList;

public class Map {

    public final int worldWidth;
    public final int worldHeight;
    public final int blockSize = 64; // Pixels de cada quadrado
    public static AEstrela estrela = new AEstrela();

    private ArrayList<Sounds> sounds;

    public Block[][] block;

    public Map(int worldHeight, int worldWidth, ArrayList<Sounds> sounds) {
        this.sounds = sounds;
        this.worldHeight = worldHeight;
        this.worldWidth = worldWidth;

        block = new Block[worldHeight][worldWidth];
        int width = (Screen.screenWidth / 2) - ((worldWidth * blockSize) / 2);

        for (int y = 0; y < block.length; y++) {
            for (int x = 0; x < block[0].length; x++) {
                block[y][x] = new Block(width + (x * blockSize), y * blockSize, blockSize, blockSize, SpriteIDs.roadID, 1);
            }
        }
    }

    public void calculatePath() {

    }

    public void physics() {

    }

    public void draw(Graphics g) {
        for (int y = 0; y < block.length; y++) {
            for (int x = 0; x < block[0].length; x++) {
                block[y][x].draw(g);
            }
        }
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }
}
