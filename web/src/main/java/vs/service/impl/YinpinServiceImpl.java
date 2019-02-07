package vs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.dao.YinpinDao;
import vs.enity.es.LineData;
import vs.enity.es.yinpin.YinpinPojo;
import vs.service.YinpinService;

@Service
public class YinpinServiceImpl implements YinpinService {

	@Autowired
	private YinpinDao esDao;

	@Override
	public Long getAllCount() {
		return esDao.getAllCount();
	}

	@Override
	public List<YinpinPojo> getYinpinList(Integer startRow, Integer pageSize) {
		return esDao.getList(startRow, pageSize);
	}

	@Override
	public LineData getFileSize() {
		// 下标边界数组
		Integer[] xAxis = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18, 20 };
		LineData result = new LineData();
		List<String> xAxisNames = new ArrayList<>();
		List<Long> seriesDatas = new ArrayList<>();
		Map<String, Long> fileSizeMap = esDao.getFileSize(xAxis);
		for (String xAxisName : fileSizeMap.keySet()) {
			xAxisNames.add(xAxisName);
			seriesDatas.add(fileSizeMap.get(xAxisName));
		}
		result.setxAxisNames(xAxisNames);
		result.setSeriesDatas(seriesDatas);
		return result;
	}

	@Override
	public LineData getMusicLong() {
		// 下标边界数组
		Integer[] xAxis = { 20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300 };
		LineData result = new LineData();
		List<String> xAxisNames = new ArrayList<>();
		List<Long> seriesDatas = new ArrayList<>();
		Map<String, Long> fileSizeMap = esDao.getMusicLong(xAxis);
		for (String xAxisName : fileSizeMap.keySet()) {
			xAxisNames.add(xAxisName);
			seriesDatas.add(fileSizeMap.get(xAxisName));
		}
		result.setxAxisNames(xAxisNames);
		result.setSeriesDatas(seriesDatas);
		return result;
	}

}
