//package server;
//
//import static org.junit.Assert.assertEquals;
//
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.InetAddress;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.stubbing.Answer;
//
//import com.sysc3010.m7.sql.Database;
//
//public class ServerMainTest {
//	
//	@InjectMocks private static ServerMain s;
//
//	 @Mock static private Database mockConnection;
//	 
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		mockConnection = Mockito.mock(Database.class);
//		s = new ServerMain();
//		s.d = mockConnection;
//		s.start();
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//	static Object[] actual = {};
//	
//	public static void setActual(Object[] o) {
//		actual =  o.clone();
//	}
//	@Test
//	public void testNewPatientValid() {
//		System.out.println("Testing adding a valid patient");
//		Mockito.doAnswer(new Answer() {
//			         public Object answer(InvocationOnMock invocation) {
//			             Object[] args= invocation.getArguments();
//			             setActual(args);
//			             return "Ok";
//			         }
//			 });//.when(mockConnection).insertPatient(Mockito.anyString(), Mockito.anyInt());
//		DatagramSocket testSender;
//		try {
//			String m = "Insert Patient, Test Patient, 7";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.setSoTimeout(5000);
//			testSender.receive(r);
//			testSender.close();
//			Object[] expected = {"Test Patient", 7};
//			assertEquals("Success", new String(r.getData(), 0, r.getLength()));
//			assertEquals("Same patient name", expected[0], actual[0]);
//			assertEquals("Same room", expected[1], actual[1]);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testNewPatientInvalid() {
//		System.out.println("Testing adding an invalid patient");
//		//Mockito.doThrow(new RuntimeException("If this happens, something went wrong.")).when(mockConnection).insertPatient("Test Patient", 7);
//		DatagramSocket testSender;
//		try {
//			String m = "insert patient, Test Patient, re7";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.setSoTimeout(5000);
//			testSender.receive(r);
//			testSender.close();
//			assertEquals(ServerMain.errorMsg + 2, new String(r.getData(), 0, r.getLength()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testSetRoomValid() {
//		System.out.println("Testing seting a valid room");
//		Mockito.doAnswer(new Answer() {
//			         public Object answer(InvocationOnMock invocation) {
//			             Object[] args= invocation.getArguments();
//			             setActual(args);
//			             return "Ok";
//			         }
//			 }).when(mockConnection).setRoom(Mockito.anyString(), Mockito.anyString());
//		DatagramSocket testSender;
//		try {
//			String m = "Set room, 2, 7";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.setSoTimeout(5000);
//			testSender.receive(r);
//			testSender.close();
//			Object[] expected = {"2", "7"};
//			assertEquals("Same patient id", expected[0], actual[0]);
//			assertEquals("Same room", expected[1], actual[1]);
//			assertEquals("Success", new String(r.getData(), 0, r.getLength()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testSetRoomInvalid() {
//		System.out.println("Setting an invalid room");
//		Mockito.doThrow(new RuntimeException("If this happens, something went wrong.")).when(mockConnection).setRoom(Mockito.anyString(), Mockito.anyString());
//		DatagramSocket testSender;
//		try {
//			String m = "Set room, 2e, 7";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.setSoTimeout(5000);
//			testSender.receive(r);
//			testSender.close();
//			assertEquals(ServerMain.errorMsg + 2, new String(r.getData(), 0, r.getLength()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testGetPatientReal() {
//		System.out.println("Testing get on a 'valid' patient.");
//		Patient pat = new Patient("Test Patient", 2);
//		Mockito.when(mockConnection.queryPatientByName(Mockito.anyString())).thenReturn(pat);
//		DatagramSocket testSender;
//		try {
//			String m = "Get Patient, Test Patient";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.receive(r);
//			testSender.close();
//			assertEquals(pat.toString(), new String (r.getData(), 0, r.getLength()));
//			//need return
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testGetPatientInfoNotReal() {
//		System.out.println("Testing get on a 'valid' patient.");
//		Mockito.when(mockConnection.queryPatientByName(Mockito.anyString())).thenReturn(null);
//		DatagramSocket testSender;
//		try {
//			String m = "Get id, Test Patient";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			testSender.setSoTimeout(5000);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.receive(r);
//			testSender.close();
//			Object[] expected = {};
//			assertEquals(ServerMain.errorMsg + 1, new String(r.getData(), 0, r.getLength()));
//			//need return
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testNewMedicationDispenserExists() {
//		System.out.println("Testing adding a valid medication.");
//		Patient pat = new Patient("Test Patient", 7);
//		Mockito.doAnswer(new Answer() {
//			         public Object answer(InvocationOnMock invocation) {
//			             Object[] args= invocation.getArguments();
//			             setActual(args);
//			             return "Ok";
//			         }
//			 });//.when(mockConnection).insertMedication(Mockito.anyInt(), Mockito.anyString(), Mockito.anyInt());
//		Mockito.when(mockConnection.queryPatientById(Mockito.anyString())).thenReturn(pat);
//		DatagramSocket testSender;
//		try {
//			String m = "Insert Medication, 1, Test Medication, 200";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.setSoTimeout(5000);
//			testSender.receive(r);
//			testSender.close();
//			Object[] expected = {1, "Test Medication", 200};
//			assertEquals("Same patient id", expected[0], actual[0]);
//			assertEquals("Same medication", expected[1], actual[1]);
//			assertEquals("Same dosage", expected[2], actual[2]);
//			assertEquals("Success", new String(r.getData(),0,r.getLength()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testNewMedicationDispenserDoesNotExist() {
//		System.out.println("Creating medication for nonexistent patient/dispenser");
//		Mockito.when(mockConnection.queryPatientById(Mockito.anyString())).thenReturn(null);
//		//Mockito.doThrow(new RuntimeException("If this happens, something went wrong.")).when(mockConnection).insertMedication(Mockito.anyInt(), Mockito.anyString(), Mockito.anyInt());
//		DatagramSocket testSender;
//		try {
//			String m = "Insert Medication, 100, Test Medication, 200";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			testSender.setSoTimeout(5000);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.receive(r);
//			testSender.close();
//			Object[] expected = {};
//			assertEquals(ServerMain.errorMsg + 2, new String(r.getData(), 0, r.getLength()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testAddDosageSuccess() {
//		System.out.println("Testing adding a valid dosage record.");
//		String date = "2017-11-11 14:42:00";
//		Medication med = new Medication("Test Medication", 1, 200);
//		Mockito.doAnswer(new Answer() {
//			         public Object answer(InvocationOnMock invocation) {
//			             Object[] args= invocation.getArguments();
//			             setActual(args);
//			             return "Ok";
//			         }
//			 }).when(mockConnection).medicationApplied(date, med);
//		Mockito.when(mockConnection.queryMedicationById(Mockito.anyString())).thenReturn(med);
//		DatagramSocket testSender;
//		try {
//			String m = "Dosage applied, " + date + ", 1";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.setSoTimeout(5000);
//			testSender.receive(r);
//			testSender.close();
//			Object[] expected = {date, med};
//			assertEquals("Same date", expected[0], actual[0]);
//			assertEquals("Same medication", expected[1], actual[1]);
//			assertEquals("Success", new String(r.getData(),0,r.getLength()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//		}
//	}
//	
//	@Test
//	public void testAddDosageFail() {
//		System.out.println("Adding dosage for nonexistent medication");
//		Mockito.when(mockConnection.queryMedicationById(Mockito.anyString())).thenReturn(null);
//		Mockito.doThrow(new RuntimeException("If this happens, something went wrong.")).when(mockConnection).medicationApplied(Mockito.anyString(), Mockito.anyObject());
//		DatagramSocket testSender;
//		try {
//			String m = "Dosage applied, 2017-11-11 20:42:00, 200";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			testSender.setSoTimeout(5000);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.receive(r);
//			testSender.close();
//			Object[] expected = {};
//			assertEquals(ServerMain.errorMsg + 2, new String(r.getData(), 0, r.getLength()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void testGetDosageInfoReal() {
//		System.out.println("Testing get on a 'valid' medication.");
//		Medication med = new Medication("Test Med",3,1);
//		Mockito.when(mockConnection.queryMedicationById(Mockito.anyString())).thenReturn(med);
//		DatagramSocket testSender;
//		try {
//			String m = "Get dosage, 1";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.receive(r);
//			testSender.close();
//			assertEquals(med.toString(), new String (r.getData(), 0, r.getLength()));
//			//need return
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testGetDosageInfoNotReal() {
//		System.out.println("Testing get on an invalid medication.");
//		Mockito.when(mockConnection.queryMedicationById(Mockito.anyString())).thenReturn(null);
//		DatagramSocket testSender;
//		try {
//			String m = "Get Dosage, 1";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			testSender.setSoTimeout(5000);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.receive(r);
//			testSender.close();
//			Object[] expected = {};
//			assertEquals(ServerMain.errorMsg + 1, new String(r.getData(), 0, r.getLength()));
//			//need return
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testRemoveDosageFail() {
//		System.out.println("Testing removing medication");
//		Mockito.doThrow(new RuntimeException("If this happens, something went wrong.")).when(mockConnection).deleteMedication(Mockito.anyString(), Mockito.anyBoolean());
//		DatagramSocket testSender;
//		try {
//			String m = "Remove medication, 1, test";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			testSender.setSoTimeout(5000);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.receive(r);
//			testSender.close();
//			Object[] expected = {};
//			assertEquals(ServerMain.errorMsg + 3, new String(r.getData(), 0, r.getLength()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testRemoveDosageSuccess() {
//		System.out.println("Testing removing medication");
//		Mockito.doAnswer(new Answer() {
//	         public Object answer(InvocationOnMock invocation) {
//	             Object[] args= invocation.getArguments();
//	             setActual(args);
//	             return "Ok";
//	         }
//		}).when(mockConnection).deleteMedication("1", false);
//		try {
//			DatagramSocket testSender;
//			String m = "Remove medication, 1, false";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.setSoTimeout(5000);
//			testSender.receive(r);
//			testSender.close();
//			Object[] expected = {"1", false};
//			assertEquals("Same medication", expected[0], actual[0]);
//			assertEquals("Same delete past", expected[1], actual[1]);
//			assertEquals("Success", new String(r.getData(),0,r.getLength()));
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testRemovePatientSuccess() {
//		System.out.println("Testing removing patient");
//		Mockito.doAnswer(new Answer() {
//	         public Object answer(InvocationOnMock invocation) {
//	             Object[] args= invocation.getArguments();
//	             setActual(args);
//	             return "Ok";
//	         }
//		}).when(mockConnection).deletePatient("1", true);
//		try {
//			DatagramSocket testSender;
//			String m = "Remove Patient, 1, true";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.setSoTimeout(5000);
//			testSender.receive(r);
//			testSender.close();
//			Object[] expected = {"1", true};
//			assertEquals("Same patient", expected[0], actual[0]);
//			assertEquals("Same delete past", expected[1], actual[1]);
//			assertEquals("Success", new String(r.getData(),0,r.getLength()));
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}	
//	
//	@Test
//	public void testRemovePatientFail() {
//		System.out.println("Testing removing patient");
//		Mockito.doThrow(new RuntimeException("If this happens, something went wrong.")).when(mockConnection).deletePatient(Mockito.anyString(), Mockito.anyBoolean());
//		DatagramSocket testSender;
//		try {
//			String m = "Remove patient, 1";
//			testSender = new DatagramSocket();
//			byte data[] = m.getBytes();
//			DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8700);
//			testSender.send(p);
//			testSender.setSoTimeout(5000);
//			data = new byte[512];
//			DatagramPacket r = new DatagramPacket(data, data.length);
//			testSender.receive(r);
//			testSender.close();
//			Object[] expected = {};
//			assertEquals(ServerMain.errorMsg + 3, new String(r.getData(), 0, r.getLength()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
