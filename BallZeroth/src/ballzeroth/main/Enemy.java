package ballzeroth.main;

import java.awt.*;

/**
 *
 * @author martin.akretzschmar
 */
public class Enemy extends Rectangle {

    private int xPos, yPos;
    private int enemySize = 64;
    private int enemyID = SpriteIDs.enemyOrcID;

    public int walkSpeed = 50, walkFrame = 0;

    public boolean inGame = false;

    public Enemy() {

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
    }
}
