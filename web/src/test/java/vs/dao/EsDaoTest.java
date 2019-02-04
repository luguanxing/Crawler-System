package vs.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import vs.dao.RuanjianDao;
import vs.enity.es.ruanjian.RuanjianPojo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsDaoTest {

	@Autowired
	private RuanjianDao esDao;

	@Test
	public void testGetList() {
		List<RuanjianPojo> list = esDao.getList(10185, 1);
		System.out.println(list);
	}

	@Test
	public void testGetAllCount() {
		Long count = esDao.getAllCount();
		System.out.println(count);
	}

	@Test
	public void testRateAgg() {
		Map<String, Long> result = esDao.getRate();
		System.out.println(result);
	}

	@Test
	public void testUpdateTimeAgg() {
		Map<String, Long> result = esDao.getUpdateTime();
		System.out.println(result);
	}

	@Test
	public void testFileSizeAgg() {
		Integer[] xAxis = { 1, 10, 50, 100, 200, 500, 1000 };
		Map<String, Long> result = esDao.getFileSize(xAxis);
		System.out.println(result);
	}
	
	@Test
	public void testRateAvgDownloadTimes() {
		Map<String, Double> result = esDao.getRateAvgDownloadTimes();
		System.out.println(result);
	}

}
