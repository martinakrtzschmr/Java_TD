package ballzeroth.main;

import java.awt.*;

public class Block extends Rectangle {

    public Rectangle towerRange;
    public int towerRangeSize = 140;
    public int terrainID;

    public Enemy enemy = null;
    public boolean shooting = false;
    public int dmgTime = 1000, dmgFrame = 0;

    public int shotEnemy = -1;
    public int coinReward = 10;

    public Block(int x, int y, int width, int height, int terrainID) {
        setBounds(x, y, width, height);
        this.towerRange = new Rectangle(x - (towerRangeSize / 2), y - (towerRangeSize / 2), width + towerRangeSize, height + towerRangeSize);
        this.terrainID = terrainID;
    }

    public void draw(Graphics g) {
        g.drawImage(Screen.tileset_ground[terrainID], x, y, width, height, null);

      //  if (this.terrainID == SpriteIDs.humanTowerOneID) {
      //      g.drawRect(towerRange.x, towerRange.y, towerRange.width, towerRange.height);
      //  }
    }

    public void attack(Graphics g) {
       // if (Screen.debug && this.terrainID == SpriteIDs.humanTowerOneID) {
       //     g.drawRect(towerRange.x, towerRange.y, towerRange.width, towerRange.height);
       // }

        if (this.shooting) {
            g.setColor(new Color(0, 0, 255));
            g.drawLine(x + (width / 2), y + (height / 2), this.enemy.x + (this.enemy.width / 2), this.enemy.y + +(this.enemy.height / 2));
        }
    }

    public void physics() {
        if (this.enemy != null && this.towerRange.intersects(enemy)) {
            shooting = true;
        } else {
            shooting = false;
        }

        if (!shooting) {
            // Melhorar ao construir uma lista que utiliza:
            // towers.contains(this.terrainID)
            if (this.terrainID == SpriteIDs.humanTowerOneID
                    || this.terrainID == SpriteIDs.humanTowerTwoID
                    || this.terrainID == SpriteIDs.humanTowerThreeID
                    || this.terrainID == SpriteIDs.orcTowerOneID
                    || this.terrainID == SpriteIDs.orcTowerTwoID
                    || this.terrainID == SpriteIDs.orcTowerThreeID) {
                // Verifica todos os inimigos ao entrarem na zona do retangulo da torre
                for (int i = 0; i < Screen.enemies.length; i++) {
                    Enemy aux = Screen.enemies[i]; // FIX: Test first and last enemy on range
                    if (aux.inGame && this.towerRange.intersects(aux)) {
                        this.shooting = true;
                        this.enemy = aux;
                    }
                }
            }
        }

        if (shooting) {
            if (dmgFrame >= dmgTime) {
                switch (this.terrainID) {
                    case SpriteIDs.humanTowerOneID:
                        enemy.loseHealth(1);
                        break;
                    case SpriteIDs.humanTowerTwoID:
                        enemy.loseHealth(3);
                        break;
                    case SpriteIDs.humanTowerThreeID:
                        enemy.loseHealth(6);
                        break;
                    default:
                        enemy.loseHealth(0);
                        break;
                }
                dmgFrame = 0;
            } else {
                dmgFrame++;
            }

            if (!enemy.inGame) {
                Screen.coins += coinReward;
                this.shooting = false;
                this.enemy = null;

            }
        }

    }

}
