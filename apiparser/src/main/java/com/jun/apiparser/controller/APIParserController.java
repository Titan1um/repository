package com.jun.apiparser.controller;

import com.jun.apiparser.parser.PropertiesCreator;
import com.jun.apiparser.parser.PropertyParser;
import com.jun.apiparser.view.Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class APIParserController {
	/**
	 * TODO:
	 * 1.前端页面:
	 * 1)可以根据传来的model/数据动态显示
	 * 2)跳转不同api控制页于同一页面,基于1)
	 * 3)传递要创建的文件
	 * 4)点执行后传出数据
	 * 5)确认 每个 参数是否要算进sign
	 * 2.后端处理:
	 * 1)执行前接受数据写入properties保存
	 * 2)创建时解析地址,根据post/get填充url
	 * 3)指向APIParser的某几个方法
	 * 4)返回的数据直接是页面得了
	 * <p>
	 * 因为不会前端，所以要做一个导航页(读取所有properties名字)，显示所有已知api，点击跳转生成一个对应的api页面
	 * <p>
	 * 创建api页面现在怎么实现。。参数个数变化的(写死n个，非空则有)
	 * /exec页最好有能找到一个查找所有元素的方法
	 * <p>
	 * 自建连接连到别人的网页
	 */
	@Autowired
	PropertiesCreator propertiesCreator;
	@Autowired
	Navigator navigator;


	@GetMapping("/hi")
	public String hi() {
		return "hi";
	}

	@GetMapping("/api")
	@ResponseBody
	public String navigator() {
		return navigator.getPage();
	}

	@GetMapping("/api/{api}")
	@ResponseBody
	public String navigator(@PathVariable("api") String api) {
		System.out.println(api);
		return navigator.getPage(api);
	}

	@RequestMapping(value="/api/{api}/exec",method= RequestMethod.POST)
	@ResponseBody
	public String navigatorExec(@PathVariable("api") String api,HttpServletRequest req) {
		return navigator.getApiExecRes(api,req);
	}

	@GetMapping("/create")
	public String createNew() {
		return "hi";
	}

	@RequestMapping(value = "/create/exec")
	@ResponseBody
	public String createExec(HttpServletRequest req) {
		if(propertiesCreator.createAPI(req)){
			return  "<a href=\"/api\">首页</a><br>"+"success!";
		}
		return "<a href=\"/api\">首页</a><br>"+"failed to create api.";
	}

	@GetMapping("/api/{api}/dele")
	public String dele(@PathVariable("api") String api){

		return  "<a href=\"/api\">首页</a><br>"+propertiesCreator.deleteAPI(api);
	}

}
