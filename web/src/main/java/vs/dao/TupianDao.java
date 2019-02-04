package vs.dao;

import java.util.List;

import vs.enity.es.tupian.TupianPojo;

public interface TupianDao {

	Long getAllCount();

	List<TupianPojo> getList(Integer startRow, Integer pageSize);

}
