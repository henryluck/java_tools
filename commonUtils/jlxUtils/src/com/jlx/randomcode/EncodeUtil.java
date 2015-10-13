package comdzy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class EncodeUtil {
	private static String sDyEncKey = "a"; 
	
	private static final EncodeUtil _inst = new EncodeUtil();

	private EncodeUtil() {
	}

	public static EncodeUtil inst() {
		return _inst;
	}

	 /*
   * 调用参数：
   * oriVal : 需要加密转换的字符串
   * changefreq ： 变化频率，单位分钟
   * getLastTwo：如需要得到当前结果值和上一个结果值，则此参数为true
   * 返回：{v1:当前值,v2:上一个值}
   */
private JSONObject getDynCodeByStr_CurrTime(String oriVal,int iChangeFreq,boolean getLastTwo){
  	JSONObject jRtn = new JSONObject();
  	Date d = new Date();
  	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
  	String sTime= df.format(d);
  	sTime=sTime.substring(0,sTime.length()-1);
  	jRtn.put("v1",A.get6NumsByMD5(oriVal+","+sDyEncKey+","+sTime));
  	
  	if (getLastTwo){
    	String sTime2 = df.format(addMinute(d,-iChangeFreq));
    	sTime2=sTime2.substring(0,sTime2.length()-1);
    	jRtn.put("v2", A.get6NumsByMD5(oriVal+","+sDyEncKey+","+sTime2));
  	}
  	return jRtn;
  }
	
/**
 * 内部方法。为指定日期增加相应的天数或月数
 * @param date 基准日期
 * @param amount 增加的数量
 * @param field 增加的单位，年，月或者日 ....
 * @return 增加以后的日期
 */
private static Date add(Date date, int amount, int field) {
  Calendar calendar = Calendar.getInstance();

  calendar.setTime(date);
  calendar.add(field, amount);

  return calendar.getTime();
}

private static Date addMinute(Date date, int minute) {
  return add(date, minute, Calendar.MINUTE);
}

public String getLastTwoCode(String id){
	JSONObject j = EncodeUtil.inst().getDynCodeByStr_CurrTime(id,60,true);
	return j.toString();
}

public String getCode(String id){
	JSONObject j = EncodeUtil.inst().getDynCodeByStr_CurrTime(id,60,false);
	return j.getString("v1");
}
//	public static void main(String[] args) {
//		String oriId = "13611020588"; // 一个标识串，比如代表某个手机、某个实体
//		JSONObject j = EncodeByTime.inst().getDynCodeByStr_CurrTime(oriId,1,true);
//		System.out.println(j.toJSONString());
//	}

	public static void main(String[] args) {
		if(args != null && args.length>0){
			String id = args[0];
			System.out.println(EncodeUtil.inst().getLastTwoCode(id));
		}
	}
}
