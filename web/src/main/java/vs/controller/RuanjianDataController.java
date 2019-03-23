package vs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import vs.enity.TableSplitResult;
import vs.enity.es.CakeData;
import vs.enity.es.LineData;
import vs.enity.es.LineDataDouble;
import vs.enity.es.ruanjian.RuanjianPojo;
import vs.service.RuanjianService;

@Controller
@RequestMapping("/data/ruanjian")
public class RuanjianDataController {

	@Autowired
	private RuanjianService esService;

	public Integer MAX_WINDOW = 10000; // ES最大分页深度，不能再往后翻页

	@RequestMapping("/list")
	@ResponseBody
	public TableSplitResult<List<RuanjianPojo>> getList(@RequestParam Integer startRow,
			@RequestParam Integer pageSize) {
		int totalCount = esService.getAllCount().intValue();
		if (startRow + pageSize > MAX_WINDOW) { // 限制分页深度
			startRow = MAX_WINDOW - pageSize;
		}
		List<RuanjianPojo> list = esService.getList(startRow, pageSize);
		return new TableSplitResult<List<RuanjianPojo>>(0, totalCount, list);
	}

	@RequestMapping("/rate")
	@ResponseBody
	public String getRate() {
		List<CakeData> rateJson = esService.getRateJson();
		return new Gson().toJson(rateJson);
	}

	@RequestMapping("/updateTime")
	@ResponseBody
	public String getUpdateTime() {
		LineData updateTimeLineData = esService.getUpdateTime();
		return new Gson().toJson(updateTimeLineData);
	}

	@RequestMapping("/fileSize")
	@ResponseBody
	public String getFileSize() {
		LineData fileSizeData = esService.getFileSize();
		return new Gson().toJson(fileSizeData);
	}

	@RequestMapping("/rateAvgDownloadTimes")
	@ResponseBody
	public String getRateAvgDownloadTimes() {
		LineDataDouble rateAvgDownloadTimesData = esService.getRateAvgDownloadTimes();
		return new Gson().toJson(rateAvgDownloadTimesData);
	}

	@RequestMapping("/category")
	@ResponseBody
	public String getCategory() {
		List<CakeData> categoryJson = esService.getCategory();
		return new Gson().toJson(categoryJson);
	}

	@RequestMapping("/type")
	@ResponseBody
	public String getType() {
		List<CakeData> typeJson = esService.getType();
		return new Gson().toJson(typeJson);
	}

	@RequestMapping("/query")
	@ResponseBody
	public String query(@RequestParam String aggType, @RequestParam Integer aggSize, @RequestParam String aggSizesArray) {
		LineData fileSizeData = null; 
		if (aggSize == -1) {
			// 自定义聚合
			fileSizeData = esService.getFileSize(aggType, aggSizesArray);
		} else {
			// 固定指标聚合
			fileSizeData = esService.getFileSize(aggType, aggSize);
		}
		return new Gson().toJson(fileSizeData);
	}
}
