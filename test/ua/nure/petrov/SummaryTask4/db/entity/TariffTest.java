package ua.nure.petrov.SummaryTask4.db.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TariffTest {

	Tariff t;

	@Before
	public void before() {
		t = new Tariff();
		t.setIdService(1);
		t.setName("Internet");
		t.setPrice(12.);
		t.setDescription("ok");
	}
	
	@Test
	public void setIdServiceTest() {
		int expected = 1;
		assertEquals(expected, t.getIdService());
	}
	
	@Test
	public void setNameTest() {
		String expected = "Internet";
		assertEquals(expected, t.getName());
	}
	
	@Test
	public void setPriceTest() {
		double expected = 12.;
		assertEquals(expected, t.getPrice(), 0.00001);
	}
	
	@Test
	public void setDescriptionTest() {
		String expected = "ok";
		assertEquals(expected, t.getDescription());
	}
	
	@Test
	public void checkPrice() {
		t.setPrice(-1);
		assertTrue(t.checkPrice(t.getPrice()));
	}
	
	@Test
	public void toStringTest() {
		t.setName("Internet");
		
		String expected = "Tariff [idTariff=0, idService=1, name=Internet, price=12.0, description=ok]";
		assertEquals(expected, t.toString());
	}
	
}
