���ģʽ��ʵսMVCģʽ 
http://www.chinaunix.net ����:eclipse  �����ڣ�2002-09-03 15:47:21 

[������������eclipse�� 2002/09/03 03:49pm �༭]

���ģʽ��ʵսMVCģʽ    turbochen��ԭ���� 
  
�ؼ���     design pattern,mvc,observer,java 
  


 ����:
1.MVC
2.Observer�ӿ�
3.ģ��Model
4.��ͼView
5.������Controller
6.���г��� 


--------------------------------------------------------------------------------


MVC

��ģ�ͣ���ͼ��������(Model-View-Controller,MVC)�ṹ��Ϊ��Щ��ҪΪͬ���������ṩ�����ͼ��Ӧ�ó������Ƶģ����ܺõ�ʵ�������ݲ����ʾ��ķ��롣������ͼ�е����ӣ�
[img]http://www.cs.nyu.edu/courses/spring99/V22.0480-001/lectures/images/l14_im2.JPG[/img]
���ǿ�����ͼ�еļ��������Բ�ͬ����ʽ(View)���ֳ�����һ���Ǳ����ʽ��һ����ͼ����ʽ��

MVC������Ӧ�ó����Ϊ���ֶ�������:
ģ�ͣ�ά�����ݲ��ṩ���ݷ��ʷ�����
��ͼ������ģ�͵Ĳ������ݻ��������ݵĿ���ͼ��
�������������¼�.
�����ǵ��͵�MVCͨ�ŷ�ʽ,
[img]http://www.javable.com/columns/serv_side/workshop/11/1.gif[/img]

�¼��ɿ����������������������û��¼����������¼����������ı�ģ�͡�
��ͼ���Ȼ���ģ���еǼǣ���ģ�����ݷ����ı�ʱ������֪ͨ�����ģ�͵Ǽǵ�ÿ����ͼ��
��ͼ��ģȡ�����µ����ݲ�ˢ���Լ�.
Ҫʵ��MVC������Ҫ��һ��������ʹ��Design Pattern�е�Observerģʽ��Observerģʽ����ĳ�����������۲�Ķ������޸�ʱ֪ͨ����۲���(Observer).

�������Ǿ���ʵ�������������Obsererģʽʵ��MVC�ĳ���ṹ�����ҵ������У���Ҫʵ��һ��ѧ��������ʾ�����ӡ��ֱ����嵥��ͼ�εķ�ʽ��ʾÿ��ѧ�������䡣������ı�ʱ���Զ�������ʾ��

Observer�ӿ�

Ϊ��ʵ�ֹ۲�Ķ������޸�ʱ֪ͨ����۲��ߣ�ͨ��Ҫ�ڱ��۲�����۲���֮����һ��С�Ľӿڣ�����:
/* file: Observer.java */
public interface Observer
{
    public void dataUpdate(Model model);
}
����ӿ�����һ��dataUpdate(Model model)������ֻҪʵ��������ӿڶ��󣬾ͳ���һ���۲��ߡ�

ģ��Model

��������һ������ģ�͡����ҵ������У��Ƚ�����һ�����ݶ���:
/* file: Data.java */
public class Data
{
    public int value;����// ѧ������ֵ
    public String name;��// ѧ����
}
����������һ��Model:
/* file: Model.java */
import java.util.*;
public class Model
{
    ArrayList data = new ArrayList();
    ArrayList observer = new ArrayList();
    public Model()
    {    super();
    }
    public Model(int[] value, String[] name)
    {
        for ( int i = 0; i< value.length; i++ 
        {
            addData(value[i],name[i]);
        }
    }
    public Model(Data[] data)
    {
        for ( int i = 0; i< data.length; i++ 
        {
            addData(data[i]);
        }
    }
    public void addData(int value, String name)
    {
        Data data = new Data();
        data.value = value;
        data.name = name;
        this.data.add(data);
    }
    public void addData(Data data)
    {
        this.data.add(data);
    }
    public Data getData(int idx)
    {
        return (Data)(data.get(idx));
    }

    public int size()
    {
        return data.size();
    }
����// ������ģ���еǼǹ۲���.
    public void registerObserver(Observer o)
    {
        observer.add(o);
    }
    public void removeObserver(Observer o)
    {
        observer.remove(o);
    }
    // �����ݸı�ʱ����Controller���ô˷�����֪ͨ����Observer,ˢ����ͼ.
    public void changeModel(Model model)
    {
        data.clear();
        for (int i=0; i<model.size(); i++ 
        {
            this.addData(model.getData(i));
        }
        dataUpdate();
    }
    private void dataUpdate()
    {
        for (Iterator i = observer.iterator(); i.hasNext(); 
        {
            Observer o = (Observer)(i.next());
            o.dataUpdate(this);
        }
    }
}
���ģ���ṩ�������ݷ��ʵķ��������ṩһ��changeModel(Model model)������Controller���ʡ����ṩһ��registerObserver(Observer o)������������Model�еǼǹ۲���Observer��

��ͼView

����Ҫʵ��һ���嵥��ʾ��ʽ����ͼView1��һ��ͼ�η�ʽ��ʾ����ͼView2,��������ʵ��Observer�ӿڣ��Ա㵱Model���ݸı�ʱ���Զ�ˢ���Լ�.
/* file: View1.java */
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
public class View1 extends JPanel implements Observer
{
    Model model;

    public View1()
    {
    }
    public View1(Model model)
    {
        try
        {
            this.model = model;
            jbInit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private void jbInit() throws Exception
    {
        this.setBackground(Color.white);
        this.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black,1),"View1");
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if ( model == null  return;
        int x = 20,y = 50;
        int h = g.getFontMetrics().getHeight();
        for ( int i=0; i< model.size(); i++ 
        {
            Data data = model.getData(i);
            g.drawString(data.name,x,y);
            y+=h;
            g.drawString(String.valueOf(data.value),x,y);
            y+=h;
        }
    }
    // ��ģ�����ݷ����ı�ʱ�����Զ����ô˷�����ˢ��ͼ��
    public void dataUpdate(Model model)
    {
        /**@todo: Implement this Observer method*/
        this.model = model;
        repaint();
    }
}
/* file: View2.java */
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
public class View2 extends JPanel implements Observer
{
    Model model;

    public View2()
    {
    }
    public View2(Model model)
    {
        try
        {
            this.model = model;
            jbInit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private void jbInit() throws Exception
    {
        this.setBackground(Color.white);
        this.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black,1),"View1");
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if ( model == null  return;
        int x = 20,y = 50;
        int h = g.getFontMetrics().getHeight();
        int width = this.getWidth();
        int height = this.getHeight();
        int sy = height / model.size();
        int sx = width/ 2;
        for ( int i=0; i< model.size(); i++ 
        {
            Data data = model.getData(i);
            int value = data.value;
            int dx = 3;
            int r = 3;
            Color c = new Color((int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random()));
            int cx = sx;
            int cy = y+i * sy;
            for ( int j=0;j<value; j++ 
            {
                g.setColor(c);
                g.drawOval(cx,cy,r,r);
                r+=dx;
            }
            g.drawString(data.name,25,cy);
        }
    }
    // ��ģ�����ݷ����ı�ʱ�����Զ����ô˷�����ˢ��ͼ��
    public void dataUpdate(Model model)
    {
        /**@todo: Implement this Observer method*/
        this.model = model;
        repaint();
    }
}

������Controller

���ˣ�MVC�е�Model,Observer���������ˣ������������һ��Controller:
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
public class Controller extends JFrame
{
    Model model =  new Model();
    View1 view1 = new View1(model);
    View2 view2 = new View2(model);
    JScrollPane jScrollPane1 = new JScrollPane();
    JButton jButton1 = new JButton();
    JTextField jTextField1 = new JTextField();
    JTextField jTextField2 = new JTextField();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    public Controller()
    {
        try
        {
            jbInit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    private void jbInit() throws Exception
    {
        Data[] data = new Data[2];
        data[0] = new Data();
        data[1] = new Data();
        data[0].name = "Ted";
        data[0].value = 20;
        data[1].name = "Joy";
        data[1].value = 14;
        model.addData(data[0]);
        model.addData(data[1]);
        // ע���������У���ģ���еǼ����Ĺ۲���View1��View2.
        model.registerObserver(view1);
        model.registerObserver(view2);

        this.getContentPane().setLayout(null);
        jScrollPane1.setBounds(new Rectangle(0, 0, 3, 3));
        jButton1.setBounds(new Rectangle(309, 259, 101, 27));
        jButton1.setText("Update";
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                jButton1_actionPerformed(e);
            }
        });
        jTextField1.setText("20";
        jTextField1.setBounds(new Rectangle(80, 254, 52, 30));
        jTextField2.setText("14";
        jTextField2.setBounds(new Rectangle(178, 255, 50, 31));
        jLabel1.setText("Age:";
        jLabel1.setBounds(new Rectangle(41, 226, 47, 23));
        jLabel2.setText("Ted";
        jLabel2.setBounds(new Rectangle(42, 252, 35, 33));
        jLabel3.setText("Joy";
        jLabel3.setBounds(new Rectangle(144, 255, 31, 31));
        view1.setBounds(new Rectangle(7, 5, 225, 20);
        view2.setBounds(new Rectangle(234, 4, 219, 209));
        this.getContentPane().add(jScrollPane1, null);
        this.getContentPane().add(jTextField2, null);
        this.getContentPane().add(jTextField1, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(view1, null);
        this.getContentPane().add(view2, null);
    }
    // ����Update��ť��֪ͨModel���ݷ����ı�.
    void jButton1_actionPerformed(ActionEvent e)
    {
        Data[] data = new Data[2];
        data[0] = new Data();
        data[1] = new Data();
        data[0].name = jLabel1.getText();
        data[0].value = Integer.parseInt(jTextField1.getText());
        data[1].name = jLabel2.getText();
        data[1].value = Integer.parseInt(jTextField2.getText());
        Model m = new Model(data);
        this.model.changeModel(m);
    }
    public static void main(String[] args)
    {
        Controller c = new Controller();
        c.setSize(475,310);
        c.setVisible(true);
    }
}

���г���

��λ���Խ���Щ������Դ�Ϊ��Ӧ��Դ�ļ���ִ�������������
javac Controller.java
����
java Controller.class
�Ϳ��Կ�������ִ�е�Ч����



��������Ÿı�����ѧ�������䣬��һ��Update��ť����Ӧ����ͼ�͸����ˡ���ô�������鵽��MVC�ṹ����������ķ������˰�?;

  
 
