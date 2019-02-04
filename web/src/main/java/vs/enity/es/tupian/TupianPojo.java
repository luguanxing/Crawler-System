package vs.enity.es.tupian;

/**
 * 对应crawler项目里的BaoTuContent_ES
 */
public class TupianPojo {

	String picId;

	String picName;

	String picColorMode;

	String picPixel;

	Float picSize;

	String picFormat;

	String picSoftware;

	Long picUploadTime;

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getPicColorMode() {
		return picColorMode;
	}

	public void setPicColorMode(String picColorMode) {
		this.picColorMode = picColorMode;
	}

	public String getPicPixel() {
		return picPixel;
	}

	public void setPicPixel(String picPixel) {
		this.picPixel = picPixel;
	}

	public Float getPicSize() {
		return picSize;
	}

	public void setPicSize(Float picSize) {
		this.picSize = picSize;
	}

	public String getPicFormat() {
		return picFormat;
	}

	public void setPicFormat(String picFormat) {
		this.picFormat = picFormat;
	}

	public String getPicSoftware() {
		return picSoftware;
	}

	public void setPicSoftware(String picSoftware) {
		this.picSoftware = picSoftware;
	}

	public Long getPicUploadTime() {
		return picUploadTime;
	}

	public void setPicUploadTime(Long picUploadTime) {
		this.picUploadTime = picUploadTime;
	}

	@Override
	public String toString() {
		return "BaoTuContent_ES [picId=" + picId + ", picName=" + picName + ", picColorMode=" + picColorMode
				+ ", picPixel=" + picPixel + ", picSize=" + picSize + ", picFormat=" + picFormat + ", picSoftware="
				+ picSoftware + ", picUploadTime=" + picUploadTime + "]";
	}

}
