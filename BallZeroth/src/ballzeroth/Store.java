package ballzeroth;

import java.awt.*;

/**
 *
 * @author martin.akretzschmar
 */
public class Store {
    public static int shopItens = 8;
    public static int buttonSize = 52;
    
    public Rectangle[] buttons = new Rectangle[shopItens];
    
    public Store() {
        int rectWidth;
        for (int i = 0; i < buttons.length; i++) {
            rectWidth = (Screen.screenWidth / 2) - (shopItens * (buttonSize + 3) /2);
            
            buttons[i] = new Rectangle(rectWidth + ((buttonSize + 3) * i), 10, buttonSize, buttonSize);
        }
    }
    
    public void draw (Graphics g) {
        for (int i = 0; i < buttons.length; i++) {
            g.fillRect(buttons[i].x, buttons[i].y, buttons[i].width, buttons[i].height);
        }
    }
}
