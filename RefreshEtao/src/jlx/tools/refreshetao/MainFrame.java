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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import jlx.tools.refreshetao.EtaoProcessor.Goods;
import jlx.tools.research.frame.IHotKeyFrame;
import jlx.tools.research.frame.IMainFrame;
import jlx.tools.research.frame.NullFrame;
import jlx.tools.research.frame.OwnFrame;
import jlx.tools.research.pop.AlertMgr;
import jlx.tools.research.task.TaskManager;
import jlx.tools.research.utils.DebugLogger;
import jlx.tools.research.utils.FormatUtil;

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
public class MainFrame extends JFrame implements IHotKeyFrame<Goods> {

    private final JPanel m_contentPane;
    /**
     * <code>collection</code> - {当前已经显示的公司信息}.
     */
    Collection<Goods> all = null;
    private final Object lock = new Object();
    private final TaskManager taskManager = new TaskManager(this);
    private final JButton m_button_start;
    private final JButton m_button_stop;
    private final JTextArea m_textArea;
    private final JScrollPane m_scrollPane;
    private final OwnFrame ownFrame = new OwnFrame();
    private final NullFrame nullFrame = new NullFrame();
    private final BossKeyHandler bossKeyHandler = new BossKeyHandler();
    private Runner runner;

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

    }

    private void stopFresh() {
        runner.stop();

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
        // ###########################################新加的时候修改这个地方##########################################
        // 加入任务列表
        /*
         * taskManager.addTask(new TaskInfo("51job",m_textField_51.getText())); taskManager.addTask(new
         * TaskInfo("taida",m_textField_taida.getText())); taskManager.addTask(new
         * TaskInfo("yicai",m_textField_yicai.getText())); taskManager.addTask(new
         * TaskInfo("chinahr",m_textField_chinahr.getText())); taskManager.addTask(new
         * TaskInfo("ganji",m_textField_ganji.getText()));
         */
        // taskManager.addTask(new TaskInfo("51job",ConfigUtil.getDefaultURLByKey("51job")));
        // taskManager.addTask(new TaskInfo("taida",ConfigUtil.getDefaultURLByKey("taida")));
        // taskManager.addTask(new TaskInfo("yicai",ConfigUtil.getDefaultURLByKey("yicai")));
        // taskManager.addTask(new TaskInfo("chinahr",ConfigUtil.getDefaultURLByKey("chinahr")));
        // //taskManager.addTask(new TaskInfo("58tc",ConfigUtil.getDefaultURLByKey("58tc")));
        // taskManager.addTask(new TaskInfo("01hr",ConfigUtil.getDefaultURLByKey("01hr")));

        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<新加的时候修改这个地方<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        m_button_stop.setEnabled(true);
        m_button_start.setEnabled(false);
        this.invalidate();

        this.runner = new Runner(this);
        new Thread(runner).start();

    }

    /**
     * {更新內容 }.
     * 
     * @param collection
     */
    @Override
    public void updateAreaTxt(final List<Goods> collection) {
        synchronized (lock) {
            List<Goods> needAdd = new ArrayList<Goods>();

            all2List(collection, needAdd);
            // 没有归属的公司pop提示框
            popNoneOwner(needAdd);
            // 写数据
            StringBuffer buf = new StringBuffer();
            for (Iterator iterator = needAdd.iterator(); iterator.hasNext();) {
                Goods gs = (Goods) iterator.next();
                String name = FormatUtil.addBlank(gs.title);
                buf.append("\n").append(name).append("\t").append(gs.content).append("\t").append(gs.url).append("\t")
                    .append(gs.imageUrl);
            }
            m_textArea.append(buf.toString());
            // m_textArea.invalidate();
        }
    }

    // 过滤没有归属的新公司提示出来
    public void popNoneOwner(final List<Goods> list) {
        
        for (int i = 0; i < list.size(); i++) {
            StringBuffer buffer = new StringBuffer();
            Goods gs = list.get(i);
            buffer.append(gs.title);
            buffer.append("\n").append(gs.content);
            AlertMgr.pop(buffer.toString(),new ImageIcon(gs.image));

        }
    }

    /**
     * 排除重复
     * 
     * @param collection
     * @param needAdd
     */
    private void all2List(final Collection<Goods> collection, final List<Goods> needAdd) {
        if (all == null) {
            all = new ArrayList<Goods>();
        }
        for (Iterator<Goods> iterator = collection.iterator(); iterator.hasNext();) {
            Goods gs = iterator.next();
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

    /**
     * 老板键操作，隐藏窗口
     */
    @Override
    public void bossKeyAction() {
        bossKeyHandler.handle(this);
    }

    class BossKeyHandler {
        /**
         * <code>ownWindowVisble</code> - {记录我的列表窗口的visible状态，再次点击快捷键的时候恢复使用}.
         */
        private boolean ownWindowVisible;

        /**
         * <code>ownWindowVisble</code> - {记录列表窗口的visible状态，再次点击快捷键的时候恢复使用}.
         */
        private boolean nullWindowVisible;

        public void handle(final MainFrame mainFrame) {
            if (mainFrame.isVisible()) {
                ownWindowVisible = ownFrame.isVisible();
                nullWindowVisible = nullFrame.isVisible();

                mainFrame.setVisible(false);
                ownFrame.setVisible(false);
                nullFrame.setVisible(false);
            } else {
                mainFrame.setVisible(true);
                ownFrame.setVisible(ownWindowVisible);
                nullFrame.setVisible(nullWindowVisible);
            }

        }
    }

    class Runner implements Runnable {
        private boolean enable = true;
        private final IMainFrame<Goods> mainFrame;

        public Runner(final IMainFrame<Goods> mainFrame) {
            this.mainFrame = mainFrame;
        }

        public void stop() {
            this.enable = false;
        }

        @Override
        public void run() {
            EtaoProcessor processor = new EtaoProcessor();
            while (enable) {
                try {
                    long start = System.currentTimeMillis();

                    List<Goods> results = processor.process();
                    // 刷新内容
                    if (results != null) {
                        mainFrame.updateAreaTxt(results);
                    }

                    DebugLogger.log("一次用时：" + (System.currentTimeMillis() - start));
                    Thread.sleep(10000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
