package Arpanoid;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Ing. Simone Giuliani
 */
public class Utili {
    public static BufferedImage scala(BufferedImage img, int w, int h){
        
        BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(img, 0, 0, w, h, null);
        graphics2D.dispose();
        return resizedImage;
    }
    
    public static Font scegliFont(String nome, int size){
       Font f = new Font(nome, Font.BOLD, size);
       return f;
    }
}
