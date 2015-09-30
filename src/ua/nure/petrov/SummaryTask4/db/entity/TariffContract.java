package ua.nure.petrov.SummaryTask4.db.entity;

public class TariffContract {

	private int idTariff;

	private int idContract;

	public int getIdTariff() {
		return idTariff;
	}

	public void setIdTariff(int idTariff) {
		this.idTariff = idTariff;
	}

	public int getIdContract() {
		return idContract;
	}

	public void setIdContract(int idContract) {
		this.idContract = idContract;
	}

	@Override
	public String toString() {
		return "TariffContract [idTariff=" + idTariff + ", idContract="
				+ idContract + "]";
	}
	
}
