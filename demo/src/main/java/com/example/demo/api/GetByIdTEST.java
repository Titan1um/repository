package com.example.demo.api;

import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @Description: API类 的 测试类
 * 除了urlForGet/urlForPost,useDefaultValue之外的成员默认为参数 参数记得生成getter setter
 * 除了NoNeedForSign外所有方法默认为参数的计算方式
 * post写类更便捷,get请求更稳定
 * @Author: LJH
 */
@Component
public class GetByIdTEST {
	/**
	 * @Description: POST / GET
	 * Get的访问url ,需要parser替换的用[[param name]]包围
	 * 注意param name 要与变量名一致
	 */
	private String urlForGet = "http://v.polyv.net/uc/services/rest?method=getById&vid={[[vid]]}&readtoken={[[readtoken]]}";

	/**
	* @Description:  post url
	*/
//	private String urlForPost = "http://v.polyv.net/uc/services/rest?method=getById";

	/**
	 * @Description: POST / GET
	 * Post的访问url,需要parser把参数set到entity中
	 */
//	private String urlForPost = "http://v.polyv.net/uc/services/rest?method=getById";
	/**
	 * @Description:(仅考虑做) 若参数需要特殊计算, 则创建相同的方法名
	 */
	private String readtoken = "66d670ea-c227-42dd-ac99-dd7dad85d23f";
	private String vid = "7ca55a3c6fb1f445d9ab845be127b10b_7";
	/**
	* @Description:  存在则使用默认值
	*/
	private boolean useDefaultValue = true;

	/**
	* @Description:  若参数需要特殊计算, 则创建相同的方法名
	* @Param: [NVP] 必须接收一个NVP(已知参数的值) 必须返回计算后的值(更新到APIParser中的NVP里)
	* @TODO: 里面仅允许对NVP4SK的参数进行操作,禁止对默认参数进行操作
	* @return: void
	* @TODO: just for test delete plz
	* @Author: LJH
	*/
	private String vid(Map<String, String> NVP4SK){
//		vid = "demo" + vid + "demo";
		String vid = NVP4SK.get("vid");
		return vid;
	}

	/**
	 * @Description: 告诉Parser需要替换[[param name]]
	 */
//	private void reqParamForGet() {}    用urlForGet即可完成此需求

	/**
	 * @Description: 对应NoNeedForSign, 若有此方法存在, 则说明Sign方法需要重载, 若需要Sign, 注释掉两个方法
	 */
	//private void Sign(String secretkey){}

	/**
	 * @Description: NoNeedForSign, 当此方法存在时, 说明不需要Sign, 则由Parse的默认方法来计算Sign, 若需要Sign, 注释掉两个方法
	 * @TODO: 此方法不需要sign, 作测试, 还是注释掉此函数
	 */
	private void NoNeedForSign() {}


	/**
	 * @Description: 以下均为setter getter
	 */

	public String getUrlForGet() { return urlForGet; }

	public void setUrlForGet(String urlForGet) { this.urlForGet = urlForGet; }

	public String getReadtoken() {
		return readtoken;
	}

	public void setReadtoken(String readtoken) {
		this.readtoken = readtoken;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public boolean isUseDefaultValue() {
		return useDefaultValue;
	}

	public void setUseDefaultValue(boolean useDefaultValue) {
		this.useDefaultValue = useDefaultValue;
	}
}
