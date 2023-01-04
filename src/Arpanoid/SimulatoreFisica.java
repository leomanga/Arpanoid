
package Arpanoid;

import java.awt.Rectangle;


public class SimulatoreFisica {
    //Pallina p[];
    public int rotti = 0;
    Mattone[] map;
    int danni = 0;
    int vite;
    
    public SimulatoreFisica(int vite){
    this.vite = vite;    
    }
    
    public void simula( //-------------------------
                        Rectangle r, Livello livello,
                        Pallina p[], Barra barra, 
                        Mattone[] map, Lavagna lavagna
                        //-------------------------
                   ){
        
        this.map = map;
        //MUOVO LE PALLINE E CONTROLLO SE TOCCANO LA BARRA
        for(int i=0;i<p.length && p[i] != null;i++){
            int vecchiDanni = danni;
            p[i].fatto = false;
            danni += p[i].muovi(r);
            
            lavagna.punteggio -= (danni-vecchiDanni) * 100;
            p[i].controllaCollisione(barra.getRettangolo());
            //SE FINISCONO LE VITE:
            if (danni > vite)
                break;    
        }
        //QUANDO FINISCONO LE VITE ESCO
        if (danni > vite)
            return;
        //CONTROLLO SE LE PALLINE TOCCANO LE ALTRE
        for(int i=0; i<p.length && p[i] != null; i++){
            if(p[i].fatto == true)
                break;
            for(int j=0;j<i;j++)
                if(p[j] != null)
                    p[i].controllaCollisioneConPallina(p[j]);            
        }
        //CONTROLLO LE COLLISIONI DELLE PALLINE CON I MATTONI
        for(int i=0; i<p.length && p[i] != null; i++){
            int rottiPrima = rotti;
            rotti += p[i].controllaCollisioneConMattone(livello.getMap(), lavagna.danno);
            lavagna.punteggio += (rotti-rottiPrima)*100;
        }
        //MUOVO I POTERI E CONTROLLO SE LI PRENDO O SCOMPAIONO
        int scelta = -1;
        if(Poteri.poteri != null)          
            for(int i=0; i<Poteri.poteri.length; i++)  
                if(Poteri.poteri[i] != null){
                    scelta = Poteri.poteri[i].muovi(barra.getRettangolo());
                    if (scelta != -1)
                        lavagna.leggiPotere(scelta);
                }
    }
        
    
    public void aggiornaVite(int n){
        vite += n;   
    }
    
    public int getVite(){
        return vite-danni;
    }
     
    public int blocchiRotti(){
        return rotti;
    }
    
    public int blocchiRimanenti(){
        return map.length - rotti;
    }
}
