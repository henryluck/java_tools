package jlx.tools.refreshhouse.pagetask;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import jlx.tools.webfetcher.processor.IProcessor;
import jlx.tools.webfetcher.task.BaseConnInfo;
import jlx.util.RegexUtils;

public class BinHeJIeYuanProcessor implements IProcessor<HouseVO> {

	public static final String url = "https://tianjin.anjuke.com/sale/rd1/?from=zjsr&kw=%E7%A7%8B%E6%B6%A6%E5%AE%B6%E5%9B%AD";

	private String itemRegex = "<li class=\"list-item\" data-from=\"\">(.*?)</li>";
	private String titleRegex = "<a data-from=\"\" data-company=\"\" title=\"(.*?)\"";
	private String priceRegex = "class=\"unit-price\">(\\d*)";
	private String priceAllRegex = "<strong>(.*?)</strong>";
	private String urlRegex = "href=\"(.*?)\"";
	private String imgRegex = "src=\"(.*?)\"";

	@Override
	public List<HouseVO> process(String html, BaseConnInfo arg1) {
		try {
			List<HouseVO> result = new ArrayList<HouseVO>();

			List<String> alist = RegexUtils.getMatchList(html, itemRegex);
			for (String item : alist) {
				String priceStr = RegexUtils.getMatchString(item, priceRegex);
				int price = Integer.parseInt(priceStr);
				if (price < 35000) {
					HouseVO vo = new HouseVO();
					vo.title = RegexUtils.getMatchString(item, titleRegex);
					vo.url = RegexUtils.getMatchString(item, urlRegex);
					vo.imageUrl = RegexUtils.getMatchString(item, imgRegex);
					vo.priceAll = RegexUtils.getMatchString(item, priceAllRegex);
					vo.price = priceStr;
					Document doc = Jsoup.parse(item);
					Elements elementsByClass = doc.getElementsByClass("details-item");
					vo.content = elementsByClass.first().text();
					result.add(vo);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
