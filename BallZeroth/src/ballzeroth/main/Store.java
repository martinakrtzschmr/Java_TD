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
    private int buttonSpace = 10;

    private boolean itemHold = false;
    private int heldId = -1;
    public static int[] buttonID = {
        SpriteIDs.humanToweOneID,
        SpriteIDs.humanToweTwoID,
        SpriteIDs.humanToweThreeID,
        SpriteIDs.orcToweOneID,
        SpriteIDs.orcToweTwoID, 
        SpriteIDs.orcToweThreeID,
        SpriteIDs.trash
    };
    public static int[] towerPrice = {
        10, 20, 30, 40, 50, 60, 0
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

            buttons[i] = new Rectangle(storeWidth + ((buttonSize + buttonSpace) * i), storeHeight, buttonSize, buttonSize);
        }

        // Sprites
        this.health = new Rectangle(Screen.map.block[0][0].x, buttons[0].y, HUDIconSize, HUDIconSize);
        this.gold = new Rectangle(Screen.map.block[0][0].x, buttons[0].y + buttons[0].height - HUDIconSize, HUDIconSize, HUDIconSize);
        
        // Values
        this.healthPoints = health;
        this.goldPoints = coins;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < buttons.length; i++) {
            if(buttons[i].contains(Screen.mouse)){
                g.drawImage(Screen.tileset_ground[buttonID[i + 1]], buttons[i].x, buttons[i].y - 52, buttons[i].width, buttons[i].height, null);
            } else {
                g.drawImage(Screen.tileset_ground[buttonID[i]], buttons[i].x, buttons[i].y - 52, buttons[i].width, buttons[i].height, null);
            }
            
            if (towerPrice[i] > 0){
                g.setColor(Color.white);
                g.drawString("$" + towerPrice[i], buttons[i].x, buttons[i].y - 52);
            }
        }

        // Vida
        g.drawImage(Screen.tileset_res[1], health.x, health.y - (storeAlign + 15), health.width, health.height, null);
        // Moedas
        g.drawImage(Screen.tileset_res[2], gold.x,   gold.y - (storeAlign + 15),   gold.width,   gold.height,   null);
        // Fonte e cor
        g.setFont(new Font("Times New Roman", Font.BOLD, 14));
        g.setColor(Color.white);
        
        // Valores
        g.drawString("" + this.healthPoints, health.x + health.width + 10, health.y - storeAlign);
        g.drawString("" + this.goldPoints,   gold.x + gold.width + 10,     gold.y - storeAlign);
        
        // Se selecionar uma torre, atualiza a sprite para seguir o mouse
        if(itemHold){
            g.drawImage(Screen.tileset_ground[heldId], Screen.mouse.x - ((buttons[0].width / 2) + 4), Screen.mouse.y - buttons[0].height, buttons[heldId].width, buttons[heldId].height, null);
        }
    }
    
    public void click (int mouseEvent) {
        if (mouseEvent == 1) {
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].contains(Screen.mouse)){ // PRECISA TESTAR
                    if (buttonID[i] == SpriteIDs.trash){
                        itemHold = false;
                    } else {
                        heldId = buttonID[i];
                        itemHold = true;
                    }
                }
            }
        }
    }
}
