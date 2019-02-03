package crawler.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class MyCrawler {

	/**
	 * 从url抓取内容的函数
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String getContentFromUrl(String url) throws Exception {
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
	
}
