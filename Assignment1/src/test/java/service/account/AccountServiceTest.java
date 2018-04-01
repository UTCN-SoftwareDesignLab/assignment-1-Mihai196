package service.account;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.DBConnectionFactory;
import model.Account;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.client.ClientService;
import service.client.ClientServiceImpl;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;

public class AccountServiceTest {

	private static AuthenticationService authenticationService;
    private static UserRepository userRepository;
    private static ClientRepository clientRepository;
    private static ClientService clientService;
    private static AccountRepository accountRepository;
    private static AccountService accountService;
    private static String type="spending";
    private static double balance=150;
    private static int clientId=1;
    private static double newbalance=100;
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
    }
    @Before
    public void removeAll()
    {
    	accountRepository.removeAll();
    	accountService.addAccount(type, balance, clientId);
    	accountService.addAccount(type, balance, clientId);
    }
	@Test
	public void testAddAccount() {
		Assert.assertTrue(accountService.addAccount(type, balance, clientId).getResult());
	}
	@Test
	public void testTransferAccount()
	{
		accountService.transferMoney(1, 2, 20);
		Account a=accountService.findById(1);
		Assert.assertTrue(130==a.getBalance());
	}
	@Test
	public void testDeleteAccount()
	{
		Assert.assertTrue(accountService.deleteAccount(1));
	}

}
