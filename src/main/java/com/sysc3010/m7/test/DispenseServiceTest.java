package com.sysc3010.m7.test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.UnknownHostException;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import com.sysc3010.m7.service.DispenseService;
import com.sysc3010.m7.sql.Database;
import com.sysc3010.m7.sql.Patient;
import com.sysc3010.m7.udp.onePi;

public class DispenseServiceTest {

    @Mock
    private Database mockDb;
    @Mock
    private onePi mockSender;

    private DispenseService scheduleService;

    @Before
    public void setupTest() {
        mockDb = mock(Database.class);
        mockSender = mock(onePi.class);
        try {
            when(mockSender.sendto()).thenReturn(true);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        scheduleService = new DispenseService();
        ReflectionTestUtils.setField(scheduleService, "sender", mockSender);
        ReflectionTestUtils.setField(scheduleService, "db", mockDb);
    }

    @Test
    public void testDispenseMedsValid() {
        Patient testPatient = new Patient("name", 1);
        testPatient.setDispenseTme(new Date(System.currentTimeMillis()));
        boolean response = scheduleService.dispenseMeds(testPatient);
        assertTrue(response);
    }

    @Test
    public void testDispenseMedsInvalid() {
        Patient testPatient = new Patient("name", 1);
        testPatient.setDispenseTme(new Date(0));
        boolean response = scheduleService.dispenseMeds(testPatient);
        assertTrue(!response);
    }

}
