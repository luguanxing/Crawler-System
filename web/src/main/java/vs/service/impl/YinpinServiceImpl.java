package vs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.dao.YinpinDao;
import vs.enity.es.yinpin.YinpinPojo;
import vs.service.YinpinService;

@Service
public class YinpinServiceImpl implements YinpinService {

	@Autowired
	private YinpinDao esDao;

	@Override
	public Long getAllCount() {
		return esDao.getAllCount();
	}

	@Override
	public List<YinpinPojo> getYinpinList(Integer startRow, Integer pageSize) {
		return esDao.getList(startRow, pageSize);
	}

}
