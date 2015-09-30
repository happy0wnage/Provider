package ua.nure.petrov.SummaryTask4.db.entity;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Root of all entities which have identifier field.
 * 
 * @author Vladyslav Petrov
 * 
 */
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 8466257860808346236L;

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}

	@Override
	public String toString() {
		return "id=" + id;
	}

	public boolean isCorrectDateFormat(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if (!date.matches("^([0-9]{4})-([0-1][0-9])-([0-3][0-9])$")) {
			return false;
		}
		
		try {
			dateFormat.parse(date);
		} catch (ParseException | IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	public boolean isCorrectDate(String date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -18);
		java.util.Date availableDate = c.getTime();
		Date d1 = Date.valueOf(dateFormat.format(availableDate));

		c = Calendar.getInstance();
		c.add(Calendar.YEAR, -80);
		availableDate = c.getTime();
		Date d2 = Date.valueOf(dateFormat.format(availableDate));

		Date enteredDate = Date.valueOf(date);
		if (enteredDate.before(d1) && enteredDate.after(d2)) {
			return true;
		}

		return false;
	}

}
