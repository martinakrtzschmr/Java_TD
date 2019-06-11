package ballzeroth.main;

import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author martin.akretzschmar
 */
public class handler implements MouseMotionListener, MouseListener {

    public void mouseDragged(MouseEvent e) {
        Screen.mouse = new Point(((e.getX()) + ((BallZeroth.size.width) - Screen.screenWidth) / 2), ((e.getY()) + ((BallZeroth.size.height - (Screen.screenHeight)) - (BallZeroth.size.width - Screen.screenWidth) / 2)));
    }

    public void mouseMoved(MouseEvent e) {
        Screen.mouse = new Point(((e.getX()) + ((BallZeroth.size.width) - Screen.screenWidth) / 2), ((e.getY()) + ((BallZeroth.size.height - (Screen.screenHeight)) - (BallZeroth.size.width - Screen.screenWidth) / 2)));
    }

    public void mouseClicked(MouseEvent e) {
        
    }

    public void mousePressed(MouseEvent e) {
        Screen.store.click(e.getButton());
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }
}
