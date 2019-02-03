package vs.enity.es.ruanjian;

public class RuanjianPojo {
	
	String id;

	String name;
	
	Float size;
	
	Long updateTime;
	
	String category;
	
	String type;
	
	Integer downloadTimes;
	
	Integer rate;

	String language;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getSize() {
		return size;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
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

	public Integer getDownloadTimes() {
		return downloadTimes;
	}

	public void setDownloadTimes(Integer downloadTimes) {
		this.downloadTimes = downloadTimes;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
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
		return "JiSuContent_ES [id=" + id + ", name=" + name + ", size=" + size + ", updateTime=" + updateTime
				+ ", category=" + category + ", type=" + type + ", downloadTimes=" + downloadTimes + ", rate=" + rate
				+ ", language=" + language + "]";
	}

}
