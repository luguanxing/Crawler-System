package vs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import vs.enity.TableSplitResult;
import vs.enity.es.LineData;
import vs.enity.es.tupian.TupianPojo;
import vs.service.TupianService;

@Controller
@RequestMapping("/data/tupian")
public class TupianDataController {

	@Autowired
	private TupianService esService;

	public Integer MAX_WINDOW = 10000; // ES最大分页深度，不能再往后翻页

	@RequestMapping("/list")
	@ResponseBody
	public TableSplitResult<List<TupianPojo>> getList(@RequestParam Integer startRow, @RequestParam Integer pageSize) {
		int totalCount = esService.getAllCount().intValue();
		if (startRow + pageSize > MAX_WINDOW) { // 限制分页深度
			startRow = MAX_WINDOW - pageSize;
		}
		List<TupianPojo> list = esService.getTupianList(startRow, pageSize);
		return new TableSplitResult<List<TupianPojo>>(0, totalCount, list);
	}

	@RequestMapping("/fileSize")
	@ResponseBody
	public String getFileSize() {
		LineData fileSizeData = esService.getFileSize();
		return new Gson().toJson(fileSizeData);
	}

}
