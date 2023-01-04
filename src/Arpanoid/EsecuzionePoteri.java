
package Arpanoid;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EsecuzionePoteri {
    int timer, posizione;
    Lavagna lavagna;   
    //Date tempoIniziale;
    String t;
    long tempoIniziale;
    int time;
    boolean eseguito = false;
    
    public EsecuzionePoteri(int timer, int posizione, Lavagna lavagna){

    tempoIniziale = new Date().getTime();
    
    this.timer = timer;
    this.posizione = posizione;
    this.lavagna = lavagna;
    }
    
    void rimpicciolisciBarra(){
        if (eseguito == false){
            lavagna.wbarra = 50;            
            eseguito = true;
        }
              
        if(timer <= time){
            lavagna.wbarra = lavagna.WBARRABASE;
            lavagna.esecuzioniPoteri[posizione] = null;
        }        
    }
    
    void velocizzaPallina(){
        if (eseguito == false){
            lavagna.moltVelPall = 2;
            eseguito = true;
        }
 
        if(timer <= time){
            lavagna.moltVelPall = 1;
            lavagna.esecuzioniPoteri[posizione] = null;
        }        
    }
    
    void ingrandisciPallina(){
        if (eseguito == false){
            lavagna.grandezzaPallina = 40; 
            eseguito = true;
        }
               
        if(timer <= time){
            lavagna.grandezzaPallina = lavagna.GRANDEZZAPALLINAFISSO;
            lavagna.esecuzioniPoteri[posizione] = null;
        }
    }
    
    void faiDanno(){
        if (eseguito == false){
            lavagna.danno = 7;
            lavagna.colorePallina = Color.red;
            eseguito = true;
        }
                
        if(timer <= time){
            lavagna.danno = 2;
            lavagna.colorePallina = lavagna.COLOREINIZIALE;
            lavagna.esecuzioniPoteri[posizione] = null;
        }
    }
    
    public void agisci(){
        long prov = new Date().getTime();
        time = (int)(prov - tempoIniziale)/1000;
        switch(posizione){
            case 1: faiDanno();break;
            case 3: ingrandisciPallina();break;
            case 5: velocizzaPallina();break;
            case 6: rimpicciolisciBarra();break;       
        }
    }    
}
