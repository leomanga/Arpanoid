package Arpanoid;

import java.awt.image.BufferedImage;

public class ContenitoreGrafica {

   static String livello1 = 
                           "  1         1  \n"+
                           "   1   a   1   \n"+
                           "      1 1      \n"+
                           "     1 1 1     \n"+
                           "    111a111    \n"+
                           "    1     1    \n"+
                           "    1 1a1 1    \n"+
                           " 1  11   11  1 \n"+           
                           "       1       \n";

   
   
   
   
    static String livello2 = 
                           "  3  3 3 3  3  \n"+
                           "   3   c   3   \n"+
                           "   c c3c3c c   \n"+
                           "  33333333333  \n"+
                           " 4  3  4  3  4 \n"+
                           "  4 3     3 4  \n"+
                           "     c343c     \n"+
                           " 3  44   44  3 \n"+           
                           "   3   3   3   \n";
    
    static String livello3 = 
                           "  ee5662665ee  \n"+
                           "   3   5   3   \n"+
                           "  66625552666  \n"+
                           "  6    6    6  \n"+
                           " f  6     6  f \n"+
                           "  2566 6 6625  \n"+
                           "   f 26 62 f   \n"+
                           " f    f f    f \n"+           
                           " 5566222226655 \n";



    static String livelloProva = "a";
   
   static String[] livelli = {livello1,livello2,livello3};
   static TipiMattoni mattoni;
   static BufferedImage barra;
   static BufferedImage[] powerups;
   
   public void resizeBarra(int w, int h){
        barra = Utili.scala(barra, w, h);
   }
   
}
