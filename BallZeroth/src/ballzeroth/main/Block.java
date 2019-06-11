package ballzeroth.main;

import java.awt.Rectangle;
import java.awt.Graphics;

public class Block extends Rectangle {

    public int terrainID;
    public int airID;

    public Block(int x, int y, int width, int height, int terrainID, int airID) {
        setBounds(x, y, width, height);
        this.terrainID = terrainID;
        this.airID = airID;
    }

    public void draw(Graphics g) {
        g.drawImage(Screen.tileset_ground[terrainID], x, y, width, height, null);
        // g.drawRect(x, y, width, height);
        //if(airID != Value.airAir){
        //    g.drawImage(Screen.tileset_air[airID], x, y, width, height, null);
        //}
    }
}
