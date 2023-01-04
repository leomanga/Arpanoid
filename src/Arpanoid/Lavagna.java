package Arpanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*

-
-!FINE PARTITA
    °NOMI
    °ARKANOID
    °IMMAGINE
    °CLASSE

*CAMBIO MUSICA PER POWERUP
*/

public class Lavagna extends JPanel implements Runnable,MouseMotionListener {
   
    public Thread t;
    
    
    //OGGETTI
    public Pallina[] p;
    public Barra barra = null;
    public Mattone[] map;
    public Rectangle r;
    public Rectangle rTot;
    public SimulatoreFisica simulatore;
    public EsecuzionePoteri[] esecuzioniPoteri;
    public Livello livello;
    public Livello[] livelli;
    public MusicStuff controlloreMusica;
    public BufferedImage[] sfondi;
    //GRANDEZZE
    final int WBARRABASE;
    public int wbarra;
    public int hbarra;
    final int GRANDEZZAPALLINAFISSO;
    public int grandezzaPallina;
    public int moltVelPall;
    public int altezzaBlocchi;
    public int grandezzaTabellone;
    public int punteggio;
    //PARAMETRI
    final public int WHAIT;
    public int nPalline;
    public int vite;
    int contLivello;
    final public int DANNOFISSO;
    public int danno;
    final public int TIMERPOTERI;
    public int MAXPOTERI;
    final public Color COLOREINIZIALE;
    public Color colorePallina;
    //FLAG 
    int scelta;
    int vecchioLivello = -1;
    boolean stampaLivello = false;
    boolean theEnd; 
    boolean vittoria;
    
    public Lavagna(){  
        super();    
         esecuzioniPoteri = new EsecuzionePoteri[7]; 
        //GRANDEZZE
        WBARRABASE = 70;
        wbarra = WBARRABASE;
        hbarra = 15;
        GRANDEZZAPALLINAFISSO = 10;
        grandezzaPallina = GRANDEZZAPALLINAFISSO;        
        moltVelPall = -1;       
        grandezzaTabellone = 40;
        punteggio = 0;
        //PARAMETRI
        WHAIT = 5;
        nPalline = 5;
        vite = 5;
        DANNOFISSO = 2;
        danno = DANNOFISSO;
        TIMERPOTERI = 10;
        MAXPOTERI = 20;
        COLOREINIZIALE = Color.orange;
        colorePallina = COLOREINIZIALE;
        //FLAG
        vittoria = false;
        theEnd = false;
        
        addMouseMotionListener(this);
        t = new Thread(this);     
    }
      
    public void aggiungiPotere(int scelta){
        esecuzioniPoteri[scelta] = new EsecuzionePoteri(TIMERPOTERI, scelta, this);              
    }
    
    public void aggiungiPallina(){
        for(int i = 0; i<p.length;i++){
            if(p[i] == null){
                int xi = (int)(Math.random()*r.width);
                p[i] = new Pallina(xi,barra.barray-10,  -1 , -2, grandezzaPallina, grandezzaPallina, colorePallina);
                break;
            }
        }
    }
    
    public void leggiPotere(int scelta){
        /*SCELTE:
        0 = cuori(+vita)
        1 = pallone(+Danno)
        2 = sfera verde(pallina in più)
        3 = basket(+grande)
        4 = X(- vita)
        5 = fulmine(+ veloce)
        6 = radiazione(barra piccola)*/  
        switch(scelta){
            case 0: simulatore.vite+=1 ; MusicStuff.playSound("getVita", -5);       break;
            case 1: aggiungiPotere(scelta); MusicStuff.playSound("getPotere1", -5); break;
            case 2: aggiungiPallina(); MusicStuff.playSound("getPotere1", -5);      break;
            case 3: aggiungiPotere(scelta); MusicStuff.playSound("getPotere1", -5); break;
            case 4: simulatore.vite-=1; punteggio -= 50; MusicStuff.playSound("perditaVita", -5);   break;
            case 5: aggiungiPotere(scelta); MusicStuff.playSound("getPotere1", -5);break;
            case 6: aggiungiPotere(scelta);punteggio -= 50; MusicStuff.playSound("perditaVita", -5); break;
                
        }
    }
    
    public void startThread(){
        t.start();
    }
    
    public void caricaSfondo(){
        try {
            if(scelta == 0){
                sfondi = new BufferedImage[3];
                for(int i = 1; i<=3; i++)
                    sfondi[i-1] = Utili.scala(ImageIO.read(new File("background"+i+".jpg")),r.width, r.height);
            }
            else{
                sfondi = new BufferedImage[1];
                sfondi[0] = Utili.scala(ImageIO.read(new File("background"+scelta+".jpg")),r.width, r.height);
            }
        } catch (IOException ex) {
            System.out.println("Errore nel caricamento degli sfondi");
        }

    }
    
    public void initLoop(){
        //LOOP INIZIALE PER CARICARE GLI OGGETTI
        r = getBounds();
        do{
            try{
                r.setBounds(r.x, r.y + grandezzaTabellone, 832, 617);
                Thread.sleep(30);
            } catch (InterruptedException ex){}
        } while (r.width==0);
         caricaSfondo();
        //IMPOSTA LA QUANTITA' DI LIVELLI IN BASE ALLA SCELTA
        int quanti = 1;
        if(scelta == 0)
            quanti = 3;
        livelli = new Livello[quanti];
        //PRENDO I LIVELLI DAL CONTENITORE GRAFICA
        if(scelta == 0)
            for(int i=0; i<livelli.length; i++){
                livelli[i] = new Livello();
                livelli[i].parseLevelFromString(ContenitoreGrafica.livelli[i],
                                                r, this);  
            }
        else{
            livelli[0] = new Livello();
            livelli[0].parseLevelFromString(ContenitoreGrafica.livelli[scelta-1], 
                                            r, this);
        }
    }
    
    public void creaElementiDiGioco(int numeroPalline){
        //SE CI SONO POTERI IN CORSO LI INTERROMPO
        if(esecuzioniPoteri != null) 
            for(int i = 0; i<esecuzioniPoteri.length; i++)
                if(esecuzioniPoteri[i] != null)
                    esecuzioniPoteri[i].eseguito = true; 
          
        Poteri.inizializza(MAXPOTERI);
        rTot = getBounds();
        //SISTEMA L'ALTEZZA DELLA BARRA
        int yb = r.height - hbarra - (int)(r.height*0.10);
        map = livello.getMap();
        p = new Pallina[numeroPalline];
        
        altezzaBlocchi = map[map.length-1].y + map[map.length-1].h;
        //CREO LA BARRA E LA PALLINA
        barra = new Barra(100,yb,Color.BLACK,r,p);
        barra.ridimensiona(wbarra, hbarra);
        aggiungiPallina();
        
    }

    public void mainLoop(){
        simulatore = new SimulatoreFisica(vite);     
        while (theEnd == false){
            //SE NON C'E' UN NUOVO LIVELLO:s
            if(stampaLivello == false){
                //EVENTUALMENTE AGGIORNO LE PALLINE CON LE NUOVE GRANDEZZE
                for(int i=0;p[i]!= null && i<p.length; i++){
                    p[i].aggiorna(grandezzaPallina, moltVelPall, colorePallina);
                }
                //RIDIMENSIONO LA BARRA COSI' DA POTERLA MODIFICARE ALL'OCCORRENZA
                barra.ridimensiona(wbarra, hbarra);              
                //SIMULO I RIMBALZI
                simulatore.simula(r, livello, p, barra, map, this);
                //ESEGUO I POTERI
                for(int i=0; i<esecuzioniPoteri.length; i++)
                    if(esecuzioniPoteri[i] != null){
                        esecuzioniPoteri[i].agisci();
                    }  
                //LEGGO LE VITE
                vite = simulatore.getVite();
                //PROSSIMO LIVELLO O VITTORIA
                if (simulatore.blocchiRimanenti() == 0){
                    if(contLivello<livelli.length)
                        vittoria = true;
                    
                    break;
                }
                //PERSO
                if (vite == 0){
                    vittoria = false;
                    theEnd = true;
                    break;
                }
            }
            repaint();
            //ASPETTO IL TEMPO DECISO NEL COSTRUTTORE
            try{
                Thread.sleep(WHAIT);
            } catch (InterruptedException ex){}
        }
        repaint();       
    }
   
    @Override
    public void run() {

        initLoop();                      // Dopo che la finestra esiste 
        creaElementiDiGioco(nPalline);   // creo le palline e la barra
        mainLoop();                      // inizio il loop del gioco

    }
   
    @Override
    public void paintComponent(Graphics g){
        //CANCELLO TUTTO
        if (r != null && sfondi != null){
            
            //g.setColor(Color.WHITE);
            //g.fillRect(0,0,r.width,r.height);
            g.drawImage(sfondi[contLivello], 0,0, null);
        }
        //DISEGNO GLI OGGETTI
        if (p!=null){
            for(int i=0;i<p.length;i++)
                if (p[i]!=null)
                    p[i].disegnati(g);
        }        
        if (barra!=null)
            barra.disegnati(g);
        if (livello != null)
            livello.disegnati(g);
        if(Poteri.poteri != null)
            for(int i=0;i<Poteri.poteri.length; i++)               
                if(Poteri.poteri[i] != null)
                    Poteri.poteri[i].disegna(g);
                   
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (barra!=null)
            barra.setX(e.getX());
    }
}
