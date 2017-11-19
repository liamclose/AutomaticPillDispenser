package com.sysc3010.m7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sysc3010.m7.model.PatientSearchForm;
import com.sysc3010.m7.service.ScheduleService;

@Controller
public class EditScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView getEdit() {
        ModelAndView mav = new ModelAndView("edit");
        return mav;
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView postEdit(@ModelAttribute("patientForm") PatientSearchForm patientForm) {
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("patientForm", patientForm);
        return mav;
    }
    
 
}
