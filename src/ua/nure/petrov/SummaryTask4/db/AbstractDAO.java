package ua.nure.petrov.SummaryTask4.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.db.entity.Abonent;
import ua.nure.petrov.SummaryTask4.db.entity.Administrator;
import ua.nure.petrov.SummaryTask4.db.entity.Contract;
import ua.nure.petrov.SummaryTask4.db.entity.Payment;
import ua.nure.petrov.SummaryTask4.db.entity.Service;
import ua.nure.petrov.SummaryTask4.db.entity.Tariff;
import ua.nure.petrov.SummaryTask4.db.entity.TariffContract;

public class AbstractDAO implements DAO {

	private static final Logger LOG = Logger.getLogger(AbstractDAO.class);

	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private CallableStatement cstm = null;
	private Connection con = null;
	private Abonent abonent = null;
	private Administrator admin = null;
	private Payment payment = null;
	private Contract contract = null;
	private Service service = null;
	private Tariff tariff = null;
	private TariffContract tariffContract = null;

	private List<Abonent> abonents = null;
	private List<Administrator> admins = null;
	private List<Tariff> tariffs = null;
	private List<Payment> payments = null;
	private List<Contract> contracts = null;
	private List<Service> services = null;
	private List<TariffContract> tariffContracts = null;

	@Override
	public List<Abonent> findAbonent() {
		abonents = new ArrayList<Abonent>();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_ABONENTS);
			rs = pst.executeQuery();
			while (rs.next()) {
				abonents.add(extractAbonent(rs));
			}
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return abonents;
	}

	public void addDatePaymentById(int days, Payment payment) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			cstm = con.prepareCall(Query.CALLABLE_QUERY);
			cstm.setInt(k++, days);
			cstm.setDouble(k++, payment.getBalance());
			cstm.setInt(k++, payment.getId());
			cstm.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public List<Payment> findPayment() {
		payments = new ArrayList<Payment>();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_PAYMENT);
			rs = pst.executeQuery();
			while (rs.next()) {
				payments.add(extractPayment(rs));
			}
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return payments;
	}

	@Override
	public List<Administrator> findAdministrator() {
		admins = new ArrayList<Administrator>();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_ADMINISTRATOR);
			rs = pst.executeQuery();
			while (rs.next()) {
				admins.add(extractAdministrator(rs));
			}
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return admins;
	}

	@Override
	public List<Contract> findContract() {
		contracts = new ArrayList<Contract>();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_CONTRACT);
			rs = pst.executeQuery();
			while (rs.next()) {
				contracts.add(extractContract(rs));
			}
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return contracts;
	}

	@Override
	public Contract findContractById(int id) {
		contract = new Contract();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_CONTRACT_BY_ID);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			rs.relative(1);
			contract = extractContract(rs);
			
			return contract;
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return null;
	}

	@Override
	public List<TariffContract> findTariffContract() {
		tariffContracts = new ArrayList<TariffContract>();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_TARIFF_CONTRACT);
			rs = pst.executeQuery();
			while (rs.next()) {
				tariffContracts.add(extractTariffContract(rs));
			}
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return tariffContracts;
	}

	@Override
	public List<TariffContract> findTariffContractByIdContract(Contract contract) {
		tariffContracts = new ArrayList<TariffContract>();

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con
					.prepareStatement(Query.FIND_TARIFF_CONTRACT_BY_ID_CONTRACT);
			pst.setInt(k++, contract.getId());
			rs = pst.executeQuery();
			while (rs.next()) {
				tariffContracts.add(extractTariffContract(rs));
			}
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return tariffContracts;
	}

	@Override
	public List<TariffContract> findTariffContractByIdTariff(Tariff tariff) {
		tariffContracts = new ArrayList<TariffContract>();

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.FIND_TARIFF_CONTRACT_BY_ID_TARIFF);
			pst.setInt(k++, tariff.getId());
			rs = pst.executeQuery();
			while (rs.next()) {
				tariffContracts.add(extractTariffContract(rs));
			}
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return tariffContracts;
	}

	@Override
	public List<Service> findService() {
		services = new ArrayList<Service>();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_SERVICE);
			rs = pst.executeQuery();
			while (rs.next()) {
				services.add(extractService(rs));
			}
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return services;
	}

	@Override
	public List<Tariff> findTariff() {
		tariffs = new ArrayList<Tariff>();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_TARIFF);
			rs = pst.executeQuery();
			while (rs.next()) {
				tariffs.add(extractTariff(rs));
			}
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return tariffs;
	}

	@Override
	public Tariff findTariffById(int id) {
		tariff = new Tariff();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_TARIFF_BY_ID);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			rs.relative(1);
			tariff = extractTariff(rs);
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return tariff;
	}

	@Override
	public List<Tariff> findTariffsByIdService(int idService) {
		tariffs = new ArrayList<Tariff>();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_TARIFF_BY_ID_SERVICE);
			pst.setInt(1, idService);
			rs = pst.executeQuery();
			while (rs.next()) {
				tariffs.add(extractTariff(rs));
			}
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return tariffs;
	}

	@Override
	public Contract findContractByIdAbonent(Abonent abonent) {
		contract = new Contract();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_CONTRACT_BY_ID_ABONENT);
			pst.setInt(1, abonent.getId());
			rs = pst.executeQuery();
			rs.relative(1);
			contract = extractContract(rs);
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return contract;
	}

	@Override
	public List<Tariff> findTariffsByIdContract(Contract contract) {

		tariffs = new ArrayList<Tariff>();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_TARIFFS_BY_ID_CONTRACT);
			pst.setInt(1, contract.getId());
			rs = pst.executeQuery();
			while (rs.next()) {
				tariffs.add(extractTariff(rs));
			}
			contract = extractContract(rs);
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return tariffs;
	}

	@Override
	public Abonent findAbonentByLogin(String login) {

		abonent = new Abonent();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_ABONENT_BY_LOGIN);
			pst.setString(1, login);
			rs = pst.executeQuery();
			rs.relative(1);
			abonent = extractAbonent(rs);
			
			return abonent;
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return null;
	}

	@Override
	public Abonent findAbonentByIdPayment(Payment payment) {

		abonent = new Abonent();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_ABONENT_BY_ID_PAYMENT);
			pst.setInt(1, payment.getId());
			rs = pst.executeQuery();
			rs.relative(1);
			abonent = extractAbonent(rs);
			
			return abonent;
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return null;
	}

	@Override
	public List<Abonent> findAbonentsByLogin(String login) {

		abonents = new ArrayList<Abonent>();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_ABONENTS_BY_LOGIN);
			pst.setString(1, "%" + login + "%");
			rs = pst.executeQuery();
			while (rs.next()) {
				abonents.add(extractAbonent(rs));
			}
			
			return abonents;
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return null;
	}

	@Override
	public Abonent findAbonentById(int id) {

		abonent = new Abonent();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_ABONENT_BY_ID);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			rs.relative(1);
			abonent = extractAbonent(rs);
			
			return abonent;
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return null;
	}

	@Override
	public List<Abonent> findAbonentsById(int id) {

		abonents = new ArrayList<Abonent>();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_ABONENT_BY_ID);
			pst.setString(1, "%" + id + "%");
			rs = pst.executeQuery();

			while (rs.next()) {
				abonents.add(extractAbonent(rs));
			}
			
			return abonents;
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return null;
	}

	@Override
	public Administrator findAdminByLogin(String login) {
		admin = new Administrator();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_ADMIN_BY_LOGIN);
			pst.setString(1, login);
			rs = pst.executeQuery();
			rs.relative(1);
			admin = extractAdministrator(rs);
			
			return admin;
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return null;
	}

	@Override
	public Payment findPaymentById(int id) {

		payment = new Payment();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_PAYMENT_BY_ID);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			rs.relative(1);
			payment = extractPayment(rs);
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return payment;

	}

	@Override
	public Payment findPaymentByContract(Contract contract) {

		payment = new Payment();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_PAYMENT_BY_CONTRACT);
			pst.setInt(1, contract.getId());
			rs = pst.executeQuery();
			rs.relative(1);
			payment = extractPayment(rs);
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return payment;

	}

	@Override
	public Service findServiceById(int id) {

		service = new Service();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_SERVICE_BY_ID);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			rs.relative(1);
			service = extractService(rs);
			
			return service;
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return null;
	}

	public boolean checkContractNumber(int number) {

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.CHECK_CONTRACT);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (number == rs.getInt("Number")) {
					
					return false;
				}
			}
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return true;
	}

	public boolean checkLogin(String login) {

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.CHECK_LOGIN);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (login.equalsIgnoreCase(rs.getString("Login"))) {
					return false;
				}
			}
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return true;
	}

	@Override
	public boolean checkServiceByContract(Service service) {

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.CHECK_SERVICE_BY_ID_CONTRACT);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (service.getId() == rs.getInt("idservice")) {
					
					return false;
				}
			}
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return true;
	}

	public boolean checkTariffName(String name) {

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.CHECK_TARIFF_NAME);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (name.equalsIgnoreCase(rs.getString("Name"))) {
					
					return false;
				}
			}
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return true;
	}

	public boolean checkEmail(String email) {

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.CHECK_EMAIL);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (email.equalsIgnoreCase(rs.getString("email"))) {
					return false;
				}
			}
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return true;
	}

	public boolean checkLoginPassword(String login, String password) {

		try {
			con = MySQLConnection.getWebConnection();
			pst = con
					.prepareStatement(Query.LOGIN_PASSWORD_AUTHENTICATION_ABONENT);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (login.equalsIgnoreCase(rs.getString("login"))
						&& password.equalsIgnoreCase(rs.getString("password"))) {
					
					return true;
				}
			}
			pst = con
					.prepareStatement(Query.LOGIN_PASSWORD_AUTHENTICATION_ADMIN);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (login.equalsIgnoreCase(rs.getString("login"))
						&& password.equalsIgnoreCase(rs.getString("password"))) {
					
					return true;
				}
			}
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return false;
	}

	@Override
	public Contract findContractByNumber(int number) {
		contract = new Contract();

		try {
			con = MySQLConnection.getWebConnection();
			pst = con.prepareStatement(Query.FIND_CONTRACT_BY_NUMBER);
			pst.setInt(1, number);
			rs = pst.executeQuery();
			rs.relative(1);
			contract = extractContract(rs);
			
			return contract;
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return null;
	}

	@Override
	public void insertAbonent(Abonent abonent) {

		if (checkLogin(abonent.getLogin())) {
			con = MySQLConnection.getWebConnection();
			try {
				int k = 1;
				pst = con.prepareStatement(Query.INSERT_ABONENT);
				pst.setInt(k++, abonent.getIdPayment());
				pst.setString(k++, abonent.getLogin());
				pst.setString(k++, abonent.getPassword());
				pst.setString(k++, abonent.getName());
				pst.setString(k++, abonent.getSurname());
				pst.setString(k++, abonent.getContactPhone());
				pst.setDate(k++, (Date) abonent.getDob());
				pst.setString(k++, abonent.getAddress());
				pst.setString(k++, abonent.getEmail());
				pst.setString(k++, abonent.getLocale());
				pst.executeUpdate();
				
			} catch (SQLException ex) {
				rollback(con);
			} finally {
				commit(con);
			}
		} else {
			LOG.debug("Abonent is already exists");
		}
	}
	
	@Override
	public void insertAbonentPayment(Abonent abonent, Payment payment) {

		if (checkLogin(abonent.getLogin())) {
			con = MySQLConnection.getWebConnection();
			try {
				int k = 1;
				String generatedColumns[] = { Fields.PAYMENT_ID };
				pst = con.prepareStatement(Query.INSERT_PAYMENT, generatedColumns);
				pst.setDouble(k++, payment.getBalance());
				pst.setBoolean(k++, payment.getStatus());
				pst.setDate(k++, (Date) payment.getEndDateService());
				pst.setDate(k++, (Date) payment.getLastDateOfWithdrawal());
				pst.executeUpdate();

				ResultSet rs = pst.getGeneratedKeys();
				rs.next();
				int orderId = rs.getInt(1);
				payment.setId(orderId);
				
				k = 1;
				pst = con.prepareStatement(Query.INSERT_ABONENT);
				pst.setInt(k++, payment.getId());
				pst.setString(k++, abonent.getLogin());
				pst.setString(k++, abonent.getPassword());
				pst.setString(k++, abonent.getName());
				pst.setString(k++, abonent.getSurname());
				pst.setString(k++, abonent.getContactPhone());
				pst.setDate(k++, (Date) abonent.getDob());
				pst.setString(k++, abonent.getAddress());
				pst.setString(k++, abonent.getEmail());
				pst.setString(k++, abonent.getLocale());
				pst.executeUpdate();
				
			} catch (SQLException ex) {
				rollback(con);
			} finally {
				commit(con);
			}
		} else {
			LOG.debug("Abonent is already exists");
		}
	}

	@Override
	public Payment insertPayment(Payment payment) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			String generatedColumns[] = { Fields.PAYMENT_ID };
			pst = con.prepareStatement(Query.INSERT_PAYMENT, generatedColumns);
			pst.setDouble(k++, payment.getBalance());
			pst.setBoolean(k++, payment.getStatus());
			pst.setDate(k++, (Date) payment.getEndDateService());
			pst.setDate(k++, (Date) payment.getLastDateOfWithdrawal());
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			int orderId = rs.getInt(1);
			payment.setId(orderId);
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
		return payment;
	}

	@Override
	public void insertAdministator(Administrator admin) {

		if (checkLogin(admin.getLogin())) {
			con = MySQLConnection.getWebConnection();
			try {
				int k = 1;
				pst = con.prepareStatement(Query.INSERT_ADMINISTRATOR);
				pst.setString(k++, admin.getLogin());
				pst.setString(k++, admin.getPassword());
				pst.setString(k++, admin.getContactPhone());
				pst.setString(k++, admin.getEmail());
				pst.setString(k++, admin.getLocale());
				pst.executeUpdate();
				
			} catch (SQLException ex) {
				rollback(con);
			} finally {
				commit(con);
			}
		} else {
			LOG.debug("Administrator already exists");
		}
	}

	@Override
	public void insertContract(Contract contract) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.INSERT_CONTRACT);
			pst.setInt(k++, contract.getIdAbonent());
			pst.setInt(k++, contract.getNumber());
			pst.setDate(k++, (Date) contract.getStartDate());
			pst.setDate(k++, (Date) contract.getEndDate());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void insertService(Service service) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.INSERT_SERVICE);
			pst.setString(k++, service.getName());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}

	}

	@Override
	public void insertTariff(Tariff tariff) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.INSERT_TARIFF);
			pst.setInt(k++, tariff.getIdService());
			pst.setString(k++, tariff.getName());
			pst.setDouble(k++, tariff.getPrice());
			pst.setString(k++, tariff.getDescription());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}

	}

	@Override
	public void insertTariffContract(TariffContract tc) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.INSERT_TARIFF_CONTRACT);
			pst.setInt(k++, tc.getIdTariff());
			pst.setInt(k++, tc.getIdContract());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	private void commit(Connection con) {
		if (con != null) {
			try {
				con.commit();
				con.close();
			} catch (SQLException ex) {
				LOG.error("Commit: " + ex.getMessage());
			}
		}
	}

	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				LOG.error("RollBack: " + ex.getMessage());
			}
		}
	}

	private Abonent extractAbonent(ResultSet rs) throws SQLException {
		abonent = new Abonent();
		abonent.setId(rs.getInt(Fields.ABONENT_ID));
		abonent.setIdPayment(rs.getInt(Fields.ABONENT_ID_PAYMENT));
		abonent.setLogin(rs.getString(Fields.ABONENT_LOGIN));
		abonent.setPassword(rs.getString(Fields.ABONENT_PASSWORD));
		abonent.setName(rs.getString(Fields.ABONENT_FIRST_NAME));
		abonent.setSurname(rs.getString(Fields.ABONENT_SURNAME));
		abonent.setContactPhone(rs.getString(Fields.ABONENT_PHONE));
		abonent.setDob(rs.getString(Fields.ABONENT_DOB));
		abonent.setAddress(rs.getString(Fields.ABONENT_ADDRESS));
		abonent.setEmail(rs.getString(Fields.ABONENT_EMAIL));
		abonent.setLocale(rs.getString(Fields.ABONENT_LOCALE));
		return abonent;
	}

	private Payment extractPayment(ResultSet rs) throws SQLException {
		payment = new Payment();
		payment.setId(rs.getInt(Fields.PAYMENT_ID));
		payment.setBalance(rs.getDouble(Fields.PAYMENT_BALANCE));
		payment.setStatus(rs.getBoolean(Fields.PAYMENT_STATUS));
		payment.setEndDateService(rs.getString(Fields.PAYMENT_END_DATE_SERVICE));
		payment.setLastDateOfWithdrawal(rs
				.getString(Fields.PAYMENT_LAST_DATE_WITHDRAWAL));
		return payment;
	}

	private Administrator extractAdministrator(ResultSet rs)
			throws SQLException {
		admin = new Administrator();
		admin.setId(rs.getInt(Fields.ADMINISTRATOR_ID));
		admin.setLogin(rs.getString(Fields.ADMINISTRATOR_LOGIN));
		admin.setPassword(rs.getString(Fields.ADMINISTRATOR_PASSWORD));
		admin.setContactPhone(rs.getString(Fields.ADMINISTRATOR_PHONE));
		admin.setEmail(rs.getString(Fields.ADMINISTRATOR_EMAIL));
		admin.setLocale(rs.getString(Fields.ADMINISTRATOR_LOCALE));
		return admin;
	}

	private Contract extractContract(ResultSet rs) throws SQLException {
		contract = new Contract();
		contract.setId(rs.getInt(Fields.CONTRACT_ID));
		contract.setIdAbonent(rs.getInt(Fields.CONTRACT_ABONENT_ID));
		contract.setNumber(rs.getInt(Fields.CONTRACT_NUMBER));
		contract.setStartDate(rs.getString(Fields.CONTRACT_START_DATE));
		contract.setEndDate(rs.getString(Fields.CONTRACT_END_DATE));
		return contract;
	}

	private Service extractService(ResultSet rs) throws SQLException {
		service = new Service();
		service.setId(rs.getInt(Fields.SERVICE_ID));
		service.setName(rs.getString(Fields.SERVICE_NAME));
		return service;
	}

	private Tariff extractTariff(ResultSet rs) throws SQLException {
		tariff = new Tariff();
		tariff.setId(rs.getInt(Fields.TARIFF_ID));
		tariff.setIdService(rs.getInt(Fields.TARIFF_SERVICE_ID));
		tariff.setName(rs.getString(Fields.TARIFF_NAME));
		tariff.setPrice(rs.getDouble(Fields.TARIFF_PRICE));
		tariff.setDescription(rs.getString(Fields.TARIFF_DESCRIPTION));
		return tariff;
	}

	private TariffContract extractTariffContract(ResultSet rs)
			throws SQLException {
		tariffContract = new TariffContract();
		tariffContract.setIdTariff(rs.getInt(Fields.TARIFFCONTRACT_ID_TARIFF));
		tariffContract.setIdContract(rs
				.getInt(Fields.TARIFFCONTRACT_ID_CONTRACT));
		return tariffContract;
	}

	@Override
	public void deleteAbonentById(int id) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.DELETE_ABONENT);
			pst.setInt(k++, id);
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void deleteTariffFromContractById(Tariff tariff, Contract contract) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.DELETE_TARIFF_FROM_CONTRACT);
			pst.setInt(k++, tariff.getId());
			pst.setInt(k++, contract.getId());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void deletePaymentById(int id) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.DELETE_PAYMENT);
			pst.setInt(k++, id);
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void deleteAdministratorById(int id) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.DELETE_ADMINISTRATOR);
			pst.setInt(k++, id);
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void deleteContractById(int id) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.DELETE_CONTRACT);
			pst.setInt(k++, id);
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void deleteServiceById(int id) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.DELETE_SERVICE);
			pst.setInt(k++, id);
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void deleteTariffById(int id) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.DELETE_TARIFF);
			pst.setInt(k++, id);
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void updateTariff(Tariff tariff) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.UPDATE_TARIFF);
			pst.setString(k++, tariff.getName());
			pst.setDouble(k++, tariff.getPrice());
			pst.setString(k++, tariff.getDescription());
			pst.setInt(k++, tariff.getId());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void updateAbonent(Abonent abonent) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.UPDATE_ABONENT);
			pst.setString(k++, abonent.getEmail());
			pst.setString(k++, abonent.getContactPhone());
			pst.setDate(k++, abonent.getDob());
			pst.setString(k++, abonent.getAddress());
			pst.setInt(k++, abonent.getId());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void updateAbonentLocale(Abonent abonent) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.UPDATE_ABONENT_LOCALE);
			pst.setString(k++, abonent.getLocale());
			pst.setInt(k++, abonent.getId());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void updateAdminLocale(Administrator admin) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.UPDATE_ADMINISTRATOR_LOCALE);
			pst.setString(k++, admin.getLocale());
			pst.setInt(k++, admin.getId());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void updateAbonentPassword(Abonent abonent) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.UPDATE_ABONENT_PASSWORD);
			pst.setString(k++, abonent.getPassword());
			pst.setInt(k++, abonent.getId());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void updatePaymentDate(Payment payment) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.UPDATE_PAYMENT_DATE_BY_ID);
			pst.setDate(k++, (Date) payment.getEndDateService());
			pst.setInt(k++, payment.getId());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void updatePayment(Payment payment) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.UPDATE_PAYMENT);
			pst.setDouble(k++, payment.getBalance());
			pst.setBoolean(k++, payment.getStatus());
			pst.setDate(k++, (Date) payment.getEndDateService());
			pst.setDate(k++, (Date) payment.getLastDateOfWithdrawal());
			pst.setInt(k++, payment.getId());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void updatePaymentWithdrawalDate(Payment payment) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.UPDATE_PAYMENT_WITHDRAWAL_DATE);
			pst.setDate(k++, (Date) payment.getLastDateOfWithdrawal());
			pst.setDouble(k++, payment.getBalance());
			pst.setInt(k++, payment.getId());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

	@Override
	public void updatePaymentStatus(Payment payment) {

		try {
			con = MySQLConnection.getWebConnection();
			int k = 1;
			pst = con.prepareStatement(Query.UPDATE_PAYMENT_STATUS);
			pst.setBoolean(k++, payment.getStatus());
			pst.setInt(k++, payment.getId());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			rollback(con);
		} finally {
			commit(con);
		}
	}

}
