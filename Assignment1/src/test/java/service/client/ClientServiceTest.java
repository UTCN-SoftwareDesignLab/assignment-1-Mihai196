package service.client;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.DBConnectionFactory;
import model.Client;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;

public class ClientServiceTest {

	private static AuthenticationService authenticationService;
    private static UserRepository userRepository;
    private static ClientRepository clientRepository;
    private static ClientService clientService;
    private static String NAME="Gigel";
    private static String ADDRESS="obs";
    private static long idCardNr=1234;
    private static long PNC=1234567890;
    private static String UPDATEDADDRESS="Hotarului";
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
    }
	@Before
	public void removeAll()
	{
		clientRepository.removeAll();
	}
	@Test
	public void testAddClient() {
		Assert.assertTrue(clientService.addClient(NAME, idCardNr, PNC, ADDRESS).getResult());
	}
	@Test
	public void testUpdateClient()
	{
		Assert.assertTrue(clientService.updateClient(1, NAME, idCardNr, PNC, UPDATEDADDRESS).getResult());
	}
	@Test
	public void testDeleteClient()
	{
		Assert.assertTrue(clientService.deleteClient(1));
	}

}
