package ballzeroth.main;

import java.awt.Rectangle;
import java.awt.Graphics;

public class Block extends Rectangle {

    public int terrainID;
    public int airID;

    public Block(int x, int y, int width, int height, int terrainID) {
        setBounds(x, y, width, height);
        this.terrainID = terrainID;
    }

    public void draw(Graphics g) {
        g.drawImage(Screen.tileset_ground[terrainID], x, y, width, height, null);
    }
}
