package ua.nure.petrov.SummaryTask4.db.entity;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class ContractTest {

	Contract c;

	@Before
	public void before() {
		c = new Contract();
	}

	@Test
	public void setNumberTest() {
		c.setNumber(645445645);
		int expected = 645445645;
		assertEquals(expected, c.getNumber());
	}

	@Test
	public void setIdAbonentTest() {
		c.setIdAbonent(1);
		int expected = 1;
		assertEquals(expected, c.getIdAbonent());
	}
	
	@Test
	public void setStartDateTest() {
		c.setStartDate("2015-02-03");
		String date = "2015-02-03";
		Date expected = Date.valueOf(date); 
		assertEquals(expected, c.getStartDate());
	}
	
	@Test
	public void setEndDateTest() {
		c.setEndDate("2015-02-03");
		String date = "2015-02-03";
		Date expected = Date.valueOf(date); 
		assertEquals(expected, c.getEndDate());
	}

	@Test
	public void setEndDateAutoTest() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 5);
		java.util.Date date = calendar.getTime();
		Date expected = Date.valueOf(dateFormat.format(date));
		
		c.setEndDate("");
		assertEquals(expected, c.getEndDate());
	}
	
	@Test
	public void setStartDateAutoTest() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		java.util.Date date = calendar.getTime();
		Date expected = Date.valueOf(dateFormat.format(date));
		
		c.setStartDate("");
		assertEquals(expected, c.getStartDate());
	}
	
	@Test
	public void toStringTest() {
		c.setIdAbonent(1);
		c.setNumber(645445645);
		c.setStartDate("2015-02-03");
		c.setEndDate("2015-02-03");

		String expected = "Contract [idContract=0, idAbonent=1, number=645445645, startDate=2015-02-03, endDate=2015-02-03]";
		assertEquals(expected, c.toString());
	}

}
