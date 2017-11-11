package com.sysc3010.m7.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sysc3010.m7.model.PatientForm;

@Controller
public class MainController {


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView mainGet() {
        ModelAndView mav = new ModelAndView("/main");
        mav.addObject("patientList", getAllPatients());
        mav.addObject("patientForm", new PatientForm());
        System.out.println("owen");
        return mav;
    }

    
    private List<String> getAllPatients(){
        
        List<String> pat = new ArrayList<>();
        pat.add("patient1");
        pat.add("patient2");
        pat.add("patient3");
        pat.add("patient4");
        pat.add("patient5");
        pat.add("patient6");
        return pat;
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editSchedule(@ModelAttribute("patientForm") PatientForm patientForm) {
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("patientForm", patientForm);
        return mav;
    }
}
