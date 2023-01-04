package Arpanoid;

import java.awt.Graphics;
import java.awt.Rectangle;


public class Mattone {
   
    int x,y,w,h;
    TipoMattone tipo;
    int stato;
    Powerup powerup;
    Lavagna lavagna;
    
    public Mattone(int x, int y, TipoMattone tipo, int stato, Lavagna lavagna){
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        this.lavagna = lavagna;
        if (tipo!=null){
            this.w = tipo.immagini[0].getWidth();
            this.h = tipo.immagini[0].getHeight();
        }
        this.stato = stato;
     }
   
    public Rectangle getSize(){
        Rectangle r = new Rectangle(x, y, w, h);
        return r;
    }
    
    public void disegna(Graphics g){
        if (tipo != null)  
            if (stato < tipo.immagini.length)
                g.drawImage(tipo.immagini[stato], x, y, null);          
        else{
            g.drawRect(x, y, w, h);
        }    
    }     
    
    public boolean rompi(int danno){ 
        boolean rotto = false;
        // Se il mattone è speciale:
        if(stato == 0){
            stato = 7;
            //SE ROMPO IL MATTONE SPECIALE GENERO IL POWERUP
            for(int i=0; i<Poteri.poteri.length; i++)
                if(Poteri.poteri[i] == null){
                    Poteri.poteri[i] = new Powerup(x, y, Poteri.grandezza, i, w, h, lavagna);
                    Poteri.poteri[i].generaPowerup();
                    break;
                }
        }
        
        // Sennò:
        stato += danno;
        
        // Quando lo stato è al massimo "elimino" il blocco
        if (tipo != null && stato >= tipo.immagini.length){   
            tipo = null;
            rotto = true;
        }
    return rotto;    
    }
}
