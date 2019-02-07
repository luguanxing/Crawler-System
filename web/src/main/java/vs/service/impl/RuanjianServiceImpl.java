package vs.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.dao.RuanjianDao;
import vs.enity.es.CakeData;
import vs.enity.es.LineData;
import vs.enity.es.LineDataDouble;
import vs.enity.es.ruanjian.RuanjianPojo;
import vs.service.RuanjianService;

@Service
public class RuanjianServiceImpl implements RuanjianService {

	@Autowired
	private RuanjianDao esDao;
	
	@Override
	public List<RuanjianPojo> getList(Integer startRow, Integer pageSize) {
		return esDao.getList(startRow, pageSize);
	}

	@Override
	public Long getAllCount() {
		return esDao.getAllCount();
	}

	@Override
	public List<CakeData> getRateJson() {
		Map<String, Long> rateMap = esDao.getRate();
		List<CakeData> result = new ArrayList<>();
		for (String name : rateMap.keySet()) {
			CakeData cakeData = new CakeData();
			cakeData.setName(name);
			cakeData.setValue(rateMap.get(name));
			result.add(cakeData);
		}
		return result;
	}

	@Override
	public LineData getUpdateTime() {
		Map<String, Long> updateTimeMap = esDao.getUpdateTime();
		LineData result = new LineData();
		List<String> xAxisNames = new ArrayList<>();
		List<Long> seriesDatas = new ArrayList<>();
		for (String yearTimeStamp : updateTimeMap.keySet()) {
			// 格式化日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String dateTimeStr = sdf.format(new Date(Long.parseLong(yearTimeStamp)));
			xAxisNames.add(dateTimeStr);
			seriesDatas.add(updateTimeMap.get(yearTimeStamp));
		}
		result.setxAxisNames(xAxisNames);
		result.setSeriesDatas(seriesDatas);
		return result;
	}

	
	@Override
	public LineData getFileSize() {
		// 下标边界数组
		Integer[] xAxis = { 1, 10, 20, 30, 50, 100, 200, 300, 500, 1000 };
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
	public LineDataDouble getRateAvgDownloadTimes() {
		LineDataDouble result = new LineDataDouble();
		Map<String, Double> rateAvgDownloadTimesMap = esDao.getRateAvgDownloadTimes();
		List<String> xAxisNames = new ArrayList<>();
		List<Double> seriesDatas = new ArrayList<>();
		for (String xAxisName : rateAvgDownloadTimesMap.keySet()) {
			xAxisNames.add(xAxisName);
			Double rateAvgDownloadTimes = rateAvgDownloadTimesMap.get(xAxisName);
			// 保留两位小数
			rateAvgDownloadTimes = Math.round(rateAvgDownloadTimes * 100) * 1.0 / 100;
			seriesDatas.add(rateAvgDownloadTimes);
		}
		result.setxAxisNames(xAxisNames);
		result.setSeriesDatas(seriesDatas);
		return result;
	}

	@Override
	public List<CakeData> getCategory() {
		Map<String, Long> rateMap = esDao.getCategory();
		List<CakeData> result = new ArrayList<>();
		for (String name : rateMap.keySet()) {
			CakeData cakeData = new CakeData();
			cakeData.setName(name);
			cakeData.setValue(rateMap.get(name));
			result.add(cakeData);
		}
		return result;
	}

	@Override
	public List<CakeData> getType() {
		Map<String, Long> rateMap = esDao.getType();
		List<CakeData> result = new ArrayList<>();
		for (String name : rateMap.keySet()) {
			CakeData cakeData = new CakeData();
			cakeData.setName(name);
			cakeData.setValue(rateMap.get(name));
			result.add(cakeData);
		}
		return result;
	}
	
}
