package vs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.dao.TupianDao;
import vs.enity.es.CakeData;
import vs.enity.es.LineData;
import vs.enity.es.tupian.TupianPojo;
import vs.service.TupianService;

@Service
public class TupianServiceImpl implements TupianService {

	@Autowired
	private TupianDao esDao;

	@Override
	public Long getAllCount() {
		return esDao.getAllCount();
	}

	@Override
	public List<TupianPojo> getTupianList(Integer startRow, Integer pageSize) {
		return esDao.getList(startRow, pageSize);
	}

	@Override
	public LineData getFileSize() {
		// 下标边界数组
		Integer[] xAxis = { 1, 10, 20, 30, 50, 80, 100, 150, 200 };
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
	public List<CakeData> getPicPixel() {
		Map<String, Long> sizeMap = esDao.getPicPixel();
		List<CakeData> result = new ArrayList<>();
		for (String name : sizeMap.keySet()) {
			CakeData cakeData = new CakeData();
			cakeData.setName(name);
			cakeData.setValue(sizeMap.get(name));
			result.add(cakeData);
		}
		return result;
	}

}
