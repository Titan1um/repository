package com.example.demo.api;

public class API_Upload {
	private String urlForPost = "http://v.polyv.net/uc/services/rest?method=uploadfile";
	private boolean useDefaultValue = true;
	private String writetoken = "9b281950-15c8-48a6-89fe-0b91d836bee7";
	private String JSONRPC = "{\"title\": \"123321123321\", \"tag\":\"标签\",\"desc\":\"描述\"}";
	private String Filedata = ".mp4";

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

	public String getWritetoken() {
		return writetoken;
	}

	public void setWritetoken(String writetoken) {
		this.writetoken = writetoken;
	}

	public String getJSONRPC() {
		return JSONRPC;
	}

	public void setJSONRPC(String JSONRPC) {
		this.JSONRPC = JSONRPC;
	}

	public String getFiledata() {
		return Filedata;
	}

	public void setFiledata(String filedata) {
		Filedata = filedata;
	}
}
