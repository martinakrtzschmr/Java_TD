package ballzeroth.main;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;

public class Block extends Rectangle {
    public Rectangle towerRange;
    public int towerRangeSize = 140;
    public int terrainID;
    
    public Enemy enemy = null;
    public boolean shooting = false;

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
        
        if (this.shooting) {
            g.setColor(new Color(255, 255, 0));
            g.drawLine(x + (width / 2), y + (height / 2), this.enemy.x + (this.enemy.width / 2), this.enemy.y + + (this.enemy.height / 2));
        }
    }
    
    public void physics () {
        this.shooting = false;
        // Melhorar ao construir uma lista que utiliza:
        // towers.contains(this.terrainID)
        if (this.terrainID == SpriteIDs.humanTowerOneID || 
            this.terrainID == SpriteIDs.humanTowerTwoID || 
            this.terrainID == SpriteIDs.humanTowerThreeID ||
            this.terrainID == SpriteIDs.orcTowerOneID ||
            this.terrainID == SpriteIDs.orcTowerTwoID ||
            this.terrainID == SpriteIDs.orcTowerThreeID 
            ) {
            // Verifica todos os inimigos ao entrarem na zona do retangulo da torre
            for (int i = 0; i < Screen.enemies.length; i++) {
                Enemy aux = Screen.enemies[0]; // FIX: Test first and last enemy on range
                if (aux.inGame && this.towerRange.intersects(aux)) {
                    this.shooting = true;
                    enemy = aux;
                }
            }
        }
    }
}
