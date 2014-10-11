package jlx.dwr.test;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * http://blog.csdn.net/caterpillar_here/archive/2006/09/18/1239538.aspx
 * 
 * @author Administrator
 * 
 */
public class Bike {
	private Map bikes;

	public Bike() {
		bikes = new TreeMap();
		bikes.put("2000", new String[] { "2000 T1", "2000 T2", "2000 T3" });
		bikes.put("2001", new String[] { "2001 A1", "2001 A2" });
		bikes.put("2002", new String[] { "2002 BW1", "2002 BW2", "2002 BW" });
		bikes.put("2003", new String[] { "2003 S320" });
		bikes.put("2004", new String[] { "2004 TA1", "2004 TA2", "2004 TA3" });
	}

	public String[] getYears() {
		String[] keys = new String[bikes.size()];
		int i = 0;
		Iterator keyset = bikes.keySet().iterator();
		while (keyset.hasNext()) {
			keys[i++] = (String) keyset.next();
		}
		return keys;
	}

	public String[] getBikes(String year) {
		return (String[]) bikes.get(year);
	}
}
