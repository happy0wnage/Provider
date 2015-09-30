package ua.nure.petrov.SummaryTask4.db.entity;

public class Service extends Entity {

	private static final long serialVersionUID = 701900689699995177L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Service [idService=" + getId() + ", name=" + name + "]";
	}

}
