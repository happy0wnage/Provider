package ua.nure.petrov.SummaryTask4.db.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ServiceTest {

	Service s;

	@Before
	public void before() {
		s = new Service();
	}
	
	@Test
	public void setNameTest() {
		s.setName("Internet");
		String expected = "Internet";
		assertEquals(expected, s.getName());
	}
	
	@Test
	public void toStringTest() {
		s.setName("Internet");
		
		String expected = "Service [idService=0, name=Internet]";
		assertEquals(expected, s.toString());
	}
	
}
