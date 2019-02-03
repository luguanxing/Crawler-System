package crawler.entity;

public class JiSuContent_HTML {

	String softId;
	
	String softName;
	
	String softSize;
	
	String softUpdateTime;
	
	String softCategory;
	
	String softType;
	
	String softDownloadTimes;
	
	String softRate;

	String softLanguage;

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

	public String getSoftSize() {
		return softSize;
	}

	public void setSoftSize(String softSize) {
		this.softSize = softSize;
	}

	public String getSoftUpdateTime() {
		return softUpdateTime;
	}

	public void setSoftUpdateTime(String softUpdateTime) {
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

	public String getSoftDownloadTimes() {
		return softDownloadTimes;
	}

	public void setSoftDownloadTimes(String softDownloadTimes) {
		this.softDownloadTimes = softDownloadTimes;
	}

	public String getSoftRate() {
		return softRate;
	}

	public void setSoftRate(String softRate) {
		this.softRate = softRate;
	}

	public String getSoftLanguage() {
		return softLanguage;
	}

	public void setSoftLanguage(String softLanguage) {
		this.softLanguage = softLanguage;
	}

	@Override
	public String toString() {
		return "JiSuContent_HTML [softId=" + softId + ", softName=" + softName + ", softSize=" + softSize
				+ ", softUpdateTime=" + softUpdateTime + ", softCategory=" + softCategory + ", softType=" + softType
				+ ", softDownloadTimes=" + softDownloadTimes + ", softRate=" + softRate + ", softLanguage="
				+ softLanguage + "]";
	}

}
