package com.weheros.framework.core.infrastructure.configure;

public class UploadConfigure extends AppConfigure {
	private String videos;
	private String pictures;
	private String csves;
	private String csvesbackup;
	private String adcontents;
	private String baseurl;
	private String videosNew;
	public String getVideosNew() {
		return videosNew;
	}
	public void setVideosNew(String videosNew) {
		this.videosNew = videosNew;
	}
	public String getBaseurl() {
		return baseurl;
	}
	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}
	public String getVideos() {
		return videos;
	}
	public void setVideos(String videos) {
		this.videos = videos;
	}
	public String getPictures() {
		return pictures;
	}
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	public String getCsves() {
		return csves;
	}
	public void setCsves(String csves) {
		this.csves = csves;
	}
	public String getCsvesbackup() {
		return csvesbackup;
	}
	public void setCsvesbackup(String csvesbackup) {
		this.csvesbackup = csvesbackup;
	}
	public String getAdcontents() {
		return adcontents;
	}
	public void setAdcontents(String adcontents) {
		this.adcontents = adcontents;
	}
}
