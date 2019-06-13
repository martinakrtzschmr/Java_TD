package ballzeroth.main;

import java.awt.*;

/**
 *
 * @author martin.akretzschmar
 */
public class Enemy extends Rectangle {
    
    private int enemyID; // SpriteIDs.enemyHumanID -- SpriteIDs.enemyOrcID
    
    private int xPos, yPos;
    public int health, enemySize = 64, enemySpeed = 0;
    public int walkSpeed = 50, walkFrame = 0;
    
    private int up = 0, down = 1, right = 2, left = 3, direction = right;

    public boolean inGame = false;

    public Enemy() {
        // On Spawn
    }
    
    public void spawn(int enemyID) {
        for (int i = 0; i < Screen.map.block.length; i++) {
            if (Screen.map.block[i][0].terrainID == SpriteIDs.roadID) {
                setBounds(Screen.map.block[i][0].x, Screen.map.block[i][0].y, enemySize, enemySize);
                xPos = 0;
                yPos = y;
            }
        }

        this.enemyID = enemyID;
        this.health = this.enemySize;

        inGame = true;
    }

    public void physics() {
        if (walkFrame >= walkSpeed) {
            this.x += 1;

            walkFrame = 0;
        } else {
            walkFrame += 1;
        }
    }

    public void draw(Graphics g) {
        // preciso das imagens para inimigos
        g.drawImage(Screen.tileset_enemies[0], x, y, width, height, null);
        
        // Barra de vida
        g.setColor(new Color(200, 0, 0));
        g.fillRect(x, y - 10, this.health, 6);
    }
    
    public void loseHealth(int hit) {
        this.health -= hit;
        
        if (this.health <= 0) {
            this.death();
        }
    }
    
    public void death() {
        this.inGame = false;
    }
    
    public void dealDamage(int hit) {
        Screen.health -= hit;
    }
}
