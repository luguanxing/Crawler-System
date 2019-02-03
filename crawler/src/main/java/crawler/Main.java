package crawler;

import crawler.entity.BaoTuContent_ES;
import crawler.entity.BaoTuContent_HTML;
import crawler.entity.JiSuContent_ES;
import crawler.entity.JiSuContent_HTML;
import crawler.entity.SheTuContent_ES;
import crawler.entity.SheTuContent_HTML;
import crawler.utils.MyConverter;
import crawler.utils.MyCrawler;
import crawler.utils.MyEsUtil;
import crawler.utils.MyParser;

public class Main {

	// 抓取的数量限制
	public static int CRAWL_LIMIT_JISU = 5;
	public static int CRAWL_LIMIT_BAOTU = 5;
	public static int CRAWL_LIMIT_SHETU = 5;

	// 抓取的地址，采用id序号递增方式抓取
	public static String CRAWL_URL_JISU = "http://www.jisuxz.com/down/";
	public static String CRAWL_URL_BAOTU = "https://ibaotu.com/sucai/";
	public static String CRAWL_URL_SHETU = "http://699pic.com/media-";

	// 抓取时间间隔(毫秒)
	public static int FREEZE_TIME = 500;

	// 主程序
	public static void main(String[] args) {
		//getRuanjianData();
		//getTupianData();
		//getYinpinData();
	}

	// 爬取软件数据，url使用自增方式
	public static void getRuanjianData() {
		int index = 1;
		int count = 0;
		while (count++ < CRAWL_LIMIT_JISU) {
			try {
				MyCrawler.getAndSaveJisuDataFromUrl(CRAWL_URL_JISU + index + ".html");
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

	// 爬取图片数据，采用网页上一次性获取所有url的，再二次爬取
	public static void getTupianData() {
		
	}

	// 爬取音频数据，采用网页上一次性获取所有url的，再二次爬取
	public static void getYinpinData() {

	}

}
