package vs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	// 首页目录
	@RequestMapping("/{pageUrl}.html")
	public String pageUrl(@PathVariable(name="pageUrl") String pageUrl) {
		return pageUrl;
	}

	// 二级目录
	@RequestMapping("/{type}/{pageUrl}.html")
	public String secondPageUrl(@PathVariable(name="type") String type, @PathVariable(name="pageUrl") String pageUrl) {
		return type + "/" + pageUrl;
	}

}
