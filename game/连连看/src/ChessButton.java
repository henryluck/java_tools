

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import cn.elva.Settings;
import cn.elva.model.ArrayPoint;

public class ChessButton extends JButton
{
	// ��ť����Ӧ�������е�ֵ��λ��,��ArrayPoint�ṹ����ʾ
	protected ArrayPoint point = null;

	/**
	 * ���캯��,ָ����ť�������ֵ��λ��
	 * 
	 * @param row
	 *              �����к�
	 * @param col
	 *              �����к�
	 * @param value
	 *              �����ֵ
	 */
	public ChessButton(int row, int col, int value)
	{
		this(new ArrayPoint(row, col, value));
	}

	/**
	 * ���캯��
	 * 
	 * @param point
	 *              ֵ��λ�õ����ݽṹ
	 */
	public ChessButton(ArrayPoint point)
	{
		this.point = point;
		String name ="Resource/"+point.getValue() + Settings.RELEX;
		URL url = ChessButton.class.getResource(name);
//		System.out.println(url);
		ImageIcon icon = new ImageIcon( url );
		this.setIcon(icon);
	}

	/**
	 * ���캯��,ʹ��Ĭ��ֵ
	 */
	public ChessButton()
	{
		this(new ArrayPoint(0, 0, 0));
	}

	/**
	 * ���ص�ǰ��ť�����λ�ú�ֵ
	 * 
	 * @return point
	 */
	public ArrayPoint getPoint()
	{
		return point;
	}

	/**
	 * ���ô˰�ť�������λ�ú�ֵ
	 * 
	 * @param point
	 *              Ҫ���õ� point
	 */
	public void setPoint(ArrayPoint point)
	{
		this.point = point;
	}
}
