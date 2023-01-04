
package Arpanoid;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class InfoPowerup extends JPanel {
    int grandezza, altezza;
    int altezzaImg;
    String[] informazioni = {
    "Aggiunge una vita",    
    "Aumenta il danno",  
    "Aggiunge una pallina",    
    "Ingrandisce la pallina",    
    "Dimunuisce una vita",
    "Aumenta velocità pallina",
    "Rimpicciolisce la barra",
    };
    
    InfoPowerup(){
        altezzaImg = ContenitoreGrafica.powerups[0].getHeight();
        altezza = altezzaImg * (ContenitoreGrafica.powerups.length + 2);
        grandezza = altezza+60;
        setSize(grandezza, altezza);
    }
    
    @Override
    public void paintComponent(Graphics g){
        BufferedImage[] gruppoImmagini = new ContenitoreGrafica().powerups;
        g.setFont(Utili.scegliFont("monospaced", 15));
        for(int i = 0; i<informazioni.length; i++){
            g.drawImage(gruppoImmagini[i], 0, i*gruppoImmagini[i].getHeight(), this);
            g.drawString(informazioni[i], gruppoImmagini[i].getWidth(),
                                          i*gruppoImmagini[i].getHeight() + 15 );
        }      
    }
}
