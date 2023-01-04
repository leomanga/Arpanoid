package Arpanoid;

import java.awt.Graphics;
import java.awt.Rectangle;


public class Livello {
    Mattone[] map;
    Lavagna lavagna;
    
    public void parseLevelFromString(String s, Rectangle r, Lavagna lavagna){
        this.lavagna = lavagna;
        int w = ContenitoreGrafica.mattoni.tipiMattoni[0].immagini[0].getWidth()+1;
        int h = ContenitoreGrafica.mattoni.tipiMattoni[0].immagini[0].getHeight()+1;
        String[] righe = s.split("\n");
        int i,j,tot=0;
        String[] buffRighe = s.replace(" ", "").split("\n");

        for(i=0;i<buffRighe.length;i++)
            tot += buffRighe[i].length();
        map = new Mattone[tot];
        int quale = 0;

        for(i=0;i<righe.length;i++)
            for(j=0;j<righe[i].length();j++){
                TipoMattone tipo;
                int stato = 1;
                switch (righe[i].charAt(j)){
                    case ' ': tipo = null; break;
                    case '0': tipo = ContenitoreGrafica.mattoni.tipiMattoni[0]; break;
                    case '1': tipo = ContenitoreGrafica.mattoni.tipiMattoni[1]; break;
                    case '2': tipo = ContenitoreGrafica.mattoni.tipiMattoni[2]; break;
                    case '3': tipo = ContenitoreGrafica.mattoni.tipiMattoni[3]; break;
                    case '4': tipo = ContenitoreGrafica.mattoni.tipiMattoni[4]; break;
                    case '5': tipo = ContenitoreGrafica.mattoni.tipiMattoni[5]; break;
                    case '6': tipo = ContenitoreGrafica.mattoni.tipiMattoni[6]; break;

                    case 'a': tipo = ContenitoreGrafica.mattoni.tipiMattoni[0];stato = 0;break;
                    case 'b': tipo = ContenitoreGrafica.mattoni.tipiMattoni[1];stato = 0;break;
                    case 'c': tipo = ContenitoreGrafica.mattoni.tipiMattoni[2];stato = 0;break;
                    case 'd': tipo = ContenitoreGrafica.mattoni.tipiMattoni[3];stato = 0;break;
                    case 'e': tipo = ContenitoreGrafica.mattoni.tipiMattoni[4];stato = 0;break;
                    case 'f': tipo = ContenitoreGrafica.mattoni.tipiMattoni[5];stato = 0;break;
                    case 'g': tipo = ContenitoreGrafica.mattoni.tipiMattoni[6];stato = 0;break;

                    default:  tipo = ContenitoreGrafica.mattoni.tipiMattoni[0]; break;   
                }
                if(tipo != null){
                    map[quale] = new Mattone(j*w, i*h+r.y,tipo, stato, lavagna);
                    quale = quale + 1;
                }
            }
    }
   
    public Mattone[] getMap(){
        return map;
    }
   
    public void disegnati(Graphics g){
        int i;
        if (map == null)
            return;
        for(i=0;i<map.length && map != null;i++)      
            map[i].disegna(g);
    }
}
