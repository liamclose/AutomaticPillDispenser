package com.sysc3010.m7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PatientController {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchForPatient() {
        ModelAndView mav = new ModelAndView("patient");

        return mav;
    }
}
