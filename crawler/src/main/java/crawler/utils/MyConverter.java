package crawler.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import crawler.entity.BaoTuContent_ES;
import crawler.entity.BaoTuContent_HTML;
import crawler.entity.JiSuContent_ES;
import crawler.entity.JiSuContent_HTML;
import crawler.entity.SheTuContent_ES;
import crawler.entity.SheTuContent_HTML;

/**
 * 将抓取的html实体类转换单位等转换到es实体类
 */
public class MyConverter {

	public static JiSuContent_ES convertToJiSuEsEntity(JiSuContent_HTML html_obj) throws Exception { 
		JiSuContent_ES es_obj = new JiSuContent_ES();
		// 单位转换，转成MB为默认单位
		String size_str = html_obj.getSoftSize();
		String[] size_strs = size_str.split(" ");
		Float size_value = Float.parseFloat(size_strs[0]);
		if ("KB".equals(size_strs[1])) {
			size_value /= 1000;
		} else if ("GB".equals(size_strs[1])) {
			size_value *= 1000;
		}
		es_obj.setSoftSize(size_value);
		// 解析时间转成时间戳
		String updateTime_str = html_obj.getSoftUpdateTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date updateTime = simpleDateFormat.parse(updateTime_str);
		es_obj.setSoftUpdateTime(updateTime.getTime());
		// 转换下载次数
		es_obj.setSoftDownloadTimes(Integer.parseInt(html_obj.getSoftDownloadTimes()));
		// 转化评分数
		es_obj.setSoftRate(Integer.parseInt(html_obj.getSoftRate()));
		// 转化其它信息
		es_obj.setSoftId(html_obj.getSoftId());
		es_obj.setSoftName(html_obj.getSoftName());
		es_obj.setSoftCategory(html_obj.getSoftCategory());
		es_obj.setSoftType(html_obj.getSoftType());
		es_obj.setSoftLanguage(html_obj.getSoftLanguage());
		return es_obj;
	}

	public static BaoTuContent_ES convertToBaoTuEsEntity(BaoTuContent_HTML html_obj) throws Exception {
		BaoTuContent_ES es_obj = new BaoTuContent_ES();
		es_obj.setPicId(html_obj.getPicId());
		es_obj.setPicName(html_obj.getPicName());
		es_obj.setPicColorMode(html_obj.getPicColorMode());
		es_obj.setPicPixel(html_obj.getPicPixel());
		// 单位只有M
		String picSize = html_obj.getPicSize().split("M")[0];
		es_obj.setPicSize(Float.parseFloat(picSize));
		es_obj.setPicFormat(html_obj.getPicFormat());
		es_obj.setPicSoftware(html_obj.getPicFormat());
		// 解析时间转成时间戳
		String updateTime_str = html_obj.getPicUploadTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date updateTime = simpleDateFormat.parse(updateTime_str);
		es_obj.setPicUploadTime(updateTime.getTime());
		return es_obj;
	}

	public static SheTuContent_ES convertToSheTuEsEntity(SheTuContent_HTML html_obj) {
		SheTuContent_ES es_obj = new SheTuContent_ES();
		es_obj.setMusicName(html_obj.getMusicName());
		es_obj.setMusicId(html_obj.getMusicId());
		es_obj.setMusicType(html_obj.getMusicType());
		// 单位转换，转成MB为默认单位
		String size_str = html_obj.getMusicSize();
		String[] size_strs = size_str.split(" ");
		Float size_value = Float.parseFloat(size_strs[0]);
		if ("KB".equals(size_strs[1])) {
			size_value /= 1000;
		} else if ("GB".equals(size_strs[1])) {
			size_value *= 1000;
		}
		es_obj.setMusicSize(size_value);
		es_obj.setMusicFormat(html_obj.getMusicFormat());
		// 单位转换，化成秒单位
		String musicLong = html_obj.getMusicLong();
		String[] mmss = musicLong.split(":");
		Integer seconds = 0;
		if (mmss.length == 2) {
			seconds += Integer.parseInt(mmss[0]) * 60;
			seconds += Integer.parseInt(mmss[1]);
		}
		es_obj.setMusicLong(seconds);
		es_obj.setMusicSoftware(html_obj.getMusicSoftware());
		es_obj.setMusicUploader(html_obj.getMusicUploader());
		return es_obj;
	}
	
}
