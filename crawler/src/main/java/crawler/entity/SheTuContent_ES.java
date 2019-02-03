package crawler.entity;

public class SheTuContent_ES {

	String musicName;

	String musicId;

	String musicType;

	Float musicSize;

	String musicFormat;

	Integer musicLong;

	String musicSoftware;

	String musicUploader;

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getMusicId() {
		return musicId;
	}

	public void setMusicId(String musicId) {
		this.musicId = musicId;
	}

	public String getMusicType() {
		return musicType;
	}

	public void setMusicType(String musicType) {
		this.musicType = musicType;
	}

	public Float getMusicSize() {
		return musicSize;
	}

	public void setMusicSize(Float musicSize) {
		this.musicSize = musicSize;
	}

	public String getMusicFormat() {
		return musicFormat;
	}

	public void setMusicFormat(String musicFormat) {
		this.musicFormat = musicFormat;
	}

	public Integer getMusicLong() {
		return musicLong;
	}

	public void setMusicLong(Integer musicLong) {
		this.musicLong = musicLong;
	}

	public String getMusicSoftware() {
		return musicSoftware;
	}

	public void setMusicSoftware(String musicSoftware) {
		this.musicSoftware = musicSoftware;
	}

	public String getMusicUploader() {
		return musicUploader;
	}

	public void setMusicUploader(String musicUploader) {
		this.musicUploader = musicUploader;
	}

	@Override
	public String toString() {
		return "SheTuContent_ES [musicName=" + musicName + ", musicId=" + musicId + ", musicType=" + musicType
				+ ", musicSize=" + musicSize + ", musicFormat=" + musicFormat + ", musicLong=" + musicLong
				+ ", musicSoftware=" + musicSoftware + ", musicUploader=" + musicUploader + "]";
	}

}
