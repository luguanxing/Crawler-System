package crawler;

import java.util.List;

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

	// 抓取时间间隔(毫秒)
	public static int FREEZE_TIME = 100;

	// 主程序
	public static void main(String[] args) {
		 getRuanjianData();
		 getTupianData();
		 getYinpinData();
	}

	// 爬取软件数据，url使用自增方式
	public static void getRuanjianData() {
		int index = 1;
		int limit = 70000;
		while (index < limit) {
			try {
				MyCrawler.getAndSaveJisuDataFromUrl("http://www.jisuxz.com/down/" + index + ".html");
				Thread.sleep(FREEZE_TIME);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				index++;
			}
		}
	}

	// 爬取图片数据，采用网页上一次性获取所有url的，再二次爬取
	public static void getTupianData() {
		int pageIndex = 1;
		int pageLimit = 223;
		while (pageIndex <= pageLimit) {
			try {
				// 抓取每页html，解析出每页所含的图片页url
				String pageUrl = "https://ibaotu.com/sy/17-0-0-0-0-" + pageIndex + ".html";
				String html = MyCrawler.getHtmlContentFromUrl(pageUrl);
				List<String> urls = MyParser.getBaoTuUrls(html);
				// 进入每个图片页url保存
				for (String url : urls) {
					MyCrawler.getAndSaveTupianDataFromUrl(url);
					Thread.sleep(FREEZE_TIME);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pageIndex++;
			}
		}
	}

	// 爬取音频数据，采用网页上一次性获取所有url的，再二次爬取
	public static void getYinpinData() {
		int pageIndex = 1;
		int pageLimit = 336;
		while (pageIndex <= pageLimit) {
			try {
				// 抓取每页html，解析出每页所含的音乐页url
				String pageUrl = "http://699pic.com/media/soundtrack-so-" + pageIndex + "-0-0-0-0-0-0-0.html";
				String html = MyCrawler.getHtmlContentFromUrl(pageUrl);
				List<String> urls = MyParser.getSheTuUrls(html);
				// 进入每个图片页url保存
				for (String url : urls) {
					MyCrawler.getAndSaveYinpinDataFromUrl(url);
					Thread.sleep(FREEZE_TIME);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pageIndex++;
			}
		}
	}

}
