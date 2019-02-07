package vs.dao;

import java.util.List;
import java.util.Map;

import vs.enity.es.tupian.TupianPojo;

public interface TupianDao {

	Long getAllCount();

	List<TupianPojo> getList(Integer startRow, Integer pageSize);

	Map<String, Long> getFileSize(Integer[] xAxis);

	Map<String, Long> getPicPixel();

}
