package vs.dao;

import java.util.List;
import java.util.Map;

import vs.enity.es.ruanjian.RuanjianPojo;

public interface RuanjianDao {

	List<RuanjianPojo> getList(Integer startRow, Integer pageSize);
	
	Long getAllCount();
	
	Map<String, Long> getRate();
	
	Map<String, Long> getUpdateTime();

	Map<String, Long>  getFileSize(Integer[] xAxis);
	
	Map<String, Double> getRateAvgDownloadTimes();
	
}
