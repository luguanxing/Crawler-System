package vs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.dao.TupianDao;
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

}
