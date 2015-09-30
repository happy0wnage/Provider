package ua.nure.petrov.SummaryTask4.db.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AdministratorTest {

	Administrator a;

	@Before
	public void before() {
		a = new Administrator();
	}

	@Test
	public void setLocaleTest() {
		a.setLocale("ru");
		String expected = "ru";
		assertEquals(expected, a.getLocale());
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
	public void setEmailTest() {
		a.setEmail("vlad@gmail.com");
		String expected = "vlad@gmail.com";
		assertEquals(expected, a.getEmail());
	}

	@Test
	public void setContactPhoneTest() {
		a.setContactPhone("+380930243946");
		String expected = "+380930243946";
		assertEquals(expected, a.getContactPhone());
	}

	@Test
	public void toStringTest() {
		a.setLogin("Vlad");
		a.setPassword("1234");
		a.setContactPhone("+380930143946");
		a.setEmail("vlad@gmail.com");
		a.setLocale("ru");

		String expected = "Administrator [idAdministrator=0, login=Vlad, password=1234, contactPhone=+380930143946, email=vlad@gmail.com, locale=ru]";
		assertEquals(expected, a.toString());
	}

}
