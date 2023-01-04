
package Arpanoid;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class TabelloneELavagna extends Lavagna{
    public int grandezzaTabellone;
    boolean fineStampata = false;
    Rectangle r;
    long tInizioStampa;
    public TabelloneELavagna(){
        grandezzaTabellone = super.grandezzaTabellone; 
    }
    
    public String stringaPunteggio(int punteggio){
        String sPunteggio = ""+ Math.abs(punteggio);
        String finale = new String(new char[10-sPunteggio.length()]).replace("\0", "0"); 
        if(punteggio < 0)
            finale = "-"+finale;
        return finale+sPunteggio;
    }
    
    public String stringaLivello(){   
        String s = "LIVELLO "+(contLivello+1);
        if(scelta != 0)
            s = "LIVELLO "+ scelta;    
        return s;
    }
    
    public void stampaFine(Graphics g){
        controlloreMusica.stoppaMusica();
        if(vittoria == true){
            try {
                if(fineStampata==false)
                    MusicStuff.playSound("vittoria", -10);
                g.drawImage(Utili.scala(ImageIO.read(
                            new File("imgVinto.jpg")),r.width, rTot.height),
                            0,0, this);
            } catch (IOException ex) {}
        }
        else{ 
            try {
                if(fineStampata==false)
                    MusicStuff.playSound("morte", -10);
                g.drawImage(Utili.scala(ImageIO.read(
                            new File("imgPerso.jpg")),r.width, rTot.height),
                            0,0, this);
            } catch (IOException ex) {}
        }
        //STAMPA PUNTEGGIO
        g.setColor(Color.WHITE);    
        g.setFont(Utili.scegliFont("monospaced", 40));
        String s = "PUNTEGGIO : "+punteggio;
        g.drawString(s, 238, 350);
        fineStampata = true;
    }
    
    public void avviaThread(int scelta){
        super.scelta = scelta;
        startThread();
  
    }
     
    @Override
    public void run() {
        initLoop(); 
        //VISTO CHE A VOLTE NON SONO UGUALI LI RENDO UGUALI
        r = getBounds();
        r.height = grandezzaTabellone;    
        r.width = super.r.width;
        controlloreMusica = new MusicStuff();
        //OGNI VOLTA CHE FINISCE IL LIVELLO RICOMINCIO
        for(contLivello = 0; contLivello<livelli.length && !theEnd; contLivello++){
            //IMPOSTO LE MUSICHE IN BASE ALLA SCELTA
            if (scelta == 0)
                controlloreMusica.playMusic("livello"+(contLivello+1)+".wav");
            else
                controlloreMusica.playMusic("livello"+scelta+".wav"); 
            //IMPOSTO IL NUOVO LIVELLO
            livello = livelli[contLivello];
            //PROCEDO
            creaElementiDiGioco(nPalline);
            mainLoop(); 
            //QUANDO FINISCO AGGIORNO IL FLAG
            if(scelta == 0 && livelli[2]!=livello){
                if(theEnd == false){
                    stampaLivello = true;
                    controlloreMusica.stoppaMusica();
                    MusicStuff.playSound("nextLevel", -10);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                }
            }    
            //AGGIORNO IL TEMPO
            tInizioStampa = new Date().getTime();
            System.out.println("OHHHH"+tInizioStampa);
            
        }
        
        //AGGIORNO I FLAG PER FINIRE IL GIOCO
        theEnd = true;
        stampaLivello = false; 
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        if(theEnd == true){
            stampaFine(g);
            return;
        }
        
        super.paintComponent(g);
        
        if (r == null || super.r == null)
            return;
        
        if(stampaLivello){
            long prov = new Date().getTime();
            System.out.println(prov + " "+ tInizioStampa);
            int time = (int)(prov - tInizioStampa)/1000;
            //SE PASSANO DUE SECONDI TORNA A FALSE
            System.out.println(time);
            if(time > 2)
                stampaLivello = false;
            
            if(rTot != null ){
                if(scelta==0)
                    if(livelli[2]==livello)
                        return;
                g.setColor(Color.black);
                g.fillRect(0, 0, rTot.width, rTot.height);
                g.setColor(Color.BLUE);    
                g.setFont(Utili.scegliFont("monospaced", 40));
                String s = "LIVELLO: "+(contLivello+2) ;
                g.drawString(s, (rTot.width/2)-120, rTot.height/2);     
            }
            return;   
        }
        //COLORO LO SFONDO E DISEGNO LA LINEA DI SEPARAZIONE
        g.setColor(Color.CYAN);
        g.fillRect(r.x,r.y,r.width,r.height);
        g.setColor(Color.BLACK);
        g.drawLine(r.x,r.height, r.width,r.height);
        //STAMPO CUORI
        g.setColor(Color.RED);
        String cuori = new String(new char[vite]).replace("\0", "❤");    
        g.drawString(cuori, r.x+20, super.r.height-20);
        //STAMPO BLOCCHI ROTTI E RIMANENTI
        g.setColor(Color.DARK_GRAY);
        g.setFont(Utili.scegliFont("monospaced",12));
        if(simulatore == null)
            return;
        g.drawString("BLOCCHI RIMANENTI "+ simulatore.blocchiRimanenti(), r.x+20, r.y+20);
        g.drawString("BLOCCHI ROTTI "+ simulatore.blocchiRotti(), r.x+20, r.y+30);
        //STAMPO PUNTEGGIO
        g.drawString("SCORE", r.width-85, r.height/2);
        if(punteggio < 0)
            g.setColor(Color.red);
        //g.setFont();
        g.drawString(stringaPunteggio(punteggio), r.width-100, r.height/2+10);
        //STAMPO POWERUP
        int pos;
        int cont = 0;
        BufferedImage img;
        for(int i = 0; i<esecuzioniPoteri.length; i++){
            if(esecuzioniPoteri[i] != null){
                cont += 1;
                pos = esecuzioniPoteri[i].posizione;
                img = ContenitoreGrafica.powerups[pos];
                g.drawImage(img, r.width - (cont * img.getWidth() + 2) , super.r.height-20, this);
            } 
        }
        //STAMPO LIVELLO
        g.setColor(Color.red);
        g.setFont(Utili.scegliFont("monospaced",16));
        g.drawString(stringaLivello(), r.width/2-30, r.height/2);
        
    }
}

