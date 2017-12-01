package com.sysc3010.m7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sysc3010.m7.model.PatientSearchForm;
import com.sysc3010.m7.model.RequestMappings;
import com.sysc3010.m7.service.DispenseService;

@Controller
public class MainController {

    @Autowired
    private DispenseService scheduleService;

    @RequestMapping(value = RequestMappings.MAIN_URL, method = RequestMethod.GET)
    public ModelAndView mainGet() {
        ModelAndView mav = new ModelAndView(RequestMappings.MAIN);
        mav.addObject("patientList", scheduleService.getAllPatients());
        mav.addObject("patientSearchForm", new PatientSearchForm());
        return mav;
    }
}
