package com.wangxr.websocket.controller;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author wangxueren
 * @Date 2016年1月5日 上午9:37:38
 * @email wangxr_it@sina.com
 *
 */
@Controller
@RequestMapping("/chat")
public class ChatController {
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView("chat");
	}

}
