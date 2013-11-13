package jlx.tools.tudou;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jianglx
 */
public class Main {

    public static void main(String[] args) {
        
        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    MainFrame jFrame = new MainFrame();
                    jFrame.setLocationRelativeTo(null);
                    jFrame.setVisible(true);
                }

                
            });
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}




    
