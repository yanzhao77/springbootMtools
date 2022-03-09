package com.yz.securitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class errorController {
	

	@RequestMapping("/toError")
	public ModelAndView name() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg","服务器开小差了");
		mv.addObject("code","403");
		mv.addObject("text","You don't have permission to access the URL on this server.");
		mv.setViewName("BaseError");
		return mv;
	}

}
