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
    private int itemIn = 4;
    private int heldID = -1;

    public static int buttonID[] = {0, 0, 0, 0, 0, 0, 0, 0, 1};

    public Rectangle[] buttons = new Rectangle[shopItens];
    public Rectangle health;
    public Rectangle gold;

    public boolean holdsItem = false;

    public Store() {
        for (int i = 0; i < buttons.length; i++) {
            rectWidth = (Screen.screenWidth / 2) - (shopItens * (buttonSize + 3) / 2);
            rectHeight = Screen.map.block[Screen.map.worldHeight - 1][0].y;

            buttons[i] = new Rectangle(rectWidth + ((buttonSize + 3) * i), rectHeight + storePush, buttonSize, buttonSize);
        }

        health = new Rectangle(Screen.map.block[0][0].x, buttons[0].y, HUDIconSize, HUDIconSize);
        gold = new Rectangle(Screen.map.block[0][0].x, buttons[0].y + buttons[0].height - HUDIconSize, HUDIconSize, HUDIconSize);
    }

    public void click(int mouseButton) {
        if (mouseButton == 1) {
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].contains(Screen.mouse)) {
                    if (heldID == Value.airTrashCan) {
                        holdsItem = false;
                    } else {

                        heldID = buttonID[i];
                        holdsItem = true;

                    }
                }
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < buttons.length; i++) {
            g.drawImage(Screen.tileset_res[0], buttons[i].x, buttons[i].y, buttons[i].width, buttons[i].height, null);
            g.drawImage(Screen.tileset_air[buttonID[i]], buttons[i].x + itemIn, buttons[i].y + itemIn, buttons[i].width - (itemIn * 2), buttons[i].height - (itemIn * 2), null);
        }

        g.drawImage(Screen.tileset_res[1], health.x, health.y, health.width, health.height, null);
        g.drawImage(Screen.tileset_res[2], gold.x, gold.y, gold.width, gold.height, null);
    }
}
