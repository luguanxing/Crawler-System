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

import crawler.entity.BaoTuContent_ES;
import crawler.entity.JiSuContent_ES;
import crawler.entity.SheTuContent_ES;

/**
 * 将ES对象写入到ES中
 */
public class MyEsUtil {

	private static String host = "127.0.0.1"; // 服务器地址
	private static int port = 9300; // 端口
	
	private static String INDEX = "data"; // ES的Index
	
	private static String JISU = "jisu";
	private static String BAOTU = "baotu";
	private static String SHETU = "shetu";
	
	private static TransportClient client = null;
	
	public static void addJiSuToEs(JiSuContent_ES esEntity) throws Exception {
		try {
			client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
			
			IndexResponse response = client.prepareIndex(INDEX, JISU, esEntity.getSoftId())
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

	public static void addBaotuToEs(BaoTuContent_ES esEntity) throws Exception {
		try {
			client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
			
			IndexResponse response = client.prepareIndex(INDEX, BAOTU, esEntity.getPicId())
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

	public static void addShetuToEs(SheTuContent_ES esEntity) {
		try {
			client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
			
			IndexResponse response = client.prepareIndex(INDEX, SHETU, esEntity.getMusicId())
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
