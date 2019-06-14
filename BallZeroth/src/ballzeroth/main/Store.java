
package ballzeroth.main;

import java.awt.*;

/**
 *
 * @author martin.akretzschmar
 */
public class Store {

    public static int shopItens = 7;
    public static int buttonSize = 64;
    private int storeAlign = 40;
    private int HUDIconSize = 20;
    private int storeWidth;
    private int storeHeight;
    private int buttonSpace = 25;

    private boolean itemHold = false;
    private int heldId = -1, auxID = -1;

    public static int[] buttonID = {        // POS--ID
        SpriteIDs.humanTowerOneID,          // 0  - 6
        SpriteIDs.humanTowerOneIDMOUSE,     // 1  - 7
        SpriteIDs.humanTowerOneIDHUD,       // 2  - 8
        SpriteIDs.humanTowerTwoID,          // 3  - 9
        SpriteIDs.humanTowerTwoIDMOUSE,     // 4  - 10
        SpriteIDs.humanTowerTwoIDHUD,       // 5  - 11
        SpriteIDs.humanTowerThreeID,        // 6  - 12
        SpriteIDs.humanTowerThreeIDMOUSE,   // 7  - 13
        SpriteIDs.humanTowerThreeIDHUD,     // 8  - 14
        SpriteIDs.orcTowerOneID,            // 9  - 15
        SpriteIDs.orcTowerOneIDMOUSE,       // 10 - 16
        SpriteIDs.orcTowerOneIDHUD,         // 11 - 17
        SpriteIDs.orcTowerTwoID,            // 12 - 18
        SpriteIDs.orcTowerTwoIDMOUSE,       // 13 - 19
        SpriteIDs.orcTowerTwoIDHUD,         // 14 - 20
        SpriteIDs.orcTowerThreeID,          // 15 - 21
        SpriteIDs.orcTowerThreeIDMOUSE,     // 16 - 22
        SpriteIDs.orcTowerThreeIDHUD,       // 17 - 23
        SpriteIDs.trash,                    // 18 - 28
        SpriteIDs.trashBig                  // 19 - 29
    };
    public static int[] towerPrice = {
        15, 25, 35, 600, 650, 700, 0
    };

    public Rectangle[] buttons = new Rectangle[shopItens];
    public Rectangle health;
    public int healthPoints;
    public Rectangle gold;
    public int goldPoints;

    public boolean holdsItem = false;

    public Store(int health, int coins) {
        for (int i = 0; i < buttons.length; i++) {
            storeWidth = (Screen.screenWidth / 2) - (shopItens * (buttonSize) / 2);
            storeHeight = Screen.map.block[Screen.map.worldHeight - 1][0].y + (buttonSize * 2);

            buttons[i] = new Rectangle(storeWidth + ((buttonSize + buttonSpace) * i), storeHeight + 10, buttonSize, buttonSize);
        }

        // Sprites
        this.health = new Rectangle(Screen.map.block[0][0].x, buttons[0].y, HUDIconSize, HUDIconSize);
        this.gold = new Rectangle(Screen.map.block[0][0].x, buttons[0].y + buttons[0].height - HUDIconSize, HUDIconSize, HUDIconSize);

        // Values
        this.healthPoints = health;
        this.goldPoints = coins;
    }

    public void draw(Graphics g) {
        int x = 0, y = 0;
        
        for (int i = 0; i < buttons.length; i++) { // 0 - 6
            x = buttons[i].x;       // Posição X
            y = buttons[i].y - 52;  // Posição Y
            
            // Verificar se botoes contem o mouse acima deles
            if (buttons[i].contains(Screen.mouse)) {
                g.drawImage(
                    Screen.tileset_ground[buttonID[i * 3] + 2], 
                    x, 
                    y, 
                    buttons[i].width, 
                    buttons[i].height, 
                    null
                );
            } else {
                g.drawImage(
                    Screen.tileset_ground[buttonID[i * 3] + 1], 
                    x, 
                    y, 
                    buttons[i].width, 
                    buttons[i].height, 
                    null
                );
            }

            if (towerPrice[i] > 0) {
                g.setColor(Color.white);
                switch (i) {
                    case 0:
                        g.drawString("$" + towerPrice[i] + " - Atk. 6", buttons[i].x, buttons[i].y - 52);
                        break;
                    case 1:
                        g.drawString("$" + towerPrice[i] + " - Atk. 12", buttons[i].x, buttons[i].y - 52);
                        break;
                    case 2:
                        g.drawString("$" + towerPrice[i] + " - Atk. 25", buttons[i].x, buttons[i].y - 52);
                        break;
                    case 3:
                        g.drawString("$" + towerPrice[i] + " - Atk. 100", buttons[i].x, buttons[i].y - 52);
                        break;
                    case 4:
                        g.drawString("$" + towerPrice[i] + " - Atk. 125", buttons[i].x, buttons[i].y - 52);
                        break;
                    case 5:
                        g.drawString("$" + towerPrice[i] + " - Atk. 150", buttons[i].x, buttons[i].y - 52);
                        break;
                    default:
                        break;
                }
            }
        }

        // Vida
        g.drawImage(Screen.tileset_res[1], health.x, health.y - (storeAlign + 15), health.width, health.height, null);
        // Moedas
        g.drawImage(Screen.tileset_res[2], gold.x, gold.y - (storeAlign + 15), gold.width, gold.height, null);
        // Fonte e cor
        g.setFont(new Font("Times New Roman", Font.BOLD, 14));
        g.setColor(Color.white);

        // Valores
        g.drawString("" + this.healthPoints, health.x + health.width + 10, health.y - storeAlign);
        g.drawString("" + this.goldPoints, gold.x + gold.width + 10, gold.y - storeAlign);

        // Se selecionar uma torre, atualiza a sprite para seguir o mouse
        // ############# GAMBIARRA ###############
        if (itemHold) {
            if(heldId / 3 == 7) {
                g.drawImage(
                    Screen.tileset_ground[heldId], 
                    Screen.mouse.x - ((buttons[0].width / 2) + 4), 
                    Screen.mouse.y - buttons[0].height, 
                    buttons[5].width,
                    buttons[5].height,
                    null
                );
            } else {
                g.drawImage(
                    Screen.tileset_ground[heldId], 
                    Screen.mouse.x - ((buttons[0].width / 2) + 4), 
                    Screen.mouse.y - buttons[0].height, 
                    buttons[heldId / 3].width,
                    buttons[heldId / 3].height,
                    null
                );
            }
        }
    }

    public void click(int mouseEvent) {
        if (mouseEvent == 1) { // Click esquerdo do mouse == 1
            // Verifica em todos os botoes se o mouse esta dentro da area de cada um
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].contains(Screen.mouse)) {
                    
                    // Mulitplicacao por 3 utilizada pois cada torre ocupada um
                    // espaco de tres quadrados na SpriteSheet, ocupando 64x192
                    if (buttonID[i * 3] == SpriteIDs.trash) {
                        itemHold = false;
                    } else {
                        heldId = buttonID[i * 3];
                        auxID = i;
                        itemHold = true;
                    }
                    
                    
                }
            }
            
            
            if (itemHold && goldPoints >= towerPrice[auxID]) {
                for (int y = 0; y < Screen.map.block.length; y++) {
                    for (int x = 0; x < Screen.map.block[0].length; x++) {

                        if (Screen.map.block[y][x].contains(Screen.mouse)) {
                            int id = Screen.map.block[y][x].terrainID;

                            if (id != SpriteIDs.roadID && id == SpriteIDs.buildID) {
                                Screen.map.block[y][x].terrainID = heldId;
                                this.goldPoints -= towerPrice[auxID];
                            }

                        }

                    }
                }
            }
            
        }
    }
}
