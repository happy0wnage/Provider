package ua.nure.petrov.SummaryTask4;

/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author Vladyslav Petrov
 * 
 */
public final class Path {
	
	// pages
	public static final String PAGE_LOGIN = "/index.jsp";
	public static final String PAGE_RELOGIN = "/WEB-INF/jsp/login.jsp";
	public static final String PAGE_REGISTER = "/WEB-INF/jsp/register.jsp";
	public static final String PAGE_CHANGE = "/WEB-INF/jsp/changeTariff.jsp";
	public static final String PAGE_CONNECTION = "/WEB-INF/jsp/connection.jsp";
	public static final String PAGE_CATALOG = "/WEB-INF/jsp/servicesCatalog.jsp";
	public static final String PAGE_ABOUT = "/WEB-INF/jsp/about.jsp";
	
	public static final String PAGE_ABONENT_PROFILE = "/WEB-INF/jsp/abonentProfile.jsp";
	public static final String PAGE_ABONENT_ACCOUNT = "/WEB-INF/jsp/abonentAccount.jsp";
	//db pages
	public static final String PAGE_TARIFF = "/WEB-INF/jsp/tariff.jsp";
	public static final String PAGE_CONTRACTS = "/WEB-INF/jsp/contract.jsp";
	public static final String PAGE_ABONENTS = "/WEB-INF/jsp/abonents.jsp";
	public static final String PAGE_SERVICES = "/WEB-INF/jsp/services.jsp";
	
	public static final String COMMAND_CONTRACT = "/controller?command=contract";
	public static final String COMMAND_RELOGIN = "/controller?command=accountFix";
	public static final String COMMAND_ABONENT = "/controller?command=abonent";
	public static final String COMMAND_SERVICE = "/controller?command=service";
	public static final String COMMAND_CONNECTION = "/controller?command=connection";
	public static final String COMMAND_MODIFY = "/controller?command=changeTariff";
	public static final String COMMAND_TARIFF = "/controller?command=tariff";
	public static final String COMMAND_ADMIN = "/controller?command=admin";
	public static final String COMMAND_ABONENT_PROFILE = "/controller?command=abonent_profile";
	public static final String COMMAND_ABONENT_ACCOUNT = "/controller?command=abonent_account";
	public static final String COMMAND_REGISTER_PAGE = "/controller?command=registerPage";

}