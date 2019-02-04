package vs.enity.es;

import java.util.List;

public class LineData {

	List<String> xAxisNames;
	
	List<Long> seriesDatas;

	public List<String> getxAxisNames() {
		return xAxisNames;
	}

	public void setxAxisNames(List<String> xAxisNames) {
		this.xAxisNames = xAxisNames;
	}

	public List<Long> getSeriesDatas() {
		return seriesDatas;
	}

	public void setSeriesDatas(List<Long> seriesDatas) {
		this.seriesDatas = seriesDatas;
	}

}
