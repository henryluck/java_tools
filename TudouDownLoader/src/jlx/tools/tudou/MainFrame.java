package jlx.tools.tudou;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import jlx.tools.tudou.vo.Definition;
import jlx.tools.utils.DebugLogger;

/**
 * {class description} <br>
 * <p>
 * Create on : 2012-11-7<br>
 * <p>
 * </p>
 * <br>
 * 
 * @author jianglinxue<br>
 * @version TudouDownLoader v1.0
 *          <p>
 *          <br>
 *          <strong>Modify History:</strong><br>
 *          user modify_date modify_content<br>
 *          -------------------------------------------<br>
 *          <br>
 */
public class MainFrame extends JFrame {

    private JPanel m_contentPane;
    private JTextField m_txtURL;
    private JComboBox m_comboDeep;
    private JComboBox m_comboDefinition;
    private TudouDownloader downloader;
    public static JButton m_btnGo;
    private JScrollPane m_scrollPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
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
    public MainFrame() {
        setResizable(false);
        setTitle("土豆视频下载");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 577, 372);
        m_contentPane = new JPanel();
        m_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(m_contentPane);
        m_contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("起始URL:");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 12));
        lblNewLabel.setBounds(10, 10, 66, 15);
        m_contentPane.add(lblNewLabel);

        m_txtURL = new JTextField();
        m_txtURL.setText("http://music.tudou.com/");
        m_txtURL.setBounds(81, 7, 470, 21);
        m_contentPane.add(m_txtURL);
        m_txtURL.setColumns(10);

        JLabel label = new JLabel("搜索深度：");
        label.setFont(new Font("宋体", Font.PLAIN, 12));
        label.setBounds(10, 41, 66, 15);
        m_contentPane.add(label);

        m_comboDeep = new JComboBox();
        m_comboDeep.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        m_comboDeep.setBounds(81, 38, 32, 21);
        m_contentPane.add(m_comboDeep);

        JLabel label_1 = new JLabel("画质：");
        label_1.setFont(new Font("宋体", Font.PLAIN, 12));
        label_1.setBounds(150, 41, 43, 15);
        m_contentPane.add(label_1);

        m_comboDefinition = new JComboBox();
        m_comboDefinition.setFont(new Font("宋体", Font.PLAIN, 12));
        m_comboDefinition.setModel(new DefaultComboBoxModel(Definition.values()));
        m_comboDefinition.setBounds(189, 38, 56, 21);
        m_contentPane.add(m_comboDefinition);

        m_btnGo = new JButton("go");
        m_btnGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                go(e);
            }
        });
        m_btnGo.setBounds(458, 37, 93, 23);
        m_contentPane.add(m_btnGo);

        m_scrollPane = new JScrollPane();
        m_scrollPane.setBounds(10, 66, 541, 258);
        m_contentPane.add(m_scrollPane);

        JTextArea textArea = new JTextArea();
        m_scrollPane.setViewportView(textArea);

        DebugLogger.setTXT_AREA(textArea);
    }

    public void go(ActionEvent e) {
        try {
            m_btnGo.setEnabled(false);
            m_btnGo.validate();
            downloader = new TudouDownloader(m_txtURL.getText(), Integer.parseInt((String) m_comboDeep
                    .getSelectedItem()), ((Definition) m_comboDefinition.getSelectedItem()).getCode(), "f4v");
            downloader.startDownload();
            // m_btnGo.setEnabled(true);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
