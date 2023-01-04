
package Arpanoid;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Powerup{
    /*
    SCELTE:
    0 = cuori(+vita)
    1 = pallone(+Danno)
    2 = sfera verde(pallina in più)
    3 = basket(+grande)
    4 = X(- vita)
    5 = fulmine(+ veloce)
    6 = radiazione(barra piccola)
    7 = chiave(prossimo livello)
    */
    BufferedImage powerup;
    int scelta;
    int x, y;
    int grandezza;
    int posizione;
    BufferedImage immagine;
    Rectangle hitbox;
    Lavagna lavagna;
    public Powerup(int x, int y, int grandezza, int posizione, 
                   int lMattone, int hMattone, Lavagna lavagna){
        
        this.lavagna = lavagna;
        this.x = x+(lMattone/2)-(grandezza/2);
        this.y = y+(hMattone/2)-(grandezza/2);
        this.grandezza = grandezza;
        this.posizione = posizione;  
    }
    
    public void generaPowerup(){
        // NON GENERA MAI LA CHIAVE
        scelta = (int)(Math.random()*7);  
    }
    
    public int muovi(Rectangle o){        
        y+=1;
        //SE IL POTERE ARRIVA IN FONDO
        if(y+grandezza > lavagna.r.height){
            Poteri.poteri[posizione] = null;            
        }
        //CONTROLLO SE IL POTERE TOCCA LA BARRA 
        int chose = -1;
        hitbox = new Rectangle(x, y , grandezza, grandezza);
        if(o.intersects(hitbox)){  
            chose = scelta;
            Poteri.poteri[posizione] = null; 
        }    
        return chose; 
    }
    
    public void disegna(Graphics g){
        if(scelta != -1){
            powerup = ContenitoreGrafica.powerups[scelta];
            g.drawImage(powerup, x, y, null);
        }
    }
}
