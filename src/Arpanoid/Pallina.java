package Arpanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Pallina {
    //COORDINATE E DIREZIONI
    private int x,y;
    final int vx, vy;
    public int dx,dy;
    public int vecchioMolt;
    public Rectangle doveEro;
    public Rectangle doveSono;
    public Rectangle doveEroX;
    public Rectangle doveEroY;
    //GRANDEZZE 
    private int larghezzaPallina,altezzaPallina;
    //COLORE
    public Color colore; 
    //FLAG
    public boolean fatto;
    public  boolean dCambiata;
    
    public Pallina(int initX, int initY,
                   int initDX,int initDY,
                   int initW,int initH, Color initColore){
        x = initX;
        y = initY;
        vx = initDX;
        vy = initDY;
        dx = vx;
        dy = vy;
        vecchioMolt = vx;
        doveSono = new Rectangle(x,y,larghezzaPallina,altezzaPallina);
        larghezzaPallina=initW;
        altezzaPallina=initH;    
        colore = initColore;
    }
   
    public Rectangle getMyRect(){
        return doveSono;
    }
   
    public void aggiorna(int nuovaGrandezza, int molt, Color colore){
        //IMPOSTO LE NUOVE GRANDEZZE
        if(nuovaGrandezza != larghezzaPallina){
            larghezzaPallina = nuovaGrandezza;
            altezzaPallina = nuovaGrandezza;
        }
        //AL CAMBIO DELLA VELOCITA' RIMANGO CON LE STESSE DIREZIONI
        if (molt != vecchioMolt){
            dx = dx/vecchioMolt;
            dy = dy/ vecchioMolt;
            
            dx = dx*molt;
            dy = dy*molt;
            vecchioMolt = molt; 
        }   
        this.colore = colore;
    }

    public void spostati(Rectangle b){
        if (doveSono.intersects(b)==false)
            return;
        doveEroY = (Rectangle)doveSono.clone();
        doveEroY.x = doveEroY.x -dx;

        boolean d = (y+altezzaPallina/2)<(b.y+b.height/2);
        
        if (doveEroY.intersects(b)){
            if ( d) y = b.y - altezzaPallina - 2;
            if (!d) y = b.y + b.height + 2;         
             doveSono = new Rectangle(x,y,larghezzaPallina,altezzaPallina);
            return;
        }
    }
   
    public void controllaCollisione(Rectangle o){
        if (doveSono.intersects(o)==false)
            return;
        MusicStuff.playSound("toccoBarra1", -2);
        // ANNULLO IL MOVIMENTO SU Y QUINDI MI SONO MOSSO SOLO SU X
        doveEroX = (Rectangle)doveSono.clone();
        doveEroX.y = doveEroX.y - dy;
        if (doveEroX.intersects(o)){
            if (dx>0) x = o.x - larghezzaPallina - 1;
            if (dx<0) x = o.x + o.width + 1;
            dx = -dx;
            return;
        }
        // ANNULLO IL MOVIMENTO SU X QUINDI MI SONO MOSSO SOLO SU Y
        doveEroY = (Rectangle)doveSono.clone();
        doveEroY.x = doveEroY.x -dx;  
        if (doveEroY.intersects(o)){
            if (dy>0) y = o.y - altezzaPallina - 1;
            if (dy<0) y = o.y + o.height + 1;         
            dy = -dy;
            return;
        }
    }
   
    public void controllaCollisioneConPallina(Pallina p){
        Rectangle o = p.getMyRect();
        if (doveSono.intersects(o)==false)
            return;
        MusicStuff.playSound("boing", -20);
        p.fatto = true;
        // ANNULLO IL MOVIMENTO SU Y QUINDI MI SONO MOSSO SOLO SU X
        doveEroX = (Rectangle)doveEro.clone();
        doveEroX.x += dx;  
        if (doveEroX.intersects(o)){ 
            if (dx*p.dx<0){
                dx = -dx;
                p.dx = -p.dx;
            }
            else{
                if (Math.abs(dx)>Math.abs(p.dx))
                    dx = -dx;
                else
                    p.dx = -p.dx;
            }

            doveSono.x = doveEro.x;
            x = doveEro.x;
            p.doveSono.x = p.doveEro.x;
            p.x = p.doveEro.x;
        }

        // ANNULLO IL MOVIMENTO SU X QUINDI MI SONO MOSSO SOLO SU Y
        doveEroY = (Rectangle)doveEro.clone();
        doveEroY.y += dy;  
        if (doveEroY.intersects(o)){
            if (dy*p.dy<0) {
                dy = -dy;
                p.dy = -p.dy;
            }
            else{
                if (Math.abs(dy)>Math.abs(p.dy))
                    dy = -dy;
                else
                    p.dy = -p.dy;
            }

           doveSono.y = doveEro.y;
           y = doveEro.y;
           p.doveSono.y = p.doveEro.y;
           p.y = p.doveEro.y;
        }
    }
   
    public boolean collisioneConMattone(Rectangle o){
        boolean collisione = false;

        if (doveSono.intersects(o)==false)
           return collisione;
        MusicStuff.playSound("rotturaBlocco", 2);
        // ANNULLO IL MOVIMENTO SU Y QUINDI MI SONO MOSSO SOLO SU X
        doveEroX = (Rectangle)doveSono.clone();
        doveEroX.y = doveEroX.y - dy;
        if (doveEroX.intersects(o)){
            //SE NON HA GIA' RIMBALZATO NELL'ASSE X:
            if(!dCambiata){               
                if (dx>0) x = o.x - larghezzaPallina - 1;
                if (dx<0) x = o.x + o.width + 1;
                dx = -dx;
                dCambiata = true;
            }
           
            collisione = true;
            return collisione;
        }
        // ANNULLO IL MOVIMENTO SU X QUINDI MI SONO MOSSO SOLO SU Y
        doveEroY = (Rectangle)doveSono.clone();
        doveEroY.x = doveEroY.x -dx;  
        if (doveEroY.intersects(o)){
            //SE NON HA GIA' RIMBALZATO NELL'ASSE Y:
            if(!dCambiata){          
                if (dy>0) y = o.y - altezzaPallina - 1;
                if (dy<0) y = o.y + o.height + 1;
                dy = -dy;
                dCambiata = true;
            } 
            
            collisione = true;
        }
        return collisione;
    }
     
    public int controllaCollisioneConMattone(Mattone[] map, int danno){
        int rotti = 0;
        
        dCambiata = false;
        //PER TUTTI I MATTONI CONTROLLO LA COLLISIONE E LI ROMPO
        for(int i=0; i<map.length; i++)
            if(map[i].tipo != null && collisioneConMattone(map[i].getSize()))
                if(map[i].rompi(danno))
                    rotti += 1;
        return rotti;
    }

    public int muovi(Rectangle r){
        int danni = 0;
        
        if (r.width!=0){
      
            x = x + dx;
            y = y + dy;

            doveEro = doveSono;
            doveSono = new Rectangle(x,y,larghezzaPallina,altezzaPallina);

            if (x<r.x){
                MusicStuff.playSound("bordi", -5);
                x = r.x;
                dx = -dx;
            }

            if (y<r.y){
                MusicStuff.playSound("bordi", -5);
                y = r.y;
                dy = -dy;
            }

            if (x+larghezzaPallina>r.width-1){
                MusicStuff.playSound("bordi", -5);
                x = r.width - larghezzaPallina - 1;
                dx = -dx;
            }

            if (y+altezzaPallina>r.height-1){
                MusicStuff.playSound("perditaVita", -5);
                y = r.height - altezzaPallina - 2;
                dy = -dy;
                danni = 1;
            }
        }
        return danni;
   }
   
    public void disegnati(Graphics g){
        g.setColor(colore);
        g.fillOval(x, y, larghezzaPallina, altezzaPallina);
    }
}
