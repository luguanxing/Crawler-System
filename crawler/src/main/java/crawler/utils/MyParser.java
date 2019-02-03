package crawler.utils;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.entity.BaoTuContent_HTML;
import crawler.entity.JiSuContent_HTML;
import crawler.entity.SheTuContent_HTML;

/**
 * 从抓取到的网页html解析内容得到实体数据
 */
public class MyParser {

	public static JiSuContent_HTML parseJiSuContent(String htmlContent) throws Exception {
		JiSuContent_HTML object = new JiSuContent_HTML();
		Document document = Jsoup.parse(htmlContent);
		// 解析名字
		Elements elements = document.select(".cont-title-h1 h1");
		for (Element element : elements) {
			object.setSoftName(element.text());
		}
		// 解析id
		elements = document.select("input[name='postid']");
		for (Element element : elements) {
			object.setSoftId(element.attr("value"));
		}
		// 解析评级
		elements = document.select(".stars");
		object.setSoftRate(elements.get(0).toString().split("-")[1].charAt(0) + "");
		// 解析其余信息
		elements = document.select(".info ul li");
		for (Element element : elements) {
			String[] keyValues = element.text().split("：");
			switch (keyValues[0]) {
			case "软件大小":
				object.setSoftSize(keyValues[1]);
				break;
			case "软件语言":
				object.setSoftLanguage(keyValues[1]);
				break;
			case "更新时间":
				object.setSoftUpdateTime(keyValues[1]);
				break;
			case "下载次数":
				object.setSoftDownloadTimes(keyValues[1]);
				break;
			case "软件类别":
				object.setSoftCategory(keyValues[1]);
				break;
			case "软件类型":
				object.setSoftType(keyValues[1]);
				break;
			default:
				break;
			}
		}
		return object;
	}

	public static BaoTuContent_HTML parseBaoTuContent(String htmlContent) throws Exception {
		BaoTuContent_HTML object = new BaoTuContent_HTML();
		Document document = Jsoup.parse(htmlContent);
		// 解析名字
		Elements elements = document.select(".works-name");
		for (Element element : elements) {
			object.setPicName(element.text());
		}
		// 解析id
		elements = document.select(".img-id");
		for (Element element : elements) {
			object.setPicId(element.text());
		}
		// 解析颜色模式
		elements = document.select(".color-mode");
		for (Element element : elements) {
			object.setPicColorMode(element.text());
		}
		// 解析像素大小
		elements = document.select(".img-size");
		for (Element element : elements) {
			object.setPicPixel(element.text());
		}
		// 解析文件大小
		elements = document.select(".file-size");
		for (Element element : elements) {
			object.setPicSize(element.text());
		}
		// 解析文件格式
		elements = document.select(".file-type");
		for (Element element : elements) {
			object.setPicFormat(element.text());
		}
		// 解析推荐软件
		elements = document.select(".file-tool");
		for (Element element : elements) {
			object.setPicSoftware(element.text());
		}
		// 解析上传时间
		elements = document.select(".upload-time");
		for (Element element : elements) {
			object.setPicUploadTime(element.text());
		}
		return object;
	}

	public static SheTuContent_HTML parseSheTuContent(String htmlContent) {
		SheTuContent_HTML object = new SheTuContent_HTML();
		Document document = Jsoup.parse(htmlContent);
		// 解析名字
		Elements elements = document.select(".photo-content");
		object.setMusicName(elements.get(0).child(0).text());
		// 解析id
		elements = document.select(".infor-title");
		for (Element element : elements) {
			switch (element.text()) {
			case "编号":
				object.setMusicId(element.nextElementSibling().text());
				break;
			case "分类":
				object.setMusicType(element.nextElementSibling().text());
				break;
			case "文件体积":
				object.setMusicSize(element.nextElementSibling().text());
				break;
			case "格式":
				object.setMusicFormat(element.nextElementSibling().text());
				break;
			case "音频时长":
				object.setMusicLong(element.nextElementSibling().text());
				break;
			case "推荐软件":
				object.setMusicSoftware(element.nextElementSibling().text());
				break;
			case "分享者":
				object.setMusicUploader(element.nextElementSibling().text());
				break;
			default:
				break;
			}
		}
		return object;
	}

	public static List<String> getBaoTuUrls(String html) {
		Document document = Jsoup.parse(html);
		List<String> urls = new ArrayList<>();
		Elements elements = document.select(".jump-details-seo");
		for (Element element : elements) {
			String url = element.attr("seo-url");
			url = "https://www." + url.substring(2);
			urls.add(url);
		}
		return urls;
	}

	public static List<String> getSheTuUrls(String html) {
		Document document = Jsoup.parse(html);
		List<String> urls = new ArrayList<>();
		Elements elements = document.select(".soundEffect-msgImg");
		for (Element element : elements) {
			String url = element.child(0).attr("href");
			url = "http://www." + url.substring(2);
			urls.add(url);
		}
		return urls;
	}

}
