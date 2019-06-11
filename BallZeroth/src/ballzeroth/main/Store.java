package ballzeroth.main;

import java.awt.*;

/**
 *
 * @author martin.akretzschmar
 */
public class Store {

    public static int shopItens = 8;
    public static int buttonSize = 52;
    private int storePush = 70;
    private int HUDIconSize = 20;
    private int rectWidth;
    private int rectHeight;
    private boolean itemHold = false;
    private int heldId = -1;
    public static int[] buttonId = {
        SpriteIDs.humanToweOneID,
        SpriteIDs.humanToweTwoID,
        SpriteIDs.humanToweThreeID,
        SpriteIDs.orcToweOneID,
        SpriteIDs.orcToweTwoID, 
        SpriteIDs.orcToweThreeID
    };

    public Rectangle[] buttons = new Rectangle[shopItens];
    public Rectangle health;
    public Rectangle gold;

    public Store() {
        for (int i = 0; i < buttons.length; i++) {
            rectWidth = (Screen.screenWidth / 2) - (shopItens * (buttonSize + 3) / 2);
            rectHeight = Screen.map.block[Screen.map.worldHeight - 1][0].y;

            buttons[i] = new Rectangle(rectWidth + ((buttonSize + 3) * i), rectHeight + storePush, buttonSize, buttonSize);
        }

        health = new Rectangle(Screen.map.block[0][0].x, buttons[0].y, HUDIconSize, HUDIconSize);
        gold = new Rectangle(Screen.map.block[0][0].x, buttons[0].y + buttons[0].height - HUDIconSize, HUDIconSize, HUDIconSize);
    }

    public void draw(Graphics g) {
        for (int i = 0; i < buttons.length; i++) {
            g.drawImage(Screen.tileset_ground[buttonId[i]], buttons[i].x, buttons[i].y, buttons[i].width, buttons[i].height, null);
        }

        g.drawImage(Screen.tileset_res[1], health.x, health.y, health.width, health.height, null);
        g.drawImage(Screen.tileset_res[2], gold.x, gold.y, gold.width, gold.height, null);
        g.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        if(itemHold){
            g.drawImage(Screen.tileset_ground[heldId], Screen.mse.x - (buttons[0].width / 2), Screen.mse.y - (buttons[0].width / 2), buttons[heldId].width, buttons[heldId].height, null);
        }
    }
    
    public void click (int mouseEvent) {
        if (mouseEvent == 1) {
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].contains(Screen.mse)){
                    if (heldId == SpriteIDs.trash){
                        itemHold = false;
                    } else {
                        heldId = buttonId[i];
                        itemHold = true;
                    }
                }
            }
        }
    }
}
