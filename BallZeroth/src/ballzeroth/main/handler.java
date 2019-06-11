package ballzeroth.main;

import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author martin.akretzschmar
 */
public class handler implements MouseMotionListener, MouseListener {

    public void mouseDragged(MouseEvent e) {
        
    }

    public void mouseMoved(MouseEvent e) {
        Screen.mouse = new Point(e.getX(), e.getY());
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
