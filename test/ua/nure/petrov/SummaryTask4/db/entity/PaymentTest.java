package ua.nure.petrov.SummaryTask4.db.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class PaymentTest {

	Payment p;

	@Before
	public void before() {
		p = new Payment();
	}
	
	@Test
	public void setBalanceTest() {
		p.setBalance(123);
		double expected = 123.0;
		assertEquals(expected, p.getBalance(), 0.0001);
	}
	
	@Test
	public void setStatusTest() {
		p.setStatus(false);
		assertFalse(p.getStatus());
		
		p.setStatus(true);
		assertTrue(p.getStatus());
	}
	
	@Test
	public void setStartDateTest() {
		p.setEndDateService("2015-02-03");
		String date = "2015-02-03";
		Date expected = Date.valueOf(date); 
		assertEquals(expected, p.getEndDateService());
	}
	
	@Test
	public void setEndDateTest() {
		p.setLastDateOfWithdrawal("2015-02-03");
		String date = "2015-02-03";
		Date expected = Date.valueOf(date); 
		assertEquals(expected, p.getLastDateOfWithdrawal());
	}
	
	@Test
	public void setDateAutoTest() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		java.util.Date date = calendar.getTime();
		Date expected = Date.valueOf(dateFormat.format(date));
		
		p.setEndDateService("");
		assertEquals(expected, p.getEndDateService());
		
		p.setLastDateOfWithdrawal("");
		assertEquals(expected, p.getLastDateOfWithdrawal());
	}
	
	@Test
	public void checkBalanceTest() {
		assertTrue(p.checkBalance(1));
		assertFalse(p.checkBalance(-1));
	}
	
	@Test
	public void addBalanceTest() {
		p.setBalance(1);
		p.addBalance(5);
		double expected = 6;
		assertEquals(expected, p.getBalance(), 0.0001);
	}
	
	@Test
	public void toStringTest() {
		p.setBalance(1);
		p.setEndDateService("2003-02-03");
		p.setLastDateOfWithdrawal("2003-02-03");
		p.setStatus(false);
		
		String expected = "Payment [idPayment=0, balance=1.0, status=false, endDateService=2003-02-03, lastDateOfWithdrawal=2003-02-03]";
		assertEquals(expected, p.toString());
	}
	
}
