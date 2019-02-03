package crawler.entity;

public class JiSuContent_ES {
	
	String softId;

	String softName;
	
	Float softSize;
	
	Long softUpdateTime;
	
	String softCategory;
	
	String softType;
	
	Integer softDownloadTimes;
	
	Integer softRate;

	String softLanguage;

	@Override
	public String toString() {
		return "JiSuContent_ES [softId=" + softId + ", softName=" + softName + ", softSize=" + softSize
				+ ", softUpdateTime=" + softUpdateTime + ", softCategory=" + softCategory + ", softType=" + softType
				+ ", softDownloadTimes=" + softDownloadTimes + ", softRate=" + softRate + ", softLanguage="
				+ softLanguage + "]";
	}

	public String getSoftId() {
		return softId;
	}

	public void setSoftId(String softId) {
		this.softId = softId;
	}

	public String getSoftName() {
		return softName;
	}

	public void setSoftName(String softName) {
		this.softName = softName;
	}

	public Float getSoftSize() {
		return softSize;
	}

	public void setSoftSize(Float softSize) {
		this.softSize = softSize;
	}

	public Long getSoftUpdateTime() {
		return softUpdateTime;
	}

	public void setSoftUpdateTime(Long softUpdateTime) {
		this.softUpdateTime = softUpdateTime;
	}

	public String getSoftCategory() {
		return softCategory;
	}

	public void setSoftCategory(String softCategory) {
		this.softCategory = softCategory;
	}

	public String getSoftType() {
		return softType;
	}

	public void setSoftType(String softType) {
		this.softType = softType;
	}

	public Integer getSoftDownloadTimes() {
		return softDownloadTimes;
	}

	public void setSoftDownloadTimes(Integer softDownloadTimes) {
		this.softDownloadTimes = softDownloadTimes;
	}

	public Integer getSoftRate() {
		return softRate;
	}

	public void setSoftRate(Integer softRate) {
		this.softRate = softRate;
	}

	public String getSoftLanguage() {
		return softLanguage;
	}

	public void setSoftLanguage(String softLanguage) {
		this.softLanguage = softLanguage;
	}

}
