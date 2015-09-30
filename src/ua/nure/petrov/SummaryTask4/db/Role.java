package ua.nure.petrov.SummaryTask4.db;


public enum Role {
	ADMIN, CLIENT;
	
	public static Role getRole(String login) {
		AbstractDAO dao = new AbstractDAO();

		if (dao.findAbonentByLogin(login) != null) {
			return CLIENT;
		}

		if (dao.findAdminByLogin(login) != null) {
			return ADMIN;
		}

		return null;
	}
	
	public static Role setRole(String userRole) {
		if(userRole.equals(ADMIN.getName())) {
			return ADMIN;
		} else if(userRole.equals(CLIENT.getName())) {
			return CLIENT;
		}
		return null;
		
	}
	
	public String getName() {
		return name().toLowerCase();
	}
}
