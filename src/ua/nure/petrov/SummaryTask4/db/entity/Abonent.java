package ua.nure.petrov.SummaryTask4.db.entity;

import java.sql.Date;

public class Abonent extends Entity {

	private static final long serialVersionUID = -2566448039288561330L;

	private int idPayment;

	private String login;

	private String password;

	private String name;

	private String surname;

	private String contactPhone;

	private Date dob;

	private String dobS;

	private String address;

	private String email;

	private String locale;

	public String getDobS() {
		if (dob == null) {
			return dobS;
		}
		return String.valueOf(dob);
	}

	public void setDobS(String dobS) {
		if (isCorrectDateFormat(dobS)) {
			this.dob = Date.valueOf(dobS);
		}
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public int getIdPayment() {
		return idPayment;
	}

	public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if (validateLoginPassword(login)) {
			this.login = login;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (validateLoginPassword(password)) {
			this.password = password;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (validateNameSurname(name)) {
			this.name = name;
		}
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		if (validateNameSurname(surname)) {
			this.surname = surname;
		}
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		if (validatePhone(contactPhone)) {
			this.contactPhone = contactPhone;
		}
	}

	public Date getDob() {
		return (Date) dob.clone();
	}

	public void setDob(String dob) {
		if (isCorrectDateFormat(dob) && isCorrectDate(dob)) {
			this.dob = Date.valueOf(dob);
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (validateEmail(email)) {
			this.email = email;
		}
	}

	public boolean validateNameSurname(String ns) {
		if (Character.isUpperCase(ns.charAt(0))) {
			return true;
		}
		return false;
	}

	public boolean validateEmail(String email) {
		if (email.matches("^\\w+@\\w+[.][a-z]+$")) {
			return true;
		}
		return false;
	}

	public boolean validateLoginPassword(String password) {
		if (password.matches("^[a-zA-Z0-9_-]{3,16}$")) {
			return true;
		}
		return false;
	}

	public boolean validatePhone(String phone) {
		if (phone
				.matches("^(\\+38)?[0](50|63|66|67|68|91|92|93|94|95|96|97|98|99)\\d{7}$")) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Abonent [idAbonent=" + getId() + ", idPayment=" + idPayment
				+ ", login=" + login + ", password=" + password + ", name="
				+ name + ", surname=" + surname + ", contactPhone="
				+ contactPhone + ", dob=" + dob + ", address=" + address
				+ ", email=" + email + ", locale=" + locale + "]";
	}

}
