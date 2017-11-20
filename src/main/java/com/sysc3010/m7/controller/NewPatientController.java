package com.sysc3010.m7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sysc3010.m7.model.NewPatientForm;
import com.sysc3010.m7.sql.Database;
import com.sysc3010.m7.sql.Patient;

@Controller
public class NewPatientController {

    @Autowired
    Database db;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getNewPatient() {
        ModelAndView mav = new ModelAndView("new");

        return mav;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView postNewPatient(@ModelAttribute NewPatientForm newPatientForm) {
        ModelAndView mav;
        Patient newPatient = new Patient(newPatientForm.getName(), newPatientForm.getRoom());

        newPatient.setDispenseTme(newPatientForm.getTime());
        newPatient.setId(newPatientForm.getId());

        if( db.insertPatient(newPatient)) {
            mav = new ModelAndView("success");
        }else {
            mav = new ModelAndView("failure");
            mav.addObject("error", "Patient data invalid");
        }

        return mav;
    }
}
