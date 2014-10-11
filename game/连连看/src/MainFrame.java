

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import cn.elva.Settings;
import cn.elva.model.Map;

public class MainFrame extends JFrame
{

	private static final long serialVersionUID = 1L;

	//ը���Ĵ���
	private int bombCount = Settings.BOMBCOUNT;
	
	private JPanel jContentPane = null;

	private JMenuBar menuBar = null;

	private JMenu fileMenu = null;

	private JMenuItem reloadItem = null;

	private JMenuItem startItem = null;
//ը��
	private JMenuItem bombItem = null;
	
	private JMenuItem exitItem = null;

	private MapUI mapUI = null;
	// ��Ϸ��ʼʱ��
	private long startTime;

	// ����ʱ��
	private long endTime;

	private Timer timer = null;

	// private JMenuItem ti
	private JMenuBar initMenuBar()
	{
		if (menuBar == null)
		{
			menuBar = new JMenuBar();
			fileMenu = new JMenu("�ļ�");

			startItem = new JMenuItem("��ʼ��Ϸ");
			startItem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					reload();
				}
			});
			reloadItem = new JMenuItem("����һ��");
			reloadItem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					reload();
				}
			});

			bombItem = new JMenuItem("ը��");
			bombItem.addActionListener(new ActionListener(){
				public void actionPerformed( ActionEvent e )
				{
					if( bombCount==0 )
					{
						JOptionPane.showMessageDialog(MainFrame.this,"���Ѿ�û��ը��������!!!" );
						bombItem.setEnabled(false);
						return;
					}
					mapUI.bomb();
					bombCount--;
				}
			});
			exitItem = new JMenuItem("�˳�");
			exitItem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			});

			fileMenu.add(startItem);
			fileMenu.add(reloadItem);
			fileMenu.add( bombItem );
			fileMenu.add(exitItem);
			menuBar.add(fileMenu);
		}

		return menuBar;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// PAN_TODO �Զ����ɷ������
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				MainFrame thisClass = new MainFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public MainFrame()
	{
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		//��С
		this.setSize(750, 750);
		// ���ھ���
		setLocationRelativeTo(null);   
		this.setTitle("llk");
		this.setJMenuBar(initMenuBar());
		// this.setContentPane(getJContentPane());
		this.setTitle("������");
	}

	private void reload()
	{
		mapUI = new MapUI();
		startTime = System.currentTimeMillis() / 1000;

		endTime = startTime + Settings.PERTIME;

		// jContentPane.setVisible(true);
		jContentPane = new JPanel();
		jContentPane.setLayout(new BorderLayout());
		jContentPane.add(mapUI);

		this.setContentPane(jContentPane);
		this.validate();
		Map.LEFTCOUNT = Settings.ROWS * Settings.COLUMNS;
		initTimer();
		
		bombItem.setEnabled(true);
		bombCount=Settings.BOMBCOUNT;
	}

	private void initTimer()
	{
		ActionListener actionListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				startTime = System.currentTimeMillis() / 1000;

				if (startTime == endTime)
				{
					JOptionPane.showMessageDialog(MainFrame.this, "ʱ�䵽��!!");
					int result = JOptionPane.showConfirmDialog(MainFrame.this,
							"����һ��?", "Again", JOptionPane.YES_NO_CANCEL_OPTION);
					if (result == JOptionPane.YES_OPTION)
					{
						reload();
					}
					else
					{
						jContentPane.setVisible(false);
						validate();
					}
				}
			}
		};
		timer = new javax.swing.Timer(1000, actionListener);
		timer.start();
	}
}
