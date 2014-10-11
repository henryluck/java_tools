/*
 * �û��� AXL �� �� 2007-7-13 ���ڹ�˾ �����廪IT
 */

package cn.elva.model;

import java.util.Random;

import cn.elva.Settings;

/**
 * @author AXL ���ݲ���,һ���ڲ���������ʾҳ���ϵľ������
 */
public class Map
{
	public static int LEFTCOUNT = Settings.ROWS * Settings.COLUMNS;

	// �������������ݲ���ʾ,���Խ���߿��������������
	private int[][] map = new int[Settings.ROWS + 2][Settings.COLUMNS + 2];

	// private ArrayPoint prePoint;

	// private ArrayPoint currPoint;

	// ���ֵĲ�ͬͼƬ����
	private int maxKinds = 4;

	public Map()
	{
		init();
	}

	public int[][] getMap()
	{
		return map;
	}

	/**
	 * ʵ���˶�ά����Ĳ���
	 */
	private void init()
	{
		int[] tempArr = new int[Settings.ROWS * Settings.COLUMNS];
		int len = tempArr.length;

		// ����ͼƬ����������ȷ�������С,����64��ͼƬ,ÿ�ĸ�Ϊһ����,����ҪͼƬ��Ϊ64/4=16
		for (int i = 0; i < len / maxKinds; i++)
		{
			tempArr[i * 4] = i + 1;
			tempArr[i * 4 + 1] = i + 1;
			tempArr[i * 4 + 2] = i + 1;
			tempArr[i * 4 + 3] = i + 1;
		}

		// ����һά���������ݵ�����
		random(tempArr);

		// ��䵽��ά������
		for (int i = 1; i < Settings.ROWS + 1; i++)
		{
			for (int j = 1; j < Settings.COLUMNS + 1; j++)
			{
				this.map[i][j] = tempArr[(i - 1) * Settings.COLUMNS
						+ (j - 1)];
			}
		}
	}

	/**
	 * �������������,ʹ�������״̬
	 * 
	 * @param array
	 *              Ҫ���ŵ�����
	 */
	private void random(int[] array)
	{
		Random random = new Random();
		int len = array.length;
		for (int i = len; i > 0; i--)
		{
			int j = random.nextInt(i);
			int temp = array[i - 1];
			array[i - 1] = array[j];
			array[j] = temp;
		}
	}

	/**
	 * �ж��Ƿ���һ��ֱ����,���ﲻȥ�Ƚ�����ֵ�Ƿ����,��Ҫ���ں��������յ�����
	 * 
	 * @param p1
	 *              ֮ǰ�ĵ�
	 * @param p2
	 *              ��ǰ����ĵ�
	 * @return true ��ͨ,false ��ͨ
	 */
	public boolean oneLineWithoutValue(ArrayPoint p1, ArrayPoint p2)
	{
		if (horizonMatch(p1, p2))
		{
			return true;
		}
		else if (verticalMatch(p1, p2))
		{
			return true;
		}
		return false;
	}

	/**
	 * �ж��Ƿ���һ��ֱ����,���а����˴�ֱ��ˮƽ�������
	 * 
	 * @param p1
	 *              ֮ǰ�ĵ�
	 * @param p2
	 *              ��ǰ����ĵ�
	 * @return true ��ͨ,false ��ͨ
	 */
	public boolean oneLine(ArrayPoint p1, ArrayPoint p2)
	{
		
		if (p1.value != p2.value)
		{
			return false;
		}
		if (oneLineWithoutValue(p1, p2))
		{
			return true;
		}
		return false;
	}

	/**
	 * �����ϵ��ж�
	 * 
	 * @param p1
	 *              ֮ǰ�ĵ�
	 * @param p2
	 *              ��ǰ����ĵ�
	 * @return true ��ͨ,false ��ͨ
	 */
	public boolean verticalMatch(ArrayPoint p1, ArrayPoint p2)
	{
		if (p1.j != p2.j)
		{
			return false;
		}
		if (p1.i > p2.i)
		{
			ArrayPoint temp = null;
			temp = p1;
			p1 = p2;
			p2 = temp;
		}
		// ֮��������������
		int spaceCount = p2.i - p1.i;

		// �������,ֱ������
		if (spaceCount == 1 && p1.value == p2.value)
		{
			return true;
		}

		for (int i = p1.i + 1; i < p2.i; i++)
		{
			ArrayPoint point = new ArrayPoint(i, p1.j, map[i][p1.j]);
			// ֻҪ��ֵΪ0�ͱ�ʾû������
			if (point.value != 0)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * ˮƽ������ж�
	 * 
	 * @param p1
	 *              ֮ǰ��ѡ�ĵ�
	 * @param p2
	 *              ��ǰѡ�еĵ�
	 * @return true ��ͨ,false ��ͨ
	 */
	public boolean horizonMatch(ArrayPoint p1, ArrayPoint p2)
	{
		// ������кŲ��ȵĻ�,�ղ���ˮƽ����Ľ��
		if (p1.i != p2.i)
		{
			return false;
		}
		if (p1.j > p2.j)
		{
			ArrayPoint temp = null;
			temp = p1;
			p1 = p2;
			p2 = temp;
		}
		// ˮƽ���������
		int spaceCount = p2.j - p1.j;
		// �������,ֱ������
		if (spaceCount == 1 && p1.value == p2.value)
		{
			return true;
		}
		for (int i = p1.j + 1; i < p2.j; i++)
		{
			ArrayPoint p = new ArrayPoint(p1.i, i, map[p1.i][i]);
			if (p.value != 0)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * �ж�ǰ�������ǲ���ͬһ����,i,j��ͬ����һ����
	 * 
	 * @param p1
	 *              ֮ǰ��ѡ�ĵ�
	 * @param p2
	 *              ��ǰѡ�еĵ�
	 * @return true ͬһ����,false ��ͬ��
	 */
	private boolean isSameOne(ArrayPoint p1, ArrayPoint p2)
	{
		if (p1.i == p2.i && p1.j == p2.j)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * ��һ���յ�����,�����Ƚ�ֵ�Ĵ�С�Ƿ����
	 * 
	 * @param p1
	 *              ��ʼ��
	 * @param p2
	 *              ������
	 * @return true ��ͨ,false ��ͨ
	 */
	public boolean oneConnerWithoutValue(ArrayPoint p1, ArrayPoint p2)
	{
		// ��ȡP1ˮƽ����Ĺյ�
		ArrayPoint p1H = new ArrayPoint(p1.i, p2.j, map[p1.i][p2.j]);
		// ��ȡP1��ֱ����Ĺյ�
		ArrayPoint p1V = new ArrayPoint(p2.i, p1.j, map[p2.i][p1.j]);

		if (horizonMatch(p1, p1H) && (p1H.value == 0))
		{
			if (this.verticalMatch(p1H, p2))
			{
				return true;
			}
		}

		if (verticalMatch(p1, p1V) && (p1V.value == 0))
		{
			if (horizonMatch(p1V, p2))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * ��һ���յ�����
	 * 
	 * @param p1
	 *              ��ʼ��
	 * @param p2
	 *              ������
	 * @return true ��ͨ,false ��ͨ
	 */
	public boolean oneConner(ArrayPoint p1, ArrayPoint p2)
	{
		// ��ȡP1ˮƽ����Ĺյ�
		ArrayPoint p1H = new ArrayPoint(p1.i, p2.j, map[p1.i][p2.j]);
		// ��ȡP1��ֱ����Ĺյ�
		ArrayPoint p1V = new ArrayPoint(p2.i, p1.j, map[p2.i][p1.j]);

		if (p1.value != p2.value)
		{
			return false;
		}

		if (oneConnerWithoutValue(p1, p2))
		{
			return true;
		}
		return false;
	}

	/**
	 * �������յ�����,���ĸ�������,��������,ֻҪһ������ͨ��,��ͨ��
	 * 
	 * @param p1
	 *              ��ʼ��
	 * @param p2
	 *              ������
	 * @return true ��ͨ,false ��ͨ
	 */
	public boolean twoConner(ArrayPoint p1, ArrayPoint p2)
	{
		if (p1.value != p2.value)
		{
			return false;
		}

		int count = 0;
		ArrayPoint temp = null;

		// ��
		for (int col = p1.j - 1; col >= 0; col--)
		{
			temp = new ArrayPoint(p1.i, col, map[p1.i][col]);
			if ((temp.value == 0) && this.oneLineWithoutValue(p1, temp))
			{
				if (this.oneConnerWithoutValue(temp, p2))
				{
					return true;
				}
			}
			else
			{
				break;
			}
		} // ��

		count = Settings.COLUMNS + 2 - p1.j;
		for (int col = p1.j; col < Settings.COLUMNS + 2; col++)
		{
			temp = new ArrayPoint(p1.i, col, map[p1.i][col]);
			if ((temp.value == 0) && oneLineWithoutValue(p1, temp))
			{
				if (oneConnerWithoutValue(temp, p2))
				{
					return true;
				}
			}
		}
		// ��
		count = p1.i;
		for (int row = count - 1; row >= 0; row--)
		{
			temp = new ArrayPoint(row, p1.j, map[row][p1.j]);
			if ((temp.value == 0) && oneLineWithoutValue(p1, temp))
			{
				if (oneConnerWithoutValue(temp, p2))
				{
					return true;
				}
			}
		}
		// ��
		for (int row = p1.i + 1; row < Settings.ROWS + 2; row++)
		{
			temp = new ArrayPoint(row, p1.j, map[row][p1.j]);
			if ((temp.value == 0) && oneLineWithoutValue(p1, temp))
			{
				if (oneConnerWithoutValue(temp, p2))
				{
					return true;
				}
			}
		}
		//
		return false;
	}

	/**
	 * �ж������Ƿ�ƥ��,����ֱ��,һ���յ�,�����յ��������
	 * 
	 * @param p1
	 *              ��ʼ��
	 * @param p2
	 *              ������
	 * @return true ��ͨ,false ��ͨ
	 */
	public boolean match(ArrayPoint p1, ArrayPoint p2)
	{
		if (this.isSameOne(p1, p2))
		{
			return false;
		}

		if (oneLine(p1, p2))
		{
			map[p1.i][p1.j] = 0;
			map[p2.i][p2.j] = 0;
			LEFTCOUNT -= 2;
			return true;
		}
		if (oneConner(p1, p2))
		{
			map[p1.i][p1.j] = 0;
			map[p2.i][p2.j] = 0;
			LEFTCOUNT -= 2;
			return true;
		}
		if (twoConner(p1, p2))
		{
			map[p1.i][p1.j] = 0;
			map[p2.i][p2.j] = 0;
			LEFTCOUNT -= 2;
			return true;
		}
		return false;
	}
	
	
}
