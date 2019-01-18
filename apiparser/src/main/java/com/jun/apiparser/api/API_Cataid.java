package com.jun.apiparser.api;

import static java.lang.String.valueOf;

public class API_Cataid {
	private String urlForGet = "http://api.polyv.net/v2/cata/size?userid={[[userid]]}&cataid={[[cataid]]}&ptime={[[ptime]]}&sign={[[sign]]}";
	private boolean useDefaultValue = true;
	private String userid = "7ca55a3c6f";
	private String cataid = "1";
	private String ptime = valueOf(System.currentTimeMillis());

	public String getUrlForGet() {
		return urlForGet;
	}

	public void setUrlForGet(String urlForGet) {
		this.urlForGet = urlForGet;
	}

	public boolean isUseDefaultValue() {
		return useDefaultValue;
	}

	public void setUseDefaultValue(boolean useDefaultValue) {
		this.useDefaultValue = useDefaultValue;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCataid() {
		return cataid;
	}

	public void setCataid(String cataid) {
		this.cataid = cataid;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}
}
