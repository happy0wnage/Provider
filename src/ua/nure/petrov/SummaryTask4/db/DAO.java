package ua.nure.petrov.SummaryTask4.db;


import java.util.List;

import ua.nure.petrov.SummaryTask4.db.entity.*;

interface DAO {

	//find
	List<Abonent> findAbonent();
	
	List<Payment> findPayment();

	List<Administrator> findAdministrator();

	List<Contract> findContract();

	List<Service> findService();

	List<Tariff> findTariff();
	
	List<TariffContract> findTariffContract();
	
	List<Tariff> findTariffsByIdContract(Contract contract);
	
	List<Tariff> findTariffsByIdService(int idService);
	
	List<TariffContract> findTariffContractByIdContract(Contract contract);
	
	List<TariffContract> findTariffContractByIdTariff(Tariff tariff);
	
	Payment findPaymentByContract(Contract contract);
	
	Contract findContractById(int id);

	Abonent findAbonentById(int id);
	
	List<Abonent> findAbonentsById(int id);
	
	Abonent findAbonentByLogin(String login);
	
	List<Abonent> findAbonentsByLogin(String login);

	Administrator findAdminByLogin(String login);

	Contract findContractByNumber(int number);

	Tariff findTariffById(int id);
	
	Contract findContractByIdAbonent(Abonent abonent);
	
	Abonent findAbonentByIdPayment(Payment payment);
	
	Payment findPaymentById(int id);
	
	Service findServiceById(int id);
	
	//insert
	void insertAbonent(Abonent abonent);
	
	void insertAdministator(Administrator admin);
	
	Payment insertPayment(Payment payment);
	
	void insertContract(Contract contract);
	
	void insertService(Service service);
	
	void insertTariff(Tariff tariff);
	
	void insertTariffContract(TariffContract tc);
	
	void insertAbonentPayment(Abonent abonent, Payment payment);
	//delete
	void deleteAbonentById(int id);
	
	void deleteAdministratorById(int id);
	
	void deletePaymentById(int id);
	
	void deleteContractById(int id);
	
	void deleteServiceById(int id);
	
	void deleteTariffById(int id);
	
	void deleteTariffFromContractById(Tariff tariff, Contract contract);
	
	//modify
	void updateTariff(Tariff tariff);
	
	void updateAbonent(Abonent abonent);
	
	void updatePayment(Payment payment);
	
	void updatePaymentStatus(Payment payment);
	
	void updatePaymentWithdrawalDate(Payment payment);
	
	void updateAbonentPassword(Abonent abonent);
	
	void updateAbonentLocale(Abonent abonent);
	
	void updatePaymentDate(Payment payment);
	
	void updateAdminLocale(Administrator admin);
	
	//check
	boolean checkLogin(String login);
	
	boolean checkEmail(String email);
	
	boolean checkLoginPassword(String login, String password);
	
	boolean checkServiceByContract(Service service);
}
