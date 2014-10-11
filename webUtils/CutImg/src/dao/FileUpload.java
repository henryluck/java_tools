package dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUpload {
	   //获取文件的一些信息

	
	 /**

	  * @param args

	  *            文件字节输入、输出流方法拷贝一个文件到指定目录

	  */

	 //拷贝的方法

	  public void copy(File f1, File f2) {

	  try {

	   // 建立相关的字节输入流

	   FileInputStream fr = new FileInputStream(f1); // 通过打开一个到实际文件的连接来创建一个

	               // FileInputStream，该文件通过文件系统中的路径名

	               // f1 指定。

	   // 创建一个向具有指定名称的文件中写入数据的输出文件流。

	   FileOutputStream fw = new FileOutputStream(f2);

	   byte buffer[] = new byte[512]; // 声明一个byte型的数组，数组的大小是512个字节

	   while (fr.read(buffer) != -1) { // read()从此输入流中读取一个数据字节，只要读取的结果不！=-1就执行while循环中的语句块

	    fw.write(buffer); //write(byte[] b)将 b.length 个字节从指定字节数组写入此文件输出流中。

	   }

	   fw.close();// 关闭此文件输出流并释放与此流有关的所有系统资源。

	   fr.close();

	   System.out.println("文件" + f1.getName() + "里的内容已拷贝到文件"

	     + f2.getName() + "中！");

	  } catch (IOException ioe) {

	   System.out.println(ioe);

	  } catch (Exception e) {

	   System.out.println(e);

	  }

	 }

	 /*

	  * 拷贝前的判断 f1该文件通过文件系统中的路径名 f2新的文件通过文件系统中的路径名 fs是新的文件名称

	  */

	 public void bcopy(File f1, File f2, String fs) {

	  File f3;

	  if (f1.exists()) {

	   // 判断要拷贝的文件是否存在

	   if (!f2.exists()) {//判f2是否存在

	    f2.mkdirs();// 建立相应的目录

	   }  

	   f3 = new File(f2,fs);// 根据 f2 抽象路径名和 fs 路径名字符串创建一个新 File 实例

	   copy(f1,f3);//调用copy方法

	   System.out.println("上传文件相关的信息：（名字，路径，大小，最后修改的时间是：）");

	   getinfo(f1);

	   getinfo(f2);

	   System.out.println("上传成功后文件相关的信息：（名字，路径，大小，最后修改的时间是：）");

	   getinfo(f3);

	  } else {

	   System.out.println("要拷贝的文件不存在!");

	  }

	 }

	 //获取文件的一些信息
	 private void getinfo(File f1) {

	  // TODO Auto-generated method stub

	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");

	  if (f1.isFile()) {//isFile()方法是测试此抽象路径名表示的文件是否是一个标准文件。

	   System.out.println("文件名称：" + f1.getName());

	   System.out.println("文件路径：" + f1.getAbsolutePath());

	   System.out.println("文件大小：" + f1.length()+"字节(byte)");

	   System.out.println("最后修改的时间是：" + sdf.format(new Date(f1.lastModified())));

	  } else {

	   System.out.println("上传成功后获取服务器的相关信息：");

	   System.out.println("目录名称：" + f1.getName());

	   System.out.println("文件路径：" + f1.getAbsolutePath());

	   File[] files = f1.listFiles();

	   System.out.println("此目录中有" + files.length + "个文件！");

	  }

	  System.out.println("_______________________________");

	 }

	 public static void main(String[] args) {

		  // TODO Auto-generated method stub

		  FileUpload fc = new FileUpload();

		  File f1, f2, f3;

		  f1 = new File("E:/1/1.jpg");

		  f2 = new File("E:/2");//创建新的目录名称，服务器存放文件的目录

		  fc.bcopy(f1, f2, "2.jpg");

		 }
	 
}
