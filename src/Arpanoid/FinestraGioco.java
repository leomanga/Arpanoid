
package Arpanoid;

import javax.swing.JFrame;


public class FinestraGioco {

    FinestraGioco(int scelta){
        JFrame finestra = new JFrame("Gioco");
        finestra.setBounds(0,0,775,656);
        finestra.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        finestra.setResizable(false);
        TabelloneELavagna tabellone = new TabelloneELavagna();
        tabellone.setBounds(0,0,775,626);
        finestra.add(tabellone);
        tabellone.avviaThread(scelta);
        finestra.setVisible(true);
    }
}
