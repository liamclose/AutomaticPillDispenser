package com.sysc3010.m7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping(value="main", method=RequestMethod.GET)
	public ModelAndView mainGet() {
		ModelAndView mav = new ModelAndView("main");
		return mav;
	}
}
