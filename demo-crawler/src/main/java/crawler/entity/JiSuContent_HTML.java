package crawler.entity;

public class JiSuContent_HTML {

	String name;
	
	String size;
	
	String updateTime;
	
	String category;
	
	String type;
	
	String downloadTimes;
	
	String rate;

	String language;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDownloadTimes() {
		return downloadTimes;
	}

	public void setDownloadTimes(String downloadTimes) {
		this.downloadTimes = downloadTimes;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "JiSuContent_HTML [name=" + name + ", size=" + size + ", updateTime=" + updateTime + ", category="
				+ category + ", type=" + type + ", downloadTimes=" + downloadTimes + ", rate=" + rate + ", language="
				+ language + "]";
	}

}
