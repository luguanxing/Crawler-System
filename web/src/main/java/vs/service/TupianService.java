package vs.service;

import java.util.List;

import vs.enity.es.LineData;
import vs.enity.es.tupian.TupianPojo;

public interface TupianService {

	Long getAllCount();

	List<TupianPojo> getTupianList(Integer startRow, Integer pageSize);

	LineData getFileSize();

}
