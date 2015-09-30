package ua.nure.petrov.SummaryTask4.db.entity;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.*;

public class AbonentTest {

	Abonent a;
	@Before
	public void before() {
		a = new Abonent();
	}

	@Test
	public void setLocaleTest() {
		a.setLocale("ru");
		String expected = "ru";
		assertEquals(expected, a.getLocale());
	}
	
	@Test
	public void setNameTest() {
		a.setName("Vlad");
		String expected = "Vlad";
		assertEquals(expected, a.getName());
	}
	
	@Test
	public void setSurnameTest() {
		a.setSurname("Petrov");
		String expected = "Petrov";
		assertEquals(expected, a.getSurname());
	}
	
	@Test
	public void setAddressTest() {
		a.setAddress("address");
		String expected = "address";
		assertEquals(expected, a.getAddress());
	}
	
	@Test
	public void setEmailTest() {
		a.setEmail("vlad@gmail.com");
		String expected = "vlad@gmail.com";
		assertEquals(expected, a.getEmail());
	}
	
	@Test
	public void setIdPaymentTest() {
		a.setIdPayment(1);
		int expected = 1;
		assertEquals(expected, a.getIdPayment());
	}
	
	@Test
	public void setContactPhoneTest() {
		a.setContactPhone("+380930243946");
		String expected = "+380930243946";
		assertEquals(expected, a.getContactPhone());
	}
	
	@Test
	public void setDobTest() {
		a.setDob("1995-07-12");
		String date = "1995-07-12";
		Date expected = Date.valueOf(date); 
		assertEquals(expected, a.getDob());
	}
	
	@Test
	public void setLoginTest() {
		a.setLogin("Vladyslav");
		String expected = "Vladyslav";
		assertEquals(expected, a.getLogin());
	}
	
	@Test
	public void setPasswordTest() {
		a.setPassword("Vladyslav");
		String expected = "Vladyslav";
		assertEquals(expected, a.getPassword());
	}
	
	@Test
	public void validateNameSurnameTrueTest() {
		assertTrue(a.validateNameSurname("Vladyslav"));
	}
	
	@Test
	public void validateNameSurnameFalseTest() {
		assertFalse(a.validateNameSurname("vladyslav"));
	}
	
	@Test
	public void validateEmailTrueTest() {
		assertTrue(a.validateEmail("vlad@gmail.com"));
	}
	
	@Test
	public void validateEmailFalseTest() {
		assertFalse(a.validateEmail("vladgmail.com"));
	}
	
	@Test
	public void validateLoginPaswordTrueTest() {
		assertTrue(a.validateLoginPassword("Vlad"));
	}
	
	@Test
	public void validateLoginPaswordFalseTest() {
		assertFalse(a.validateLoginPassword("V,"));
	}
	
	@Test
	public void validatePhoneFalseTest() {
		assertFalse(a.validatePhone("809302439461"));
	}
	
	@Test
	public void validatePhoneTrueTest() {
		assertTrue(a.validatePhone("+380930243946"));
	}
	
	@Test
	public void validateDateTest() {
		assertTrue(a.isCorrectDate("1995-02-03"));
		assertFalse(a.isCorrectDate("2013-02-03"));
	}
	
	@Test
	public void validateDateFormatTest() {
		assertTrue(a.isCorrectDateFormat("1995-02-03"));
		assertFalse(a.isCorrectDateFormat("2013-1-3"));
	}
	
	@Test 
	public void toStringTest() {
		a.setLogin("Vlad");
		a.setPassword("1234");
		a.setEmail("vlad@gmail.com");
		a.setAddress("address");
		a.setDob("1995-07-12");
		String expected = "Abonent [idAbonent=0, idPayment=0, login=Vlad, password=1234, name=null, surname=null, contactPhone=null, dob=1995-07-12, address=address, email=vlad@gmail.com, locale=null]";
		assertEquals(expected, a.toString());
	}
	
	
}
