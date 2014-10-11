package samples.quickstart.service.pojo;

import java.util.HashMap;

/**
 * <strong>Title : StockQuoteService<br></strong>
 * <strong>Description : </strong><br> 
 * <strong>Create on : 2008-2-26<br></strong>
 * <p>
 * <strong>Copyright (C) Mocha Software Co.,Ltd.<br></strong>
 * <p>
 * @author jianglx jianglx@mochasoft.com.cn<br>
 * @version <strong>Mocha BPM v6.4.0</strong><br>
 * <br>
 * <strong>修改历史:</strong><br>
 * 修改人		修改日期		修改描述<br>
 * -------------------------------------------<br>
 * <br>
 * <br>
 */
public class StockQuoteService {
	private HashMap map = new HashMap();

	public double getPrice(String symbol) {
		Double price = (Double) map.get(symbol);
		if (price != null) {
			return price.doubleValue();
		}
		return 42.00;
	}

	public void update(String symbol, double price) {
		map.put(symbol, new Double(price));
	}
}
