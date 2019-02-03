package crawler.entity;

public class BaoTuContent_HTML {

	String picId;

	String picName;

	String picColorMode;

	String picPixel;

	String picSize;

	String picFormat;

	String picSoftware;

	String picUploadTime;

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

	public String getPicSize() {
		return picSize;
	}

	public void setPicSize(String picSize) {
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

	public String getPicUploadTime() {
		return picUploadTime;
	}

	public void setPicUploadTime(String picUploadTime) {
		this.picUploadTime = picUploadTime;
	}

	@Override
	public String toString() {
		return "BaoTuContent_HTML [picId=" + picId + ", picName=" + picName + ", picColorMode=" + picColorMode
				+ ", picPixel=" + picPixel + ", picSize=" + picSize + ", picFormat=" + picFormat + ", picSoftware="
				+ picSoftware + ", picUploadTime=" + picUploadTime + "]";
	}

}
