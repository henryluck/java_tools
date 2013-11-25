package jlx.tools.refreshetao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import jlx.tools.webfetcher.IUpdateTextFrame;
import jlx.tools.webfetcher.task.BaseConnInfo;
import jlx.tools.webfetcher.task.HttpTask;
import jlx.tools.webfetcher.task.TaskManager;
import jlx.util.string.FormatUtil;

/**
 * {class description} <br>
 * <p>
 * Create on : 2012-6-1<br>
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
public class MainFrame extends JFrame implements IUpdateTextFrame<GoodsVO> {

    private final String url = "http://www.etao.com";
    private final JPanel m_contentPane;
    /**
     * <code>collection</code> - {当前已经显示的公司信息}.
     */
    Collection<GoodsVO> all = null;
    private final Object lock = new Object();
    private final TaskManager<GoodsVO> taskManager = new TaskManager<GoodsVO>(this);
    private final JButton m_button_start;
    private final JButton m_button_stop;
    private final JTextArea m_textArea;
    private final JScrollPane m_scrollPane;

    /**
     * Launch the application.
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
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
        setIconImage(Toolkit.getDefaultToolkit().getImage(
            MainFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(final WindowEvent e) {
                // 做一些初始化动作
                onWindowOpened();
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                onResize();
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 989, 602);
        m_contentPane = new JPanel();
        m_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(m_contentPane);
        m_contentPane.setLayout(null);

        m_button_stop = new JButton("停止");
        m_button_stop.setBounds(785, 10, 80, 23);
        m_contentPane.add(m_button_stop);
        m_button_stop.setEnabled(false);
        m_button_stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                stopFresh();
            }

        });
        m_button_stop.setFont(new Font("宋体", Font.PLAIN, 12));

        m_button_start = new JButton("开始自动刷新");
        m_button_start.setBounds(670, 10, 116, 23);
        m_contentPane.add(m_button_start);
        m_button_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                startFresh();

            }
        });
        m_button_start.setFont(new Font("宋体", Font.PLAIN, 12));

        m_scrollPane = new JScrollPane();
        m_scrollPane.setBounds(10, 67, 953, 492);
        m_contentPane.add(m_scrollPane);

        m_textArea = new JTextArea();
        m_scrollPane.setViewportView(m_textArea);
        //初始化任务
        BaseConnInfo connInfo = new BaseConnInfo();
        connInfo.setUrl(this.url);
        HttpTask<GoodsVO> task = new HttpTask<GoodsVO>(connInfo, new EtaoProcessor());
        taskManager.addTask(task);

    }

    private void stopFresh() {
        taskManager.stop();

        m_button_stop.setEnabled(false);
        m_button_start.setEnabled(true);
    }

    /**
     * {method description}.
     */
    protected void onWindowOpened() {
        // ######################################新加的时候修改这个地方##########################################
        // 设置URL默认值
        /**
         * m_textField_51.setText(ConfigUtil.getDefaultURLByKey("51job"));
         * m_textField_taida.setText(ConfigUtil.getDefaultURLByKey("taida"));
         * m_textField_yicai.setText(ConfigUtil.getDefaultURLByKey("yicai"));
         * m_textField_chinahr.setText(ConfigUtil.getDefaultURLByKey("chinahr"));
         * m_textField_ganji.setText(ConfigUtil.getDefaultURLByKey("ganji"));
         */
        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<新加的时候修改这个地方<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    }

    /**
     * {开始刷新}.
     */
    public void startFresh() {
        m_button_stop.setEnabled(true);
        m_button_start.setEnabled(false);
        this.invalidate();
        
        taskManager.start();
    }

    /**
     * {更新內容 }.
     * 
     * @param collection
     */
    @Override
    public void updateAreaTxt(final List<GoodsVO> collection) {
        synchronized (lock) {
            List<GoodsVO> needAdd = new ArrayList<GoodsVO>();

            all2List(collection, needAdd);
            // 没有归属的公司pop提示框
            popNoneOwner(needAdd);
            // 写数据
            StringBuffer buf = new StringBuffer();
            for (Iterator iterator = needAdd.iterator(); iterator.hasNext();) {
                GoodsVO gs = (GoodsVO) iterator.next();
                String name = FormatUtil.addBlank(gs.title);
                buf.append("\n").append(name).append("\t").append(gs.content).append("\t").append(gs.url).append("\t")
                    .append(gs.imageUrl);
            }
            m_textArea.append(buf.toString());
            // m_textArea.invalidate();
        }
    }

    // 过滤没有归属的新公司提示出来
    public void popNoneOwner(final List<GoodsVO> list) {
        
        for (int i = 0; i < list.size(); i++) {
            StringBuffer buffer = new StringBuffer();
            GoodsVO gs = list.get(i);
            buffer.append(gs.title);
            buffer.append("\n").append(gs.content);
            
            GoodsAlertMgr.pop(buffer.toString(),gs.image,gs.url);

        }
    }

    /**
     * 排除重复
     * 
     * @param collection
     * @param needAdd
     */
    private void all2List(final Collection<GoodsVO> collection, final List<GoodsVO> needAdd) {
        if (all == null) {
            all = new ArrayList<GoodsVO>();
        }
        for (Iterator<GoodsVO> iterator = collection.iterator(); iterator.hasNext();) {
            GoodsVO gs = iterator.next();
            if (!all.contains(gs)) {
                needAdd.add(gs);
                all.add(gs);
            }
        }
    }

    private void onResize() {
        m_textArea.setSize(this.getWidth() - 35, this.getHeight() - 113);
        m_scrollPane.setSize(this.getWidth() - 35, this.getHeight() - 113);
    }
}
