package jlx.tools.research.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import jlx.tools.research.vo.CompanyInfo;
import jlx.tools.research.zhaopin.WebRoboter;
import jlx.util.string.FormatUtil;

/**
 * {class description}
 * <br>
 *  
 * <p>
 * Create on : 2012-9-6<br>
 * <p>
 * </p>
 * <br>
 * @version CompanyResearchTool v1.0
 * <p>
 *<br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public class OwnFrame extends JFrame {

    private final JPanel m_contentPane;
    private final Object lock = new Object();
    private final JTextArea m_textArea;

    /**
     * Launch the application.
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    OwnFrame frame = new OwnFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public OwnFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(OwnFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
        setTitle("我的列表");
        setBounds(100, 100, 751, 496);
        m_contentPane = new JPanel();
        m_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        m_contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(m_contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        m_contentPane.add(scrollPane, BorderLayout.CENTER);
        
        m_textArea = new JTextArea();
        scrollPane.setViewportView(m_textArea);
    }
    
    
    /**
     * {更新內容 }.
     * 
     * @param collection
     */
    public void updateAreaTxt(final List<CompanyInfo> collection) {
        synchronized (lock) {
            // 写数据
            StringBuffer buf = new StringBuffer();
            for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
                CompanyInfo companyInfo = (CompanyInfo) iterator.next();
                String name = FormatUtil.addBlank(companyInfo.getName());
                if(WebRoboter.USER.equals(companyInfo.getOwnerUser())){
                    buf.append("\n").append(name).append("\t").append(companyInfo.getOwnerDep())
                    .append("\t").append(companyInfo.getOwnerUser()).append("\t")
                    .append(companyInfo.getDetailURL());
                }
            }
            m_textArea.append(buf.toString());
            // m_textArea.invalidate();
        }
    }

}
