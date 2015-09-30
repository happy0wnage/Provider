package ua.nure.petrov.SummaryTask4.db.entity;

public class Administrator extends Entity {

	private static final long serialVersionUID = 7946484540164049023L;

	private String login;
	
	private String password;
	
	private String contactPhone;
	
	private String email;

	private String locale;

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Administrator [idAdministrator=" + getId() + ", login=" + login + ", password=" + password
				+ ", contactPhone=" + contactPhone + ", email=" + email + ", locale=" + locale + "]";
	}
	
}
