
package Arpanoid;

import java.awt.Dimension;
import java.awt.Toolkit;


public class Finestra extends javax.swing.JFrame {

    public Finestra(int scelta) {
        initComponents();
        //BARRA AL CENTRO DELLO SCHERMO
        setBounds(0,0,838,646);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                         dim.height/2-this.getSize().height/2);
        tabelloneELavagna1.avviaThread(scelta);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabelloneELavagna1 = new Arpanoid.TabelloneELavagna();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout tabelloneELavagna1Layout = new javax.swing.GroupLayout(tabelloneELavagna1);
        tabelloneELavagna1.setLayout(tabelloneELavagna1Layout);
        tabelloneELavagna1Layout.setHorizontalGroup(
            tabelloneELavagna1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
        );
        tabelloneELavagna1Layout.setVerticalGroup(
            tabelloneELavagna1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelloneELavagna1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelloneELavagna1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Arpanoid.TabelloneELavagna tabelloneELavagna1;
    // End of variables declaration//GEN-END:variables
}
