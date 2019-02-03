package crawler.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import crawler.entity.JiSuContent_ES;
import crawler.entity.JiSuContent_HTML;

public class MyConverter {

	/**
	 * 将抓取的html实体类转换单位等转换到es实体类
	 * 
	 * @param html_obj
	 * @return
	 */
	public static JiSuContent_ES convertToEsEntity(JiSuContent_HTML html_obj) throws Exception { 
		JiSuContent_ES es_obj = new JiSuContent_ES();
		// 单位转换，转成MB为默认单位
		String size_str = html_obj.getSize();
		String[] size_strs = size_str.split(" ");
		Float size_value = Float.parseFloat(size_strs[0]);
		if ("KB".equals(size_strs[1])) {
			size_value /= 1000;
		} else if ("GB".equals(size_strs[1])) {
			size_value *= 1000;
		}
		es_obj.setSize(size_value);
		// 解析时间转成时间戳
		String updateTime_str = html_obj.getUpdateTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date updateTime = simpleDateFormat.parse(updateTime_str);
		es_obj.setUpdateTime(updateTime.getTime());
		// 转换下载次数
		es_obj.setDownloadTimes(Integer.parseInt(html_obj.getDownloadTimes()));
		// 转化评分数
		es_obj.setRate(Integer.parseInt(html_obj.getRate()));
		// 转化其它信息
		es_obj.setName(html_obj.getName());
		es_obj.setCategory(html_obj.getCategory());
		es_obj.setType(html_obj.getType());
		es_obj.setLanguage(html_obj.getLanguage());
		return es_obj;
	}

}
