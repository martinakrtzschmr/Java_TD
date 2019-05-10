package ballzeroth;

import java.io.*;
import java.util.*;

public class MapConstruct {
    public void loadMap (File loadPath) throws FileNotFoundException {
        Scanner scan = new Scanner(loadPath);
        
        while(scan.hasNext()){
            for (int y = 0; y < Screen.map.block.length; y++) {
                for (int x = 0; x < Screen.map.block.length; x++) {
                    Screen.map.block[y][x].terrainID = scan.nextInt();
                }
            }
        }
        
        scan.close();
    }
}
