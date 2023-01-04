/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arpanoid;
   
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javafx.scene.layout.Border;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;



public class Start extends javax.swing.JFrame {
    public JFrame frame; 
    JButton[] listaBottoni;
    public InfoPowerup infoPowerup;
    Finestra finestra;
    /*0 = Arcade
    .... Livelli*/
    int scelta = 0;
    public Start() {
        setBounds(0,0,838,646);
        //BARRA AL CENTRO DELLO SCHERMO
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                         dim.height/2-this.getSize().height/2);
        initComponents();
        
        infoPowerup = new InfoPowerup();
        JButton[] listaButton = {bArcade, bLivello1, bLivello2, bLivello3};
        listaBottoni = listaButton;
        
    }
    
    private void coloraBottone(JButton daColorare){
        daColorare.setBorder(new LineBorder(Color.red));
        for(int i=0; i<listaBottoni.length; i++)
            if(daColorare != listaBottoni[i])
                listaBottoni[i].setBorder(new LineBorder(Color.gray));
                
    }
    //@Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        bInfoPowerup = new javax.swing.JButton();
        bLivello1 = new javax.swing.JButton();
        bLivello2 = new javax.swing.JButton();
        bArcade = new javax.swing.JButton();
        bLivello3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jPanel1.setLayout(null);

        bInfoPowerup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arpanoid/infoPoteri.jpg"))); // NOI18N
        bInfoPowerup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bInfoPowerupMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bInfoPowerupMouseReleased(evt);
            }
        });
        bInfoPowerup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInfoPowerupActionPerformed(evt);
            }
        });
        bInfoPowerup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bInfoPowerupKeyReleased(evt);
            }
        });
        jPanel1.add(bInfoPowerup);
        bInfoPowerup.setBounds(640, 490, 140, 60);

        bLivello1.setBackground(java.awt.Color.gray);
        bLivello1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arpanoid/level1.jpg"))); // NOI18N
        bLivello1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonGroup1.add(bLivello1);
        bLivello1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLivello1ActionPerformed(evt);
            }
        });
        jPanel1.add(bLivello1);
        bLivello1.setBounds(250, 230, 160, 80);

        bLivello2.setBackground(java.awt.Color.gray);
        bLivello2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arpanoid/level2.jpg"))); // NOI18N
        bLivello2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonGroup1.add(bLivello2);
        bLivello2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLivello2ActionPerformed(evt);
            }
        });
        jPanel1.add(bLivello2);
        bLivello2.setBounds(410, 230, 160, 80);

        bArcade.setBackground(java.awt.Color.red);
        bArcade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arpanoid/arcade.jpg"))); // NOI18N
        bArcade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        buttonGroup1.add(bArcade);
        bArcade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bArcadeActionPerformed(evt);
            }
        });
        jPanel1.add(bArcade);
        bArcade.setBounds(90, 230, 160, 80);

        bLivello3.setBackground(java.awt.Color.gray);
        bLivello3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arpanoid/level3.jpg"))); // NOI18N
        bLivello3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonGroup1.add(bLivello3);
        bLivello3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLivello3ActionPerformed(evt);
            }
        });
        jPanel1.add(bLivello3);
        bLivello3.setBounds(570, 230, 160, 80);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arpanoid/start.jpg"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, -20, 832, 590);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bLivello1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLivello1ActionPerformed
        scelta = 1;
        coloraBottone(bLivello1);
    }//GEN-LAST:event_bLivello1ActionPerformed

    private void bLivello2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLivello2ActionPerformed
        scelta = 2;
        coloraBottone(bLivello2);
    }//GEN-LAST:event_bLivello2ActionPerformed

    private void bLivello3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLivello3ActionPerformed
        scelta = 3;
        coloraBottone(bLivello3);
    }//GEN-LAST:event_bLivello3ActionPerformed

    private void bInfoPowerupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInfoPowerupActionPerformed
      
    }//GEN-LAST:event_bInfoPowerupActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped

    }//GEN-LAST:event_formKeyTyped

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        setVisible(false);
        //if(frame != null)
        //frame.setVisible(false);
        finestra = new Finestra(scelta);
        finestra.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void bArcadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bArcadeActionPerformed
        scelta = 0;
        bArcade.setBackground(Color.red);
        coloraBottone(bArcade);
    }//GEN-LAST:event_bArcadeActionPerformed

    private void bInfoPowerupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bInfoPowerupMousePressed
        
        frame = new JFrame("Powerups"); 
        frame.setBounds(0,0,260,200);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-this.getSize().width/2,
                         dim.height/2-this.getSize().height/2);
        frame.setSize(infoPowerup.grandezza+70, infoPowerup.altezza);
        frame.add(infoPowerup);
        frame.setVisible(true);
    }//GEN-LAST:event_bInfoPowerupMousePressed

    private void bInfoPowerupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bInfoPowerupKeyReleased
        
    }//GEN-LAST:event_bInfoPowerupKeyReleased

    private void bInfoPowerupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bInfoPowerupMouseReleased
        frame.setVisible(false); 
    }//GEN-LAST:event_bInfoPowerupMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bArcade;
    private javax.swing.JButton bInfoPowerup;
    private javax.swing.JButton bLivello1;
    private javax.swing.JButton bLivello2;
    private javax.swing.JButton bLivello3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
