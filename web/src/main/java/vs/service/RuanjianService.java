package vs.service;

import java.util.List;

import vs.enity.es.CakeData;
import vs.enity.es.LineData;
import vs.enity.es.LineDataDouble;
import vs.enity.es.ruanjian.RuanjianPojo;

public interface RuanjianService {

	List<RuanjianPojo> getList(Integer startRow, Integer pageSize);
	
	Long getAllCount();
	
	List<CakeData> getRateJson();
	
	LineData getUpdateTime();
	
	LineData getFileSize();

	LineDataDouble getRateAvgDownloadTimes();

	List<CakeData> getCategory();

	List<CakeData> getType();
	
}
