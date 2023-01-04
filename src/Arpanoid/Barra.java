package Arpanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Barra {
    public int barrax,barray;
    private int larghezzaBarra,altezzaBarra;
    private Color colore;
    private Rectangle areaDiGioco;
    private Pallina[] p;
    Rectangle myRect;
    BufferedImage img;
   
    public Barra(int initX, int initY,
                 Color color, Rectangle r, Pallina[] palline){
        
        barrax = initX;
        barray = initY;
        img = ContenitoreGrafica.barra;
        larghezzaBarra = img.getWidth();
        altezzaBarra = img.getHeight();
        colore = color;
        areaDiGioco = r;
        p = palline;
        myRect = new Rectangle(barrax,barray,larghezzaBarra,altezzaBarra);
    }
   
    public void ridimensiona(int w, int h){
       
        larghezzaBarra = w;
        altezzaBarra = h;
        img = Utili.scala(img, larghezzaBarra, altezzaBarra);
    }
   
    private void sistemaPalline(Rectangle myNewRect){
        int i;
        for(i=0;i<p.length && p[i] != null;i++){
            p[i].spostati(myNewRect);
        }
    }
   
    public void setX(int nuovaX){
      
        if (nuovaX+larghezzaBarra>areaDiGioco.width)
            nuovaX = areaDiGioco.width - larghezzaBarra;
        barrax = nuovaX;
        Rectangle myNewRect = new Rectangle(barrax,barray,larghezzaBarra,altezzaBarra);
        sistemaPalline( myNewRect);
    }
   
    public void disegnati(Graphics g){
        g.drawImage(img, barrax, barray, null);
    }
   
    public Rectangle getRettangolo(){
        Rectangle r = new Rectangle(barrax,barray,larghezzaBarra,altezzaBarra);
        return r;
    }
}
