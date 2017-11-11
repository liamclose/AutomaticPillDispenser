package com.sysc3010.m7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sysc3010.m7.model.PatientForm;

@Controller
public class MainController {

	@RequestMapping(value = "main", method = RequestMethod.GET)
	public ModelAndView mainGet() {
		ModelAndView mav = new ModelAndView("main");
		mav.addObject("patientForm", new PatientForm());
		return mav;
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public ModelAndView editSchedule(@ModelAttribute("patientForm") PatientForm patientForm) {
		ModelAndView mav = new ModelAndView("edit");
		mav.addObject("patientForm", patientForm);
		return mav;
	}
}
