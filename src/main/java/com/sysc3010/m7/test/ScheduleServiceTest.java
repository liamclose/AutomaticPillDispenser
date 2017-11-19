package com.sysc3010.m7.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import com.mysql.jdbc.MySQLConnection;
import com.sysc3010.m7.model.PatientData;
import com.sysc3010.m7.service.ScheduleService;

public class ScheduleServiceTest {

    @Mock
    MySQLConnection conn;


    @Test
    public void testGetSchedule() {
//        when(conn.clientPrepareStatement(null))
        //when(conn.createClob()).thenReturn(null);
        ScheduleService scheduleService = new ScheduleService();
        PatientData testData = new PatientData("1", "fname", "lname", 3, false);
        ReflectionTestUtils.setField(scheduleService, "conn", conn);
        PatientData result = scheduleService.getSchedule(testData.getId());
        assertEquals(testData, result);
    }

}
