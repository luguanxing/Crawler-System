package crawler.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.entity.JiSuContent_HTML;

public class MyParser {

	/**
	 * 从抓取到的网页html解析内容得到实体数据
	 * @param htmlContent
	 * @return
	 */
	public static JiSuContent_HTML parseContent(String htmlContent) throws Exception {
		JiSuContent_HTML object = new JiSuContent_HTML();
		
		Document document = Jsoup.parse(htmlContent);
		// 解析名字
		Elements elements = document.select(".cont-title-h1 h1");
		for (Element element : elements) {
			object.setName(element.text());
		}
		
		// 解析评级
		 elements = document.select(".stars");
		 object.setRate(elements.get(0).toString().split("-")[1].charAt(0) + "");
		
		// 解析其余信息
		elements = document.select(".info ul li");
		for (Element element : elements) {
			String[] keyValues = element.text().split("：");
			switch (keyValues[0]) {
				case "软件大小":
					object.setSize(keyValues[1]);
					break;
				case "软件语言":
					object.setLanguage(keyValues[1]);
					break;
				case "更新时间":
					object.setUpdateTime(keyValues[1]);
					break;
				case "下载次数":
					object.setDownloadTimes(keyValues[1]);
					break;
				case "软件类别":
					object.setCategory(keyValues[1]);
					break;
				case "软件类型":
					object.setType(keyValues[1]);
					break;
				default:
					break;
			}
		}
		
		return object;
	}
	
}
