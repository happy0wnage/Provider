package ua.nure.petrov.SummaryTask4.db;

import javax.servlet.http.HttpSession;

public class SelectLocale {

	private String ru;
	
	private String en;
	
	public String getRu() {
		return ru;
	}

	public void setRu(String ru) {
		this.ru = ru;
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public SelectLocale(String ru, String en) {
		this.ru = ru;
		this.en = en;
	}

	public SelectLocale() {
	}
	public String getMessage(HttpSession session) {
		if ("ru".equals(session.getAttribute("defaultLocale"))) {
			return ru;
		} else if ("en".equals(session.getAttribute("defaultLocale"))) {
			return en;
		}
		return ru;
	}
	
}
