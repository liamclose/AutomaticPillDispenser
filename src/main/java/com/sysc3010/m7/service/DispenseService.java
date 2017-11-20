package com.sysc3010.m7.service;

import java.net.UnknownHostException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysc3010.m7.sql.Database;
import com.sysc3010.m7.sql.Patient;
import com.sysc3010.m7.udp.onePi;

@Service
public class DispenseService {

    private static final long TIME_THRESHOLD = 5 * 60 * 1000;

    @Autowired
    Database db;

    @Autowired
    onePi sender;

    // Send signal to dispenser
    public boolean dispenseMeds(Patient patient) {

        if (checkTimeToDispense(patient.getDispenseTme())) {
            try {
                return sender.sendto();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // check that patient time is within one minute of current time
    private boolean checkTimeToDispense(Date patientDate) {

        long difference = System.currentTimeMillis() - patientDate.getTime();
        if (difference <= TIME_THRESHOLD) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> getAllPatients() {
        List<String> pat = new ArrayList<>();
        pat.add("patient1");
        pat.add("patient2");
        pat.add("patient3");
        pat.add("patient4");
        pat.add("patient5");
        pat.add("patient6");
        return pat;
    }

}
