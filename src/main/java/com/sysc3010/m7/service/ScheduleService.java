package com.sysc3010.m7.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.PreparedStatement;
import com.sysc3010.m7.model.PatientData;

@Service
public class ScheduleService {

    private static final String SELECT_SCHEDULE_BY_ID = "SELECT * FROM schedules WHERE id = ?";

    MySQLConnection conn;

    public PatientData getSchedule(String patientId) {
        PatientData schedule = null;
        try {
            PreparedStatement query = new PreparedStatement(conn, SELECT_SCHEDULE_BY_ID);
            query.setString(0, patientId);
            ResultSet result = query.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return schedule;
    }

    public void saveSchedule(PatientData newSchedule) {
        PatientData currentSchedule = getSchedule(newSchedule.getId());
        currentSchedule.setHour(newSchedule.getHour());
        currentSchedule.setHalfHour(newSchedule.isHalfHour());
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
