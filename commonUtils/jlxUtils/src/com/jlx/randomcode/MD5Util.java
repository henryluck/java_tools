package comdzy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
  private static volatile MD5Util _inst = null;
  private static Object _lock = new Object();

  private MD5Util() {
    _inst = this;
  }

  public static MD5Util inst() {
    if (_inst != null)
      return _inst;
    synchronized (_lock) {
      if (_inst == null) {
        _inst = new MD5Util();
      }
    }
    return _inst;
  }
/**
* 默认的密码字符串组合，apache校验下载的文件的正确性用的就是默认的这个组合
*/
private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
private static MessageDigest messagedigest = null;

static{
   try{
    messagedigest = MessageDigest.getInstance("MD5");
   }catch(NoSuchAlgorithmException nsaex){
    System.err.println(MD5Util.class.getName()+"初始化失败，MessageDigest不支持MD5Util。");
    nsaex.printStackTrace();
   }
}

/**
* 适用于上G大的文件
* @param file
* @return
* @throws IOException
*/
private static String getFileMD5String(File file) throws IOException {
   FileInputStream in = new FileInputStream(file);
   FileChannel ch = in.getChannel();
   MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
   messagedigest.update(byteBuffer);
   return bufferToHex(messagedigest.digest());
}

private static String getMD5String(String s) {
   return getMD5String(s.getBytes());
}

private static String getMD5String(byte[] bytes) {
   messagedigest.update(bytes);
   return bufferToHex(messagedigest.digest());
}

private static String bufferToHex(byte bytes[]) {
   return bufferToHex(bytes, 0, bytes.length);
}

private static String bufferToHex(byte bytes[], int m, int n) {
   StringBuffer stringbuffer = new StringBuffer(2 * n);
   int k = m + n;
   for (int l = m; l < k; l++) {
    appendHexPair(bytes[l], stringbuffer);
   }
   return stringbuffer.toString();
}


private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
   char c0 = hexDigits[(bt & 0xf0) >> 4];
   char c1 = hexDigits[bt & 0xf];
   stringbuffer.append(c0);
   stringbuffer.append(c1);
}

private static boolean checkPassword(String password, String md5PwdStr) {
   String s = getMD5String(password);
   return s.equals(md5PwdStr);
}

/*
 * 将输入的字符串经过MD5之后，再取前6个字符的数字最后一位，拼成一个6位数字返回，如：
 * 输入：
 * 18008434182,drccy2059,2014-05-19 21:3 -->
 * 567a983d7745ae7897929525d746179f -->
 * 53:54:55:97:57:56 --> 
 * 345776
 */
public static String get6NumsByMD5(String s0){
	String s1= MD5Util.getMD5String(s0);
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i<6 && i<s1.length() ; i++) {
		String  sX = ""+ (int) s1.charAt(i);
		sb.append(sX.charAt(sX.length()-1));
	}
	return sb.toString();
}

	// 参考：Java计算文件MD5值， http://www.oschina.net/code/snippet_230123_22951
	private static String getMd5ByFile(File file) {
		String value = null;
		FileInputStream in = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length = -1;
			while ((length = in.read(buffer)) != -1) {
				md5.update(buffer, 0, length);
			}
			value = bufferToHex(md5.digest());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception ex1) {
			}
		}
		return value;
	}

//public static void main(String[] args) throws IOException {
////   long begin = System.currentTimeMillis();
//
////   File big = new File("d:/cotrsDB.rar");
//
////   String md5=getFileMD5String(big);
//		System.out.println("user=7758,md5 pwd="+ getMD5String(get6NumsByMD5("o7758"+"_"+ "ncis")));
//
//   long end = System.currentTimeMillis();
////   System.out.println("md5:"+md5+" time:"+((end-begin)/1000)+"s");
//}

}