package ballzeroth.main;

import java.awt.*;

/**
 *
 * @author martin.akretzschmar
 */
public class Enemy extends Rectangle {

    private int enemyID; // SpriteIDs.enemyHumanID -- SpriteIDs.enemyOrcID

    private int xPos,
            yPos;
    public int health,
            armor;
    public int enemySize = 64,
            enemySpeed = 0;

    public int walkSpeed = 5,
            walkFrame = 0,
            walkEnemy = 0;

    public int up = 0, down = 1, right = 2, left = 3, direction = right;
    public String[] sprites;
    public boolean inGame = false;
    public boolean isUp = false;
    public boolean isDown = false;
    public boolean isRight = false;
    public boolean isLeft = false;

    public Enemy() {
        // On Spawn
    }

    public void spawn(int enemyID, int health, int armor, String[] images) {
        for (int i = 0; i < Screen.map.block.length; i++) {
            if (Screen.map.block[i][0].terrainID == SpriteIDs.roadID) {
                setBounds(Screen.map.block[i][0].x, Screen.map.block[i][0].y, enemySize, enemySize);
                xPos = 0;
                yPos = i;
            }
        }

        this.enemyID = enemyID;
        this.health = health;
        this.armor = armor;
        this.sprites = images;
        inGame = true;
    }

    public void physics() {
        if (walkFrame >= walkSpeed) {
            walkEnemy += 1;

            if (walkEnemy == Screen.map.blockSize) {
                if (direction == right) {
                    xPos += 1;
                    isRight = true;
                } else if (direction == up) {
                    yPos -= 1;
                    isUp = true;
                } else if (direction == down) {
                    yPos += 1;
                    isDown = true;
                } else if (direction == left) {
                    xPos -= 1;
                    isLeft = true;
                }

                if (!isUp) {
                    try {
                        if (Screen.map.block[yPos + 1][xPos].terrainID == SpriteIDs.roadID) {
                            direction = down;
                        }
                    } catch (Exception e) {
                    }
                }
                if (!isDown) {
                    try {
                        if (Screen.map.block[yPos - 1][xPos].terrainID == SpriteIDs.roadID) {
                            direction = up;
                        }
                    } catch (Exception e) {
                    }
                }
                if (!isLeft) {
                    try {
                        if (Screen.map.block[yPos][xPos + 1].terrainID == SpriteIDs.roadID) {
                            direction = right;
                        }
                    } catch (Exception e) {
                    }
                }
                if (!isRight) {
                    try {
                        if (Screen.map.block[yPos][xPos - 1].terrainID == SpriteIDs.roadID) {
                            direction = left;
                        }
                    } catch (Exception e) {
                    }
                }

                if (Screen.map.block[yPos][xPos].terrainID == SpriteIDs.endTowerMid) {
                    playerHealth(this.armor);
                    death();
                }

                isDown = false;
                isUp = false;
                isRight = false;
                isLeft = false;
                walkEnemy = 0;
            }

            walkFrame = 0;
        } else {
            walkFrame += 1;
        }
    }

    public void draw(Graphics g) {
        // preciso das imagens para inimigos
        if (direction == right) {
            g.drawImage(Screen.tileset_enemies[3], x, y, width, height, null);
        } else if (direction == up) {
            g.drawImage(Screen.tileset_enemies[2], x, y, width, height, null);
        } else if (direction == down) {
            g.drawImage(Screen.tileset_enemies[0], x, y, width, height, null);
        } else if (direction == left) {
            g.drawImage(Screen.tileset_enemies[1], x, y, width, height, null);
        }

        // Barra de vida
        g.setColor(new Color(200, 0, 0));
        g.fillRect(x, y - 10, this.health, 6);
    }

    public void loseHealth(int hit) {
        this.health -= (hit - this.armor);

        if (this.health <= 0) {
            this.death();
        }
    }

    public void playerHealth(int damagePlus) {
        Screen.store.healthPoints -= (1 + damagePlus);
    }

    public void death() {
        Screen.store.goldPoints += 10;
        this.inGame = false;
        this.direction = right;
        this.walkEnemy = 0;
    }

    public void dealDamage(int hit) {
        Screen.health -= hit;
    }
}
