package ua.nure.petrov.SummaryTask4.db.entity;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import ua.nure.petrov.SummaryTask4.db.AbstractDAO;

public class Contract extends Entity {

	private static final long serialVersionUID = 1964546040152366761L;

	private int idAbonent;

	private int number;

	private Date startDate;

	private Date endDate;

	public int getIdAbonent() {
		return idAbonent;
	}

	public void setIdAbonent(int idAbonent) {
		this.idAbonent = idAbonent;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getStartDate() {
		return (Date) startDate.clone();
	}

	public void setStartDate(String startDate) {
		if (startDate.isEmpty()) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = new java.util.Date();
			this.startDate = Date.valueOf(dateFormat.format(date));
		}
		if (isCorrectDateFormat(startDate)) {
			this.startDate = Date.valueOf(startDate);
		}
	}

	public Date getEndDate() {
		return (Date) endDate.clone();
	}

	public void setEndDate(String endDate) {
		if (endDate.isEmpty()) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.YEAR, 5);
			java.util.Date date = c.getTime();
			this.endDate = Date.valueOf(dateFormat.format(date));
		}

		if (isCorrectDateFormat(endDate)) {
			this.endDate = Date.valueOf(endDate);
		}
	}

	public int generateNumber() {
		Random rand = new Random();
		int range = 99999999;

		AbstractDAO dao = new AbstractDAO();
		int number = rand.nextInt(range);

		if (!dao.checkContractNumber(number)) {
			generateNumber();
		}

		return number;
	}

	@Override
	public String toString() {
		return "Contract [idContract=" + getId() + ", idAbonent=" + idAbonent
				+ ", number=" + number + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

}
