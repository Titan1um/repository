package com.jun.apiparser.api;

import org.springframework.stereotype.Component;

import java.util.Map;

import static java.lang.String.valueOf;

/**
* @Author: LJH
*/
@Component
public class API_GetVideoList {
	private String urlForPost = "http://api.polyv.net/v2/video/{userid}/get-new-list";
	private boolean useDefaultValue = true;
	private String catatree = "1";
	private String userid_NotInSign = "7ca55a3c6f";
	private String ptime = valueOf(System.currentTimeMillis());
	private String pageNum = "1";
	private String numPerPage = "10000";
	private String startDate = "2018-11-14";
	private String endDate = "2018-11-27";
	private String format = "json";

	/**
	* @Description:  演示作用:ptime既可以在声明处直接函数赋值,也可以用方法
	* @Param: [NVP]
	* @return: java.lang.String
	*/
	private String ptime(Map<String, String> NVP) {
		return String.valueOf(System.currentTimeMillis());
	}




	//All setter getter
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

	public String getCatatree() {
		return catatree;
	}

	public void setCatatree(String catatree) {
		this.catatree = catatree;
	}

	public String getUserid_NotInSign() {
		return userid_NotInSign;
	}

	public void setUserid_NotInSign(String userid_NotInSign) {
		this.userid_NotInSign = userid_NotInSign;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(String numPerPage) {
		this.numPerPage = numPerPage;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
