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
    public int walkSpeed = 1, walkFrame = 0, walkEnemy = 0;

    public int up = 0, down = 1, right = 2, left = 3, direction = right;

    public boolean inGame = false;
    public boolean isUp = false;
    public boolean isDown = false;
    public boolean isRight = false;
    public boolean isLeft = false;

    public Enemy() {
        // On Spawn
    }

    public void spawn(int enemyID) {
        for (int i = 0; i < Screen.map.block.length; i++) {
            if (Screen.map.block[i][0].terrainID == SpriteIDs.roadID) {
                setBounds(Screen.map.block[i][0].x, Screen.map.block[i][0].y, enemySize, enemySize);
                xPos = 0;
                yPos = i;
            }
        }

        this.enemyID = enemyID;
        this.health = this.enemySize;

        inGame = true;
    }

    public void physics() {
        if (walkFrame >= walkSpeed) {
            if (direction == right) {
                x += 1;
            } else if (direction == up) {
                y -= 1;

            } else if (direction == down) {
                y += 1;

            }
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

                    playerHealth();
                    enemyDied();
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

    public void playerHealth() {
        Screen.store.healthPoints -= 1;
    }

    public void enemyDied() {
        inGame = false;
    }

    public void death() {
        Screen.store.goldPoints += 10;

    }

    public void dealDamage(int hit) {
        Screen.health -= hit;
    }
}
