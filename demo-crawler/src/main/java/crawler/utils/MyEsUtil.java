package crawler.utils;

import java.net.InetAddress;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import crawler.entity.JiSuContent_ES;

public class MyEsUtil {

	private static String host = "192.168.25.149"; // 服务器地址
	private static int port = 9300; // 端口
	
	private static String INDEX = "data"; // ES的Index
	private static String JISU = "jisu"; // ES的Index的type
	
	private static TransportClient client = null;
	
	/**
	 * 将ES对象写入到ES中
	 * @param esEntity
	 * @throws Exception
	 */
	public static void addToEs(JiSuContent_ES esEntity) throws Exception {
		try {
			client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
			
			IndexResponse response = client.prepareIndex(INDEX, JISU, esEntity.getId())
					.setSource(new Gson().toJson(esEntity), XContentType.JSON)
					.get();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}
	
}
