package dao;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.*;
import javax.servlet.ServletContext;

import dao.FileUpload;

public class CommonMethod {

	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	//����ͼƬ
	public void CutImg(int imgWith,int imgHeight,int left,int top,int cutWidth,int cutHeight,String oldImg,String newPath)
	{
		File file = new File(oldImg); 
		Date dt=new Date();
		String imgName = sdf.format(dt)+".jpg";
		String newurl=newPath+"/"+imgName;  //�µ�����ͼ�����ַ
		Image src = null;
		try {
			src=javax.imageio.ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                     
		BufferedImage tag = new BufferedImage(cutWidth,cutHeight,BufferedImage.TYPE_3BYTE_BGR);//���û���
		tag.getGraphics().drawImage(src,-left,-top,imgWith,imgHeight,Color.white,null);//��ͼ
		//tag.getGraphics().drawImage(src,0,0,new_w,new_h,null);       //������С���ͼ
		FileOutputStream newimage=null;
		try {
			newimage=new FileOutputStream(newurl);
			javax.imageio.ImageIO.write(tag, "jpeg", newimage);
			newimage.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          //������ļ���
	}
	
	public String Upload(String upURL,String tmp)
	{
		File f2=new File(tmp);
		FileUpload load=new FileUpload();
		File f1=new File(upURL);
		Date dt=new Date();
		String imgName = sdf.format(dt)+".jpg";
		load.bcopy(f1, f2, imgName);
		return "tmp/"+imgName;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
