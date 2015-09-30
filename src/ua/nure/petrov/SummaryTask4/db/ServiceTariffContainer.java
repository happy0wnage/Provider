package ua.nure.petrov.SummaryTask4.db;

public class ServiceTariffContainer {

	private String serviceName;

	private String tariffName;
	
	private double tariffPrice;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getTariffName() {
		return tariffName;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}

	public double getTariffPrice() {
		return tariffPrice;
	}

	public void setTariffPrice(double tariffPrice) {
		this.tariffPrice = tariffPrice;
	}

	@Override
	public String toString() {
		return "ServiceTariffContainer [serviceName=" + serviceName
				+ ", tariffName=" + tariffName + ", tariffPrice=" + tariffPrice
				+ "]";
	}
	
	
	
}
