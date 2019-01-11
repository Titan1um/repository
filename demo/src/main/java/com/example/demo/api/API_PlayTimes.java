package com.example.demo.api;

import org.springframework.stereotype.Component;

@Component
public class API_PlayTimes {
	private String urlForPost = "http://api.polyv.net/v2/data/{[[userid]]}/play-times";
	private boolean useDefaultValue = true;
	private String userid_NotInSign = "7ca55a3c6f";
	private String vids = "7ca55a3c6f493ba1f50bd8c672592518_7,7ca55a3c6fe48ad090ecab2a82957495_7";
	private String ptime = String.valueOf(System.currentTimeMillis());

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

	public String getVids() {
		return vids;
	}

	public void setVids(String vids) {
		this.vids = vids;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}
}
