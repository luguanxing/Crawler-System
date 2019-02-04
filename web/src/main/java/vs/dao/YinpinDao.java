package vs.dao;

import java.util.List;

import vs.enity.es.yinpin.YinpinPojo;

public interface YinpinDao {

	Long getAllCount();

	List<YinpinPojo> getList(Integer startRow, Integer pageSize);

}
