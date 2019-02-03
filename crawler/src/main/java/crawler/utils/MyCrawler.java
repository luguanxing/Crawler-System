package crawler.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import crawler.entity.BaoTuContent_ES;
import crawler.entity.BaoTuContent_HTML;
import crawler.entity.JiSuContent_ES;
import crawler.entity.JiSuContent_HTML;
import crawler.entity.SheTuContent_ES;
import crawler.entity.SheTuContent_HTML;

public class MyCrawler {

	private static String getContentFromUrl(String url) throws Exception {
		// 创建httpClient客户端实例
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//设置代理IP
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(10000)	//连接到服务器超时时间
				.setSocketTimeout(10000)	//从服务器获取内容超时时间
				.build();	
		// 创建httpGet请求实例,设置头信息和其它配置
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
		httpGet.setConfig(config);
		// 执行httpGet请求实例获取返回报文
		CloseableHttpResponse response = null;
		response = httpClient.execute(httpGet);
		// 获取报文内容获取信息
		HttpEntity entity = response.getEntity();
		String htmlContent = EntityUtils.toString(entity, "gbk");
		// 关闭资源
		response.close();
		httpClient.close();
		// 返回结果
		return htmlContent;
	}

	// 从url抓取软件数据并保存
	public static void getAndSaveJisuDataFromUrl(String url) {
		try {
			// 抓取网页
			String html = MyCrawler.getContentFromUrl(url);
			// 解析内容
			JiSuContent_HTML htmlEntity = MyParser.parseJiSuContent(html);
			// 转换内容
			JiSuContent_ES esEntity = MyConverter.convertToJiSuEsEntity(htmlEntity);
			// 输出检查
			System.out.println(htmlEntity);
			System.out.println(esEntity);
			System.out.println();
			// 写入ES中
			MyEsUtil.addJiSuToEs(esEntity);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	// 从url抓取图片数据并保存
	public static void getAndSaveTupianDataFromUrl(String url) {
		try {
			// 抓取网页
			String html = MyCrawler.getContentFromUrl(url);
			// 解析内容
			BaoTuContent_HTML htmlEntity = MyParser.parseBaotuContent(html);
			// 转换内容
			BaoTuContent_ES baoTuContent_ES = MyConverter.convertToBaoTuEsEntity(htmlEntity);
			// 写入ES中
			MyEsUtil.addBaotuToEs(baoTuContent_ES);
			// 输出检查
			System.out.println(htmlEntity);
			System.out.println(baoTuContent_ES);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 从url抓取音频数据并保存
	public static void getAndSaveYinpinDataFromUrl(String url) {
		try {
			// 抓取网页
			String html = MyCrawler.getContentFromUrl(url);
			// 解析内容
			SheTuContent_HTML htmlEntity = MyParser.parseShetuContent(html);
			// 转换内容
			SheTuContent_ES sheTuContent_ES = MyConverter.convertToSheTuEsEntity(htmlEntity);
			// 写入ES中
			MyEsUtil.addShetuToEs(sheTuContent_ES);
			// 输出检查
			System.out.println(htmlEntity);
			System.out.println(sheTuContent_ES);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
