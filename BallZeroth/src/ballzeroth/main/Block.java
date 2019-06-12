package ballzeroth.main;

import java.awt.Rectangle;
import java.awt.Graphics;

public class Block extends Rectangle {
    public Rectangle towerRange;
    public int towerRangeSize = 140;
    public int terrainID;

    public Block(int x, int y, int width, int height, int terrainID) {
        setBounds(x, y, width, height);
        towerRange = new Rectangle(x - (towerRangeSize / 2), y - (towerRangeSize / 2), width + towerRangeSize, height + towerRangeSize);
        this.terrainID = terrainID;
    }

    public void draw(Graphics g) {
        g.drawImage(Screen.tileset_ground[terrainID], x, y, width, height, null);
        
        if (this.terrainID == SpriteIDs.humanTowerOneID)
            g.drawRect(towerRange.x, towerRange.y, towerRange.width, towerRange.height);
    }
    
    public void attack (Graphics g) {
        if (Screen.debug && this.terrainID == SpriteIDs.humanTowerOneID)
            g.drawRect(towerRange.x, towerRange.y, towerRange.width, towerRange.height);        
    }
}
