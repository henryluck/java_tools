import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


/**
 * {class description}
 * <br>
 *  
 * <p>
 * Create on : 2012-9-5<br>
 * <p>
 * </p>
 * <br>
 * @author henry.luck@gmail.com<br>
 * @version 正则表达式测试工具 v1.0
 * <p>
 *<br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public class RegexFrame extends JFrame {

    private JPanel m_contentPane;
    private JTextField jTextField1;
    private JTextArea txtAreaSrc;
    private JTextArea txtAreaResult;
    private JCheckBox jCheckBoxCaseIn;
    private JCheckBox jCheckBoxDotAll;
    private JScrollPane m_scrollPaneSrc;
    private JScrollPane m_scrollPaneResult;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegexFrame frame = new RegexFrame();
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
    public RegexFrame() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                onResize();
            }
        });
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 744, 627);
        m_contentPane = new JPanel();
        m_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(m_contentPane);
        m_contentPane.setLayout(null);
        
        jTextField1 = new JTextField();
        jTextField1.setText("正则表达式");
        jTextField1.setBounds(10, 10, 516, 21);
        m_contentPane.add(jTextField1);
        jTextField1.setColumns(10);
        
        JButton btnResult = new JButton("匹配结果");
        btnResult.setFont(new Font("宋体", Font.PLAIN, 12));
        btnResult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnResultMouseClicked(e);
            }
        });
        btnResult.setBounds(536, 9, 86, 23);
        m_contentPane.add(btnResult);
        
        JButton btnList = new JButton("匹配列表");
        btnList.setFont(new Font("宋体", Font.PLAIN, 12));
        btnList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnListMouseClicked(e);
            }
        });
        btnList.setBounds(628, 9, 92, 23);
        m_contentPane.add(btnList);
        
        m_scrollPaneSrc = new JScrollPane();
        m_scrollPaneSrc.setBounds(10, 70, 708, 215);
        m_contentPane.add(m_scrollPaneSrc);
        
        txtAreaSrc = new JTextArea();
        m_scrollPaneSrc.setViewportView(txtAreaSrc);
        txtAreaSrc.setFont(new Font("宋体", Font.PLAIN, 13));
        txtAreaSrc.setText("要做匹配处理的文字");
        
        m_scrollPaneResult = new JScrollPane();
        m_scrollPaneResult.setBounds(9, 294, 710, 286);
        m_contentPane.add(m_scrollPaneResult);
        
        txtAreaResult = new JTextArea();
        m_scrollPaneResult.setViewportView(txtAreaResult);
        txtAreaResult.setFont(new Font("宋体", Font.PLAIN, 13));
        txtAreaResult.setText("匹配结果显示");
        
        jCheckBoxCaseIn = new JCheckBox("大小写不敏感");
        jCheckBoxCaseIn.setFont(new Font("宋体", Font.PLAIN, 12));
        jCheckBoxCaseIn.setBounds(10, 41, 103, 23);
        m_contentPane.add(jCheckBoxCaseIn);
        
        jCheckBoxDotAll = new JCheckBox("Dot Match All");
        jCheckBoxDotAll.setBounds(115, 41, 103, 23);
        m_contentPane.add(jCheckBoxDotAll);
    }

    /**
     * {method description}.
     * @param e
     */
    protected void btnResultMouseClicked(ActionEvent e) {
        this.txtAreaResult.setText("");
        int mode = (jCheckBoxDotAll.isSelected() ? Pattern.DOTALL : 0)
                | (jCheckBoxCaseIn.isSelected() ? Pattern.CASE_INSENSITIVE : 0);
        Pattern purl = Pattern.compile(this.jTextField1.getText(), mode);
        Matcher murl = purl.matcher(this.txtAreaSrc.getText());

        int i = 0;
        StringBuffer buf = new StringBuffer();
        while (murl.find()) {
            i++;
            buf.append("\n匹配项值").append(i).append("：").append(murl.group());
            for (int j = 1; j <= murl.groupCount(); j++) {
                buf.append("\n组").append(j).append(":").append(murl.group(j));

            }
            buf.append("\n-------------------------------华丽分割线---------------------------");
        }

        if (i > 0) {
            buf.insert(0, "匹配次数:"+i+"\n-------------------------------华丽分割线---------------------------");
            this.txtAreaResult.setText(buf.toString());
        } else {
            this.txtAreaResult.setText("没有匹配项");
        }
                
    }
    /**
     * {method description}.
     * @param e
     */
    protected void btnListMouseClicked(ActionEvent e) {
        this.txtAreaResult.setText("");
        int mode = (jCheckBoxDotAll.isSelected() ? Pattern.DOTALL : 0)
                | (jCheckBoxCaseIn.isSelected() ? Pattern.CASE_INSENSITIVE : 0);
        Pattern purl = Pattern.compile(this.jTextField1.getText(), mode);
        Matcher murl = purl.matcher(this.txtAreaSrc.getText());

        int i = 0;
        StringBuffer buf = new StringBuffer();
        List<String> list = new ArrayList<String>();
        while (murl.find()) {
            i++;
//            buf.append("\n匹配项值").append(i).append("：").append(murl.group());
            for (int j = 1; j <= murl.groupCount(); j++) {
                String string = murl.group(j);
                if(!list.contains(string)){
                    list.add(string);
                    buf.append(murl.group(j)).append("\n");
                }
                
                

            }
//            buf.append("\n-------------------------------华丽分割线---------------------------");
        }

        if (i > 0) {
//            buf.insert(0, "匹配次数:"+i+"\n-------------------------------华丽分割线---------------------------");
            this.txtAreaResult.setText(buf.toString());
        } else {
            this.txtAreaResult.setText("没有匹配项");
        }        
    }
    
    private void onResize() {
        // 输入输出框
        int space = 130;
        int height =  (this.getHeight() - space)/2;
        
        txtAreaSrc.setSize(this.getWidth() - 35,height);
        m_scrollPaneSrc.setSize(this.getWidth() - 35,height);
        
        txtAreaResult.setBounds(txtAreaResult.getX(), (this.getHeight() -space)/2+80, this.getWidth() - 35,  height);
        m_scrollPaneResult.setBounds(m_scrollPaneResult.getX(), (this.getHeight() -space)/2+80, this.getWidth() - 35,  height);
        
        //textbox
        
    }
}
