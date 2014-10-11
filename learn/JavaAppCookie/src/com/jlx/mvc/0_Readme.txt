设计模式：实战MVC模式 
http://www.chinaunix.net 作者:eclipse  发表于：2002-09-03 15:47:21 

[这个贴子最后由eclipse在 2002/09/03 03:49pm 编辑]

设计模式：实战MVC模式    turbochen（原作） 
  
关键字     design pattern,mvc,observer,java 
  


 内容:
1.MVC
2.Observer接口
3.模型Model
4.视图View
5.控制器Controller
6.运行程序 


--------------------------------------------------------------------------------


MVC

“模型－视图－控制器(Model-View-Controller,MVC)结构是为那些需要为同样的数据提供多个视图的应用程序而设计的，它很好的实现了数据层与表示层的分离。例如下图中的例子：
[img]http://www.cs.nyu.edu/courses/spring99/V22.0480-001/lectures/images/l14_im2.JPG[/img]
我们看到，图中的几组数据以不同的形式(View)表现出来，一个是表格样式，一个是图形样式。

MVC把这种应用程序分为三种对象类型:
模型：维护数据并提供数据访问方法。
视图：给制模型的部分数据或所有数据的可视图。
控制器：处理事件.
以下是典型的MVC通信方式,
[img]http://www.javable.com/columns/serv_side/workshop/11/1.gif[/img]

事件由控制器来处理，控制器接收用户事件，并根据事件的类型来改变模型。
视图事先会在模型中登记，当模型数据发生改变时，马上通知已向此模型登记的每个视图。
视图从模取得最新的数据并刷新自己.
要实现MVC，最重要的一个环节是使用Design Pattern中的Observer模式。Observer模式允许某个对象在所观察的对象发生修改时通知多个观察者(Observer).

下面我们就以实例来讲解如何用Obserer模式实现MVC的程序结构。在我的例子中，我要实现一个学生年龄显示的例子。分别用清单和图形的方式显示每个学生的年龄。当年龄改变时，自动更新显示。

Observer接口

为了实现观察的对象发生修改时通知多个观察者，通常要在被观察者与观察者之间有一个小的接口，如下:
/* file: Observer.java */
public interface Observer
{
    public void dataUpdate(Model model);
}
这个接口中有一个dataUpdate(Model model)方法，只要实现了这个接口对象，就成了一个观察者。

模型Model

再来建立一个数据模型。在我的例子中，先建立了一个数据对象:
/* file: Data.java */
public class Data
{
    public int value;　　// 学生年龄值
    public String name;　// 学生名
}
现在来建立一个Model:
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
　　// 用来向模型中登记观察者.
    public void registerObserver(Observer o)
    {
        observer.add(o);
    }
    public void removeObserver(Observer o)
    {
        observer.remove(o);
    }
    // 当数据改变时，由Controller调用此方法，通知各个Observer,刷新视图.
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
这个模型提供各种数据访问的方法。并提供一个changeModel(Model model)方法供Controller访问。还提供一个registerObserver(Observer o)方法，用来向Model中登记观察者Observer。

视图View

我们要实现一个清单显示样式的视图View1和一个图形方式显示的视图View2,并让它们实现Observer接口，以便当Model数据改变时，自动刷新自己.
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
    // 当模型数据发生改变时，会自动调用此方法来刷新图形
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
    // 当模型数据发生改变时，会自动调用此方法来刷新图形
    public void dataUpdate(Model model)
    {
        /**@todo: Implement this Observer method*/
        this.model = model;
        repaint();
    }
}

控制器Controller

好了，MVC中的Model,Observer都建立好了，我们最后来做一个Controller:
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
        // 注意下面两行：向模型中登记它的观察者View1和View2.
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
    // 按下Update按钮，通知Model数据发生改变.
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

运行程序

各位可以将这些代码各自存为相应的源文件，执行以下命令编译
javac Controller.java
运行
java Controller.class
就可以看到程序执行的效果，



你可以试着改变两个学生的年龄，按一下Update按钮，相应的视图就更新了。怎么样，体验到了MVC结构给程序带来的方便性了吧?;

  
 
