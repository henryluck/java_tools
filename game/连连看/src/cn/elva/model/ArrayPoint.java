/*
*�û��� AXL
*��  ��   2007-7-13
*���ڹ�˾ �����廪IT
*
*/

package cn.elva.model;

public class ArrayPoint
{
	//��ʾ��
	protected int i;
	//��ʾ��
	protected int j;
	//��ʾֵ
	protected int value;
	
	public ArrayPoint( int i,int j,int value )
	{
		this.i =i;
		this.j =j;
		this.value = value;
	}

	public String toString( )
	{
		return i+","+j+","+value;
	}
	/**
	 * @return i
	 */
	public int getI()
	{
		return i;
	}

	/**
	 * @param i Ҫ���õ� i
	 */
	public void setI(int i)
	{
		this.i = i;
	}

	/**
	 * @return j
	 */
	public int getJ()
	{
		return j;
	}

	/**
	 * @param j Ҫ���õ� j
	 */
	public void setJ(int j)
	{
		this.j = j;
	}

	/**
	 * @return value
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * @param value Ҫ���õ� value
	 */
	public void setValue(int value)
	{
		this.value = value;
	}
}
