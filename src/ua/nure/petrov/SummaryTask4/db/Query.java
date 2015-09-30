package ua.nure.petrov.SummaryTask4.db;

public class Query {

	public static final String FIND_ABONENTS = "SELECT * FROM abonent;";
	public static final String FIND_PAYMENT = "SELECT * FROM payment;";
	public static final String FIND_ADMINISTRATOR = "SELECT * FROM administrator;";
	public static final String FIND_CONTRACT = "SELECT * FROM contract;";
	public static final String FIND_SERVICE = "SELECT * FROM service;";
	public static final String FIND_TARIFF = "SELECT * FROM tariff;";
	public static final String FIND_ACTION = "SELECT * FROM action;";

	public static final String FIND_TARIFF_CONTRACT = "SELECT * FROM tariffcontract;";
	public static final String FIND_TARIFF_CONTRACT_BY_ID_CONTRACT = "SELECT * FROM tariffcontract WHERE idContract = ?;";
	public static final String FIND_TARIFF_CONTRACT_BY_ID_TARIFF = "SELECT * FROM tariffcontract WHERE idTariff = ?;";
	public static final String FIND_SERVICESCONTRACT_BY_ID_CONTRACT = "SELECT * FROM servicecontract WHERE idContract = ?;";
	public static final String FIND_SERVICESCONTRACT_BY_ID_SERVICE = "SELECT * FROM servicecontract WHERE idService = ?;";
	public static final String FIND_TARIFFS_BY_ID_CONTRACT = "SELECT * FROM Tariff WHERE idTariff IN (SELECT idTariff FROM tariffcontract WHERE idContract = ?);";
	public static final String FIND_CONTRACT_BY_ID = "SELECT * FROM contract WHERE idContract = ?;";
	public static final String FIND_PAYMENT_BY_CONTRACT = "SELECT * FROM payment WHERE idPayment = (SELECT idPayment FROM abonent WHERE idAbonent = (SELECT idAbonent FROM contract where idContract = ?));";

	public static final String FIND_CONTRACT_BY_ID_ABONENT = "SELECT * FROM contract where idAbonent = ?";
	public static final String FIND_ABONENT_BY_ID_PAYMENT = "SELECT * FROM abonent where idPayment = ?";
	public static final String FIND_CONTRACT_BY_NUMBER = "SELECT * FROM contract WHERE number = ?;";
	public static final String FIND_PAYMENT_BY_ID = "SELECT * FROM payment where idPayment = ?";
	public static final String FIND_ABONENT_BY_LOGIN = "SELECT * FROM abonent WHERE login LIKE ?;";
	public static final String FIND_ABONENTS_BY_LOGIN = "SELECT * FROM abonent WHERE login LIKE ?;";
	public static final String FIND_ABONENT_BY_ID = "SELECT * FROM abonent WHERE idAbonent LIKE ?;";
	public static final String FIND_ABONENTS_BY_ID = "SELECT * FROM abonent WHERE idAbonent = ?;";
	public static final String FIND_ADMIN_BY_LOGIN = "SELECT * FROM administrator WHERE login LIKE ?;";
	public static final String FIND_TARIFF_BY_ID = "SELECT * FROM tariff where idTariff = ?;";
	public static final String FIND_TARIFF_BY_ID_SERVICE = "SELECT * FROM tariff where idService = ?;";
	public static final String FIND_SERVICE_BY_ID = "SELECT * FROM service WHERE idService = ?;";
	public static final String FIND_ACTIONS_BY_ID = "SELECT * FROM action WHERE idPayment = ?;";

	public static final String INSERT_ABONENT = "INSERT INTO abonent (idPayment, Login, `Password`, `Name`, `Surname`, `Contact_phone`, DOB, Address, Email, Locale) VALUES (?,?,?,?,?,?,?,?,?,?);";
	public static final String INSERT_PAYMENT = "INSERT INTO payment (Balance, Status, End_date_service, Last_Date_of_withdrawal) VALUES (?,?,?,?);";
	public static final String INSERT_ADMINISTRATOR = "INSERT INTO administrator (Login, `Password`, `Contact_phone`, Email, Locale) VALUES (?,?,?,?,?);";
	public static final String INSERT_CONTRACT = "INSERT INTO contract (idAbonent, Number, `Start_date`, `End_date`) VALUES (?,?,?,?);";
	public static final String INSERT_SERVICE = "INSERT INTO service (`Name`) VALUES (?,?);";
	public static final String INSERT_TARIFF = "INSERT INTO tariff (idService, `Name`, `Price`, `Description`) VALUES (?,?,?,?);";
	public static final String INSERT_TARIFF_CONTRACT = "INSERT INTO tariffcontract VALUES (?,?);";
	public static final String INSERT_ACTION = "INSERT INTO action (idPayment, Event, Date, Changes) VALUES (?,?,?,?);";

	public static final String DELETE_ABONENT = "DELETE FROM abonent where idAbonent = ?;";
	public static final String DELETE_PAYMENT = "DELETE FROM payment where idPayment = ?;";
	public static final String DELETE_ADMINISTRATOR = "DELETE FROM administrator where idAdministrator = ?;";
	public static final String DELETE_CONTRACT = "DELETE FROM contract where idContract = ?;";
	public static final String DELETE_SERVICE = "DELETE FROM service where idService = ?;";
	public static final String DELETE_TARIFF = "DELETE FROM tariff where idTariff = ?;";
	public static final String DELETE_TARIFF_FROM_CONTRACT = "DELETE FROM tariffcontract where idTariff = ? AND idContract = ?;";
	
	public static final String UPDATE_TARIFF = "UPDATE tariff SET Name = ?, Price = ?, Description = ? WHERE idTariff = ?;";
	public static final String UPDATE_PAYMENT = "UPDATE payment SET Balance = ?, Status = ?, End_date_service = ?, Last_Date_of_withdrawal = ? WHERE idPayment = ?;";
	public static final String UPDATE_ABONENT = "UPDATE abonent SET Email = ?, Contact_phone = ?, DOB = ?, Address = ? WHERE idAbonent = ?;";
	public static final String UPDATE_ABONENT_LOCALE = "UPDATE abonent SET Locale = ? WHERE idAbonent = ?;";
	public static final String UPDATE_ADMINISTRATOR_LOCALE = "UPDATE administrator SET Locale = ? WHERE idAdministrator = ?;";
	public static final String UPDATE_PAYMENT_DATE = "UPDATE payment SET End_date_service = ?;";
	public static final String UPDATE_PAYMENT_DATE_BY_ID = "UPDATE payment SET End_date_service = ? WHERE idPayment = ?;";
	public static final String UPDATE_PAYMENT_STATUS = "UPDATE payment SET Status = ? where idPayment = ?;";
	public static final String UPDATE_PAYMENT_WITHDRAWAL_DATE = "UPDATE payment SET Last_Date_of_withdrawal = ?, Balance = ? WHERE idPayment = ?;";
	public static final String UPDATE_ABONENT_PASSWORD = "UPDATE abonent SET password = ? WHERE idAbonent = ?;";

	public static final String CHECK_LOGIN = "(SELECT Login FROM administrator) UNION (SELECT Login FROM abonent);";
	public static final String CHECK_CONTRACT = "SELECT number FROM contract";
	public static final String CHECK_EMAIL = "SELECT Email FROM abonent;";
	public static final String CHECK_TARIFF_NAME = "SELECT Name FROM tariff;";
	public static final String CHECK_SERVICE_BY_ID_CONTRACT = "SELECT idService FROM service WHERE idService IN (SELECT idService FROM Tariff WHERE idTariff IN (SELECT idTariff from tariffcontract WHERE idContract = ?));";

	public static final String LOGIN_PASSWORD_AUTHENTICATION_ABONENT = "SELECT login, password FROM Abonent";
	public static final String LOGIN_PASSWORD_AUTHENTICATION_ADMIN = "SELECT login, password FROM Administrator";

	public static final String CALLABLE_QUERY = "{call addDate(?, ?, ?)}";
}
