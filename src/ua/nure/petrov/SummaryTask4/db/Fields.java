package ua.nure.petrov.SummaryTask4.db;

/**
 * Holder for fields names of DB tables and beans.
 * 
 * @author Vladyslav Petrov
 * 
 */
public final class Fields {
	
	public static final String ID = "id";
	
	public static final String ABONENT_ID = "idAbonent";
	public static final String ABONENT_ID_PAYMENT = "idPayment";
	public static final String ABONENT_LOGIN = "login";
	public static final String ABONENT_PASSWORD = "password";
	public static final String ABONENT_FIRST_NAME = "name";
	public static final String ABONENT_SURNAME = "surname";
	public static final String ABONENT_PHONE = "contact_phone";
	public static final String ABONENT_DOB = "dob";
	public static final String ABONENT_ADDRESS = "address";
	public static final String ABONENT_EMAIL = "email";
	public static final String ABONENT_LOCALE = "locale";
	
	public static final String PAYMENT_ID = "idPayment";
	public static final String PAYMENT_BALANCE = "balance";
	public static final String PAYMENT_STATUS = "status";
	public static final String PAYMENT_END_DATE_SERVICE = "end_date_service";
	public static final String PAYMENT_LAST_DATE_WITHDRAWAL = "last_Date_of_withdrawal";
	
	public static final String ADMINISTRATOR_ID = "idAdministrator";
	public static final String ADMINISTRATOR_LOGIN = "login";
	public static final String ADMINISTRATOR_PASSWORD = "password";
	public static final String ADMINISTRATOR_PHONE = "contact_phone";
	public static final String ADMINISTRATOR_EMAIL = "email";
	public static final String ADMINISTRATOR_LOCALE = "locale";
	
	public static final String CONTRACT_ID = "idContract";
	public static final String CONTRACT_ABONENT_ID = "idAbonent";
	public static final String CONTRACT_NUMBER = "number";
	public static final String CONTRACT_START_DATE = "start_date";
	public static final String CONTRACT_END_DATE = "end_date";
	
	public static final String SERVICE_ID = "idService";
	public static final String SERVICE_NAME = "name";
	
	public static final String TARIFF_ID = "idTariff";
	public static final String TARIFF_SERVICE_ID = "idService";
	public static final String TARIFF_NAME = "name";
	public static final String TARIFF_PRICE = "price";
	public static final String TARIFF_DESCRIPTION = "description";
	
	public static final String TARIFFCONTRACT_ID_TARIFF = "idTariff";
	public static final String TARIFFCONTRACT_ID_CONTRACT = "idContract";

	public static final String ACTION_ID_ACTION = "idAction";
	public static final String ACTION_ID_PAYMENT = "idPayment";
	public static final String ACTION_EVENT = "Event";
	public static final String ACTION_DATE = "Date";
	public static final String ACTION_CHANGE = "Changes";

	
	
	
}