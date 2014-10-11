package jlx.tools.research;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import jlx.tools.research.vo.CompanyInfo;
import jlx.tools.research.zhaopin.CorSearcher;
import jlx.util.log.SystemOutSetter;

/**
 * {class description} <br>
 * <p>
 * Create on : 2012-7-7<br>
 * <p>
 * </p>
 * <br>
 * 
 * @author henry.luck@gmail.com<br>
 * @version CompanyResearchTool v1.0
 *          <p>
 *          <br>
 *          <strong>Modify History:</strong><br>
 *          user modify_date modify_content<br>
 *          -------------------------------------------<br>
 *          <br>
 */
public class ComNameListFrame extends JFrame {

    private final JPanel m_contentPane;
    private final JTextArea m_textArea_comList;
    private final JTextArea m_textArea_result;
    private final JScrollPane m_scrollPane_comList;
    private final JScrollPane m_scrollPane_result;
    private final JButton m_button;

    /**
     * Launch the application.
     */
    public static void main(final String[] args) {
        SystemOutSetter.setSystOut("namelist.log");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ComNameListFrame frame = new ComNameListFrame();
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
    public ComNameListFrame() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                onResize();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 764, 465);
        m_contentPane = new JPanel();
        m_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(m_contentPane);
        m_contentPane.setLayout(null);

        m_scrollPane_comList = new JScrollPane();
        m_scrollPane_comList.setBounds(10, 41, 350, 376);
        m_contentPane.add(m_scrollPane_comList);

        m_textArea_comList = new JTextArea();
        m_scrollPane_comList.setViewportView(m_textArea_comList);

        JLabel label = new JLabel("公司名称列表：");
        label.setFont(new Font("宋体", Font.PLAIN, 12));
        label.setBounds(10, 16, 105, 15);
        m_contentPane.add(label);

        m_scrollPane_result = new JScrollPane();
        m_scrollPane_result.setBounds(370, 41, 421, 376);
        m_contentPane.add(m_scrollPane_result);

        m_textArea_result = new JTextArea();
        m_scrollPane_result.setViewportView(m_textArea_result);

        m_button = new JButton("开始批量查询");
        m_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                m_button.setEnabled(false);
                searchComList();
            }

        });
        m_button.setBounds(179, 12, 128, 23);
        m_contentPane.add(m_button);
    }

    private void onResize() {
        m_scrollPane_comList.setSize(350, this.getHeight() - 90);
        m_textArea_comList.setSize(350, this.getHeight() - 90);

        m_scrollPane_result.setSize(this.getWidth() - m_textArea_comList.getWidth() - 40,
                this.getHeight() - 90);
        m_textArea_result
                .setSize(this.getWidth() - m_textArea_comList.getWidth() - 40, this.getHeight() - 90);
    }

    private void searchComList() {
        m_textArea_result.setText("");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] coms = m_textArea_comList.getText().split("\n");
                for (int i = 0; i < coms.length; i++) {
                    String name = coms[i];
                    if(name != null && !name.equals("")){
                        CompanyInfo com = new CompanyInfo(coms[i]);
                        CorSearcher.search(com);
                        m_textArea_result.setText(m_textArea_result.getText() + com.getName() + "\t" + com.getOwnerDep()
                                + "\t" + com.getOwnerUser() + "\n");
                    }
                    
                }
                JOptionPane.showMessageDialog(null, "搜索公司列表完成！");
                m_button.setEnabled(true);
            }
        }).start();
        

    }
}
