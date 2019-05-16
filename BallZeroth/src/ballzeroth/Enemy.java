package ballzeroth;

import java.awt.*;

/**
 *
 * @author martin.akretzschmar
 */
public class Enemy extends Rectangle {
    public int enemySize = 64;
    
    public Enemy() {
        for (int i = 0; i < Screen.map.block.length; i++) {
            if ( Screen.map.block[i][0].terrainID == SpriteIDs.roadID ) {
                
            }
        }
    }
}
