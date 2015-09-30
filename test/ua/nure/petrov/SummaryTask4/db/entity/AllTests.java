package ua.nure.petrov.SummaryTask4.db.entity;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AbonentTest.class, AdministratorTest.class, ContractTest.class, PaymentTest.class, ServiceTest.class, TariffContractTest.class,
		TariffTest.class })
public class AllTests {

}
