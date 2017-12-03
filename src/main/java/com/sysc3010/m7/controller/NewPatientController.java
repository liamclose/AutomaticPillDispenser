package com.sysc3010.m7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sysc3010.m7.model.NewPatientForm;
import com.sysc3010.m7.model.RequestMappings;
import com.sysc3010.m7.service.DatabaseService;
import com.sysc3010.m7.sql.Patient;

@Controller
public class NewPatientController {

    @Autowired
    private DatabaseService DatabaseService;

    @RequestMapping(value = RequestMappings.NEW_PATIENT_URL, method = RequestMethod.GET)
    public ModelAndView getNewPatient() {
        ModelAndView mav = new ModelAndView(RequestMappings.NEW_PATIENT);
        mav.addObject("newPatientForm", new NewPatientForm());
        return mav;
    }

    @RequestMapping(value = RequestMappings.NEW_PATIENT_URL, method = RequestMethod.POST)
    public ModelAndView postNewPatient(@ModelAttribute NewPatientForm newPatientForm) {
        ModelAndView mav;
        Patient newPatient = new Patient(newPatientForm.getName(), newPatientForm.getRoom());

        newPatient.setDispenseTme(newPatientForm.getTime());
        newPatient.setId(newPatientForm.getId());

        if (DatabaseService.writePatientData(newPatient)) {
            RedirectView r = new RedirectView(RequestMappings.EDIT_SCHEDULE);
            mav = new ModelAndView("edit");
            mav.addObject("success", newPatient.getName() + " added to the database");
            mav.addObject("patient", newPatient);
        } else {
            mav = new ModelAndView("failure");
            mav.addObject("error", "Patient data invalid");
        }
        return mav;
    }
}
