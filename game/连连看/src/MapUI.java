import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cn.elva.Settings;
import cn.elva.model.ArrayPoint;
import cn.elva.model.Map;

public class MapUI extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;

	//private int bombCount = Settings.BOMBCOUNT;
	// ��������,�ð�ť����ʾ
	private ChessButton[] chesses = null;

	// ����ģ��
	private Map map = new Map();

	// �жϵ�ǰ����������Ƿ��ǵڶ���ѡ�е�
	private boolean two = false;

	// ǰ�����Ǹ�����
	private ArrayPoint priviousPoint;

	// �ڶ���ѡ�е�����
	private ArrayPoint currPoint;

	/**
	 * ���캯��
	 */
	public MapUI()
	{
		super();
		initialize();
	}

	/**
	 * ��ʼ������
	 * 
	 * @return void
	 */
	private void initialize()
	{
		initChesses();
		GridLayout gridLayout = new GridLayout(Settings.ROWS + 2,
				Settings.COLUMNS + 2);
		gridLayout.setHgap(2);
		gridLayout.setVgap(2);
		this.setLayout(gridLayout);
		this.setSize(400, 400);
		// ���ð�ť,����
		for (int row = 0; row < Settings.ROWS + 2; row++)
		{
			for (int col = 0; col < Settings.COLUMNS + 2; col++)
			{
				add(chesses[row * (Settings.COLUMNS + 2) + col]);
			}
		}
	}

	private void initChesses()
	{
		int[][] values = map.getMap();
		// ��ʼ������,������ģ���ﱣ��һ��
		this.chesses = new ChessButton[(Settings.ROWS + 2)
				* (Settings.COLUMNS + 2)];

		for (int row = 0; row < 10; row++)
		{
			for (int col = 0; col < 10; col++)
			{
				// ͨ����ά������ģ������õ�һά����������
				int index = row * (Settings.COLUMNS + 2) + col;
				// �����ӵ�����ģ��,��ArrayPoint�����������,ָ�������Ӿ����λ�ú�ֵ
				chesses[index] = new ChessButton(row, col, values[row][col]);
				// ��Ӽ�����
				chesses[index].addActionListener(this);
				// ����Χ��һȦ��Ϊ���ɼ�,��,��Ϊ0 ��Ϊ���ֵ�����
				if (row == 0 || row == (Settings.ROWS + 2 - 1) || col == 0
						|| col == (Settings.COLUMNS + 2 - 1))
				{
					chesses[index].setVisible(false);
				}
			}
		}
	}

	public void clearCheese(ArrayPoint priviousPoint, ArrayPoint currPoint)
	{
		// ����ƥ��,�������з���ͨ
		
			// �������ģ���е�����
			int[][] values = map.getMap();
			// ��ģ���ж�Ӧ��������Ϊ0
			values[priviousPoint.getI()][priviousPoint.getJ()] = 0;
			values[currPoint.getI()][currPoint.getJ()] = 0;

			// ʹ�����Ѿ������İ�ť���ɼ�
			int index1 = priviousPoint.getI() * (Settings.COLUMNS + 2)
					+ priviousPoint.getJ();
			int index2 = currPoint.getI() * (Settings.COLUMNS + 2)
					+ currPoint.getJ();
			chesses[index1].setVisible(false);
			chesses[index2].setVisible(false);
			

			// �������������Ϊ0,��������
			if (map.LEFTCOUNT == 0)
			{
				
				JOptionPane.showMessageDialog(this, "��ϲ��ͨ��!!");
			}
	}

	/**
	 * �¼�������������,Ҳ�Ǵ������������ĵط�
	 */
	public void actionPerformed(ActionEvent e)
	{
		// ��õ�ǰ�Ĺ���
		ChessButton button = (ChessButton) e.getSource();
		// ��õ�ǰ���ӵ����ݽṹ
		ArrayPoint p = button.getPoint();

		// ���������������ѡ��, ������жϲ���
		if (two)
		{
			currPoint = p;
			if( map.match(this.priviousPoint, this.currPoint))
			{
				clearCheese(this.priviousPoint, this.currPoint);
			}
			
			// ����Ϊû��������ť��ѡ�е�״̬
			two = false;
		}
		else
		{
			// ����ǰ��������Ӹ�������priviousPoint
			this.priviousPoint = p;
			// ��־λ��ΪTRUE,���ڵ���¸����ӵ�ʱ��ʹ��
			two = true;
		}

	}

	/**
	 * ը���Ĺ���
	 * 
	 */
	public void bomb()
	{
		int[][] values = map.getMap();
		ArrayPoint p1 = null;
		ArrayPoint p2 = null;

		for (int row = 1; row < Settings.ROWS + 1; row++)
		{
			for (int col = 1; col < Settings.COLUMNS + 1; col++)
			{
				if (values[row][col] != 0)
				{
					p1 = new ArrayPoint(row, col, values[row][col]);
					
					for (int i = 1; i < Settings.ROWS + 1; i++)
					{
						for (int j = 1; j < Settings.COLUMNS + 1; j++)
						{
							if (values[i][j] != 0)
							{
								p2 = new ArrayPoint(i, j, values[i][j]);
							}
							else
							{
								continue;
							}

							//System.out.println(p1 + "|" + p2);

							if (map.match(p1, p2))
							{
								clearCheese(p1, p2);
								return;
							}
						}
					}
				}
			}
		}	
	}
}
