package crawler;

import crawler.entity.JiSuContent_ES;
import crawler.entity.JiSuContent_HTML;
import crawler.utils.MyConverter;
import crawler.utils.MyCrawler;
import crawler.utils.MyEsUtil;
import crawler.utils.MyParser;

public class Main {

	// 抓取的数量限制
	public static int CRAWL_LIMIT = 60000;

	// 抓取的地址，采用id序号递增方式抓取
	public static String CRAWL_URL = "http://www.jisuxz.com/down/";

	// 抓取时间间隔(毫秒)
	public static int FREEZE_TIME = 20;

	// 主程序
	public static void main(String[] args) {
		int index = 14233;
		int count = 0;
		while (count++ < CRAWL_LIMIT) {
			try {
				// 抓取网页
				String html = MyCrawler.getContentFromUrl(CRAWL_URL + index + ".html");
				// 解析内容
				JiSuContent_HTML htmlEntity = MyParser.parseContent(html);
				// 转换内容
				JiSuContent_ES esEntity = MyConverter.convertToEsEntity(htmlEntity);
				esEntity.setId(index + "");
				// 输出检查
				System.out.println(htmlEntity);
				System.out.println(esEntity);
				System.out.println();
				// 写入ES中
				MyEsUtil.addToEs(esEntity);
				// 暂停
				Thread.sleep(FREEZE_TIME);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 向后递增index
				index++;
			}
		}
	}

}
