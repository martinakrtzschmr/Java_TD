package ballzeroth;

import java.awt.*;

public class Map {
    public final int worldWidth   = 12; 
    public final int worldHeight  = 8; 
    public final int blockSize    = 64; // Pixels de cada quadrado
    
    public Block[][] block;
    
    public Map () {
        block = new Block[worldHeight][worldWidth];
        int width = (Screen.screenWidth / 2) - ((worldWidth * blockSize) / 2);
        
        for (int y = 0; y < block.length; y++) {
            for (int x = 0; x < block[0].length; x++) {
                block[y][x] = new Block(width + (x * blockSize), y * blockSize, blockSize, blockSize, SpriteIDs.groundID, SpriteIDs.airID);
            }
        }
    }
    
    public void physics ()  {
        
    }
    
    public void draw (Graphics g) {
        for (int y = 0; y < block.length; y++) {
            for (int x = 0; x < block[0].length; x++) {
                block[y][x].draw(g);
            }
        }
    }
}
