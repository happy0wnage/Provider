package ua.nure.petrov.SummaryTask4.db.entity;

public class Tariff extends Entity {

	private static final long serialVersionUID = 8492303970334564979L;

	private int idService;
	
	private String name;

	private double price;

	private String description;

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (checkPrice(price)) {
			this.price = price;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean checkPrice(double price) {
		if (price > 0) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Tariff [idTariff=" + getId() + ", idService=" + idService + ", name=" + name + ", price="
				+ price + ", description=" + description + "]";
	}
	
	

}
