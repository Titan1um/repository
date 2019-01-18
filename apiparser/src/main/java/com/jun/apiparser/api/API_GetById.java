package com.jun.apiparser.api;

import org.springframework.stereotype.Component;

@Component
public class API_GetById {
	private String urlForGet = "http://v.polyv.net/uc/services/rest?method=getById&vid={[[vid]]}&readtoken={[[readtoken]]}";
	private boolean useDefaultValue = true;
	private String vid = "7ca55a3c6fb1f445d9ab845be127b10b_7";
	private String readtoken = "66d670ea-c227-42dd-ac99-dd7dad85d23f";

	private void NoNeedForSign(){}

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

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getReadtoken() {
		return readtoken;
	}

	public void setReadtoken(String readtoken) {
		this.readtoken = readtoken;
	}
}
