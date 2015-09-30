package ua.nure.petrov.SummaryTask4.db.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TariffContractTest {

	TariffContract tc;

	@Before
	public void before() {
		tc = new TariffContract();
		tc.setIdContract(1);
		tc.setIdTariff(2);
	}
	
	@Test
	public void setTariffContractTest() {
		int expected = 1;
		assertEquals(expected, tc.getIdContract());
		
		expected = 2;
		assertEquals(expected, tc.getIdTariff());
	}
	
	@Test
	public void toStringTest() {
		String expected = "TariffContract [idTariff=2, idContract=1]";
		assertEquals(expected, tc.toString());
	}
	
}
