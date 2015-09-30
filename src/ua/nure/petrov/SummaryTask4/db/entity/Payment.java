package ua.nure.petrov.SummaryTask4.db.entity;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Payment extends Entity {

	private static final long serialVersionUID = 1L;

	private double balance;

	private boolean status;

	private Date endDateService;

	private Date lastDateOfWithdrawal;

	public double getBalance() {
		return balance;
	}

	public boolean checkBalance(double balance) {
		if (balance <= 0) {
			return false;
		}
		return true;
	}

	public void addBalance(double value) {
		balance += value;
		status = true;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getStatus() {
		return status;
	}

	public Date getEndDateService() {
		return (Date) endDateService.clone();
	}

	public void setEndDateService(String endDateService) {
		if (endDateService.isEmpty()) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = new java.util.Date();
			this.endDateService = Date.valueOf(dateFormat.format(date));
		}
		if (isCorrectDateFormat(endDateService)) {
			this.endDateService = Date.valueOf(endDateService);
		}
	}

	public Date getLastDateOfWithdrawal() {
		return (Date) lastDateOfWithdrawal.clone();
	}

	public void setLastDateOfWithdrawal(String lastDateOfWithdrawal) {
		if (lastDateOfWithdrawal.isEmpty()) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = new java.util.Date();
			this.lastDateOfWithdrawal = Date.valueOf(dateFormat.format(date));
		}
		if (isCorrectDateFormat(lastDateOfWithdrawal)) {
			this.lastDateOfWithdrawal = Date.valueOf(lastDateOfWithdrawal);
		}
	}

	@Override
	public String toString() {
		return "Payment [idPayment=" + getId() + ", balance=" + balance
				+ ", status=" + status + ", endDateService=" + endDateService
				+ ", lastDateOfWithdrawal=" + lastDateOfWithdrawal + "]";
	}
}
