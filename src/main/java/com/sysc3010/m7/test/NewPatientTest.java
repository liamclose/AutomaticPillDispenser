package com.sysc3010.m7.test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.sysc3010.m7.controller.NewPatientController;
import com.sysc3010.m7.model.NewPatientForm;
import com.sysc3010.m7.sql.Database;

public class NewPatientTest {

    @Mock
    private Database mockDb;
    
    private NewPatientController npc;
    
    @Before
    public void setupTest() {
        mockDb = mock(Database.class);
        
        npc = new NewPatientController();
        ReflectionTestUtils.setField(npc, "db", mockDb);
    }
    
    @Test
    public void testAddNewPatientValid() {
        
        NewPatientForm npf = new NewPatientForm("n", 1, 1, new Date(System.currentTimeMillis()));
        when(mockDb.insertPatient(any())).thenReturn(true);
        
        ModelAndView result = npc.postNewPatient(npf);
        
        assertTrue(result.getViewName().equals("success"));
        assertNull(result.getModel().get("error"));
    }
    
    @Test
    public void testAddNewPatientInvalid() {
        
        NewPatientForm npf = new NewPatientForm("n", 1, 1, new Date(System.currentTimeMillis()));
        when(mockDb.insertPatient(any())).thenReturn(false);
        
        ModelAndView result = npc.postNewPatient(npf);
        
        assertTrue(result.getViewName().equals("failure"));
        assertTrue(result.getModel().get("error") != null);
    }
    
}
