/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmp3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oneleaf
 */
public class Main {

    public static void main(String[] args) {
        try {
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




    
