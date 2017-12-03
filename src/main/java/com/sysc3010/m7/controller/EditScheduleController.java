package com.sysc3010.m7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sysc3010.m7.model.PatientSearchForm;
import com.sysc3010.m7.model.RequestMappings;
import com.sysc3010.m7.service.DatabaseService;
import com.sysc3010.m7.sql.Patient;

@Controller
public class EditScheduleController {

    @Autowired
    private DatabaseService dbService;

    @RequestMapping(value = RequestMappings.EDIT_SCHEDULE_URL, method = RequestMethod.GET)
    public ModelAndView getEdit() {
        ModelAndView mav = new ModelAndView(RequestMappings.EDIT_SCHEDULE);
        //mav.addObject("patient", new Patient("", 3));
        mav.addObject("patientSearchForm", new PatientSearchForm());
        return mav;
    }

    @RequestMapping(value = RequestMappings.EDIT_SCHEDULE_URL, method = RequestMethod.POST)
    public ModelAndView postEdit(@ModelAttribute("patientForm") PatientSearchForm patientForm) {
        ModelAndView mav = new ModelAndView(RequestMappings.EDIT_SCHEDULE);
        Patient patient = dbService.getPatientById(patientForm.getId());
        mav.addObject("patient", patient);
        return mav;
    }
}
