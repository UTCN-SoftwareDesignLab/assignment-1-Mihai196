package service.bill;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.DBConnectionFactory;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.bill.BillRepository;
import repository.bill.BillRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.client.ClientService;
import service.client.ClientServiceImpl;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;

public class BillServiceTest {

	private static AuthenticationService authenticationService;
    private static UserRepository userRepository;
    private static ClientRepository clientRepository;
    private static ClientService clientService;
    private static AccountRepository accountRepository;
    private static AccountService accountService;
    private static BillRepository billRepository;
    private static BillService billService;
    private static String type="spending";
    private static double balance=1000;
    private static int clientId=1;
    private static double sumToPay=20;
    private static String company="Electrica";
    private static String NAME="Gigel";
    private static String ADDRESS="obs";
    private static long idCardNr=1234;
    private static long PNC=1234567890;
    @BeforeClass
    public static void setUp() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);

        authenticationService = new AuthenticationServiceMySQL(
                userRepository,
                rightsRolesRepository
        );
        clientRepository=new ClientRepositoryMySQL(connection);
        clientService=new ClientServiceImpl(clientRepository);
        accountRepository=new AccountRepositoryMySQL(connection);
        accountService= new AccountServiceImpl(accountRepository);
        billRepository=new BillRepositoryMySQL(connection);
        billService=new BillServiceImpl(billRepository,accountRepository);
    }
    @Before
    public void removeAll()
    {
    	clientRepository.removeAll();
    	accountRepository.removeAll();
    	billRepository.removeAll();
    	clientService.addClient(NAME, idCardNr, PNC, ADDRESS);
    	billService.addBill(sumToPay, company, clientId);
    	accountService.addAccount(type, balance, clientId);
    }
	@Test
	public void testAddBill() {
		Assert.assertTrue(billService.addBill(sumToPay, company, clientId).getResult());
	}
	@Test
	public void processBill()
	{
		Assert.assertTrue(billService.processBill(1, 1).getResult());
	}

}
