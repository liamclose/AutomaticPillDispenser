package com.sysc3010.m7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sysc3010.m7.model.PatientSearchForm;
import com.sysc3010.m7.model.RequestMappings;
import com.sysc3010.m7.service.DatabaseService;

import server.Patient;

@Controller
public class MainController {

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(value = RequestMappings.MAIN_URL, method = RequestMethod.GET)
    public ModelAndView mainGet() {
        ModelAndView mav = new ModelAndView(RequestMappings.MAIN);

        List<Patient> patientList = databaseService.getAllPatients();
        for (Patient patient : patientList) {
            System.out.println(patient.getId());
            System.out.println(patient.getName());
        }
        mav.addObject("patientList", patientList);
        mav.addObject("patientSearchForm", new PatientSearchForm());
        return mav;
    }
}
