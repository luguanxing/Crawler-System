package vs.service;

import java.util.List;

import vs.enity.es.yinpin.YinpinPojo;

public interface YinpinService {

	Long getAllCount();

	List<YinpinPojo> getYinpinList(Integer startRow, Integer pageSize);

}