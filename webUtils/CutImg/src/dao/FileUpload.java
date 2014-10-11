package dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUpload {
	   //��ȡ�ļ���һЩ��Ϣ

	
	 /**

	  * @param args

	  *            �ļ��ֽ����롢�������������һ���ļ���ָ��Ŀ¼

	  */

	 //�����ķ���

	  public void copy(File f1, File f2) {

	  try {

	   // ������ص��ֽ�������

	   FileInputStream fr = new FileInputStream(f1); // ͨ����һ����ʵ���ļ�������������һ��

	               // FileInputStream�����ļ�ͨ���ļ�ϵͳ�е�·����

	               // f1 ָ����

	   // ����һ�������ָ�����Ƶ��ļ���д�����ݵ�����ļ�����

	   FileOutputStream fw = new FileOutputStream(f2);

	   byte buffer[] = new byte[512]; // ����һ��byte�͵����飬����Ĵ�С��512���ֽ�

	   while (fr.read(buffer) != -1) { // read()�Ӵ��������ж�ȡһ�������ֽڣ�ֻҪ��ȡ�Ľ������=-1��ִ��whileѭ���е�����

	    fw.write(buffer); //write(byte[] b)�� b.length ���ֽڴ�ָ���ֽ�����д����ļ�������С�

	   }

	   fw.close();// �رմ��ļ���������ͷ�������йص�����ϵͳ��Դ��

	   fr.close();

	   System.out.println("�ļ�" + f1.getName() + "��������ѿ������ļ�"

	     + f2.getName() + "�У�");

	  } catch (IOException ioe) {

	   System.out.println(ioe);

	  } catch (Exception e) {

	   System.out.println(e);

	  }

	 }

	 /*

	  * ����ǰ���ж� f1���ļ�ͨ���ļ�ϵͳ�е�·���� f2�µ��ļ�ͨ���ļ�ϵͳ�е�·���� fs���µ��ļ�����

	  */

	 public void bcopy(File f1, File f2, String fs) {

	  File f3;

	  if (f1.exists()) {

	   // �ж�Ҫ�������ļ��Ƿ����

	   if (!f2.exists()) {//��f2�Ƿ����

	    f2.mkdirs();// ������Ӧ��Ŀ¼

	   }  

	   f3 = new File(f2,fs);// ���� f2 ����·������ fs ·�����ַ�������һ���� File ʵ��

	   copy(f1,f3);//����copy����

	   System.out.println("�ϴ��ļ���ص���Ϣ�������֣�·������С������޸ĵ�ʱ���ǣ���");

	   getinfo(f1);

	   getinfo(f2);

	   System.out.println("�ϴ��ɹ����ļ���ص���Ϣ�������֣�·������С������޸ĵ�ʱ���ǣ���");

	   getinfo(f3);

	  } else {

	   System.out.println("Ҫ�������ļ�������!");

	  }

	 }

	 //��ȡ�ļ���һЩ��Ϣ
	 private void getinfo(File f1) {

	  // TODO Auto-generated method stub

	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��hhʱmm��ss��");

	  if (f1.isFile()) {//isFile()�����ǲ��Դ˳���·������ʾ���ļ��Ƿ���һ����׼�ļ���

	   System.out.println("�ļ����ƣ�" + f1.getName());

	   System.out.println("�ļ�·����" + f1.getAbsolutePath());

	   System.out.println("�ļ���С��" + f1.length()+"�ֽ�(byte)");

	   System.out.println("����޸ĵ�ʱ���ǣ�" + sdf.format(new Date(f1.lastModified())));

	  } else {

	   System.out.println("�ϴ��ɹ����ȡ�������������Ϣ��");

	   System.out.println("Ŀ¼���ƣ�" + f1.getName());

	   System.out.println("�ļ�·����" + f1.getAbsolutePath());

	   File[] files = f1.listFiles();

	   System.out.println("��Ŀ¼����" + files.length + "���ļ���");

	  }

	  System.out.println("_______________________________");

	 }

	 public static void main(String[] args) {

		  // TODO Auto-generated method stub

		  FileUpload fc = new FileUpload();

		  File f1, f2, f3;

		  f1 = new File("E:/1/1.jpg");

		  f2 = new File("E:/2");//�����µ�Ŀ¼���ƣ�����������ļ���Ŀ¼

		  fc.bcopy(f1, f2, "2.jpg");

		 }
	 
}
