package Arpanoid;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImporterGrafica {
   
    public static void caricaGrafica(String fileName){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println("Non riesco a caricare l'immagine:"+fileName);
            System.out.println(e);
            System.exit(1);
        }
        TipiMattoni mattoni = new TipiMattoni(7);
        for(int j=0;j<7;j++){
            TipoMattone m = new TipoMattone(6);
            for(int i=0;i<6;i++)
                m.immagini[i] = img.getSubimage(54*j+324,i*22,54,22);
            mattoni.tipiMattoni[j] = m;
        }

        BufferedImage[] powerup = new BufferedImage[8];
        int posizione = 0;
        for(int i = 0; i<2; i++){
            for(int j=0; j<4;j++)
                if(i != 1 || j>1){
                    int whd = 32*i+59;
                    powerup[posizione] = Utili.scala(img.getSubimage(32*j+86,32*i+59,32,32),
                                                     Poteri.grandezza,Poteri.grandezza);
                    posizione += 1;
                }   
            }
        
        for(int i = 0; i<2; i++){
            powerup[posizione] = Utili.scala(img.getSubimage(32*i+246,59,32,32),
                                             Poteri.grandezza,Poteri.grandezza);           
            posizione+=1;
        }

        ContenitoreGrafica.powerups = powerup;
        ContenitoreGrafica.mattoni = mattoni;
        BufferedImage imgIniziale = img.getSubimage(148,332,128,30);
        ContenitoreGrafica.barra = imgIniziale;
    }
}
