package com.example.demo.api;

import static java.lang.String.valueOf;

public class API_GetVideoMsg {

	private String urlForPost = "http://api.polyv.net/v2/video/{[[userid]]}/get-video-msg";
	private boolean useDefaultValue = true;
	private String userid_NotInSign = "7ca55a3c6f";
	private String vid = "7ca55a3c6fabae30e22dbeecdaa5fafe_7";
	private String ptime = valueOf(System.currentTimeMillis());
	private String format = "xml";

	public String getUrlForPost() {
		return urlForPost;
	}

	public void setUrlForPost(String urlForPost) {
		this.urlForPost = urlForPost;
	}

	public boolean isUseDefaultValue() {
		return useDefaultValue;
	}

	public void setUseDefaultValue(boolean useDefaultValue) {
		this.useDefaultValue = useDefaultValue;
	}

	public String getUserid_NotInSign() {
		return userid_NotInSign;
	}

	public void setUserid_NotInSign(String userid_NotInSign) {
		this.userid_NotInSign = userid_NotInSign;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
