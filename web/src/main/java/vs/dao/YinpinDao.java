package vs.dao;

import java.util.List;
import java.util.Map;

import vs.enity.es.yinpin.YinpinPojo;

public interface YinpinDao {

	Long getAllCount();

	List<YinpinPojo> getList(Integer startRow, Integer pageSize);

	Map<String, Long> getFileSize(Integer[] xAxis);

}
