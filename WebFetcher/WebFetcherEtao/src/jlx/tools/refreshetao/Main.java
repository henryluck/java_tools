package jlx.tools.refreshetao;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jlx.util.hotkey.SystemHotKeyManager;
import jlx.util.log.SystemOutSetter;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author jianglx
 */
public class Main {

    public static void main(final String[] args) {
        
        try {
            
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             UIManager.setLookAndFeel(new NimbusLookAndFeel());
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                  //重定向System.out
                    SystemOutSetter.setSystOut();
                    MainFrame jFrame = new MainFrame();
                    jFrame.setLocationRelativeTo(null);
                    jFrame.setVisible(true);
                    //初始化系统快捷键
                    SystemHotKeyManager key = new SystemHotKeyManager();
                    key.addHotkey(new HotKeyHandler(jFrame));
                }

                
            });
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 切换窗口样式
     * 
     * @param args
     */
    public static void main1(final String[] args) {
        final JFrame frame = new JFrame();
        UIManager.LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();
        JComboBox combo = new JComboBox(lafs);
        combo.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(final ItemEvent e) {
                if (ItemEvent.SELECTED == e.getStateChange()) {
                    UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo) e.getItem();
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                        SwingUtilities.updateComponentTreeUI(frame);
                    } catch (Exception e2) {
                        // TODO: handle exception
                    }
                }

            }
        });
        frame.add(combo, BorderLayout.NORTH);
        frame.add(new JButton("按钮"), BorderLayout.SOUTH);
        frame.setSize(400, 300);
        frame.setVisible(true);

    }

}




    
