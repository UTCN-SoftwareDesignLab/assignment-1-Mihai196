import database.DBConnectionFactory;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.activity.ActivityRepository;
import repository.activity.ActivityRepositoryMySQL;
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
import service.activity.ActivityService;
import service.activity.ActivityServiceImpl;
import service.bill.BillService;
import service.bill.BillServiceImpl;
import service.client.ClientService;
import service.client.ClientServiceImpl;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;
import service.user.UserService;
import service.user.UserServiceImpl;

import java.sql.Connection;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    
    private final ClientService clientService;
    private final ClientRepository clientRepository;
    
    private final AccountRepository accRepository;
    private final AccountService accService;

	private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    
    private final ActivityRepository activityRepository;
    private final ActivityService activityService;
    
    private final BillRepository billRepository;
	private final BillService billService;

	private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.userService = new UserServiceImpl(userRepository);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);
        this.clientRepository=new ClientRepositoryMySQL(connection);
        this.clientService=new ClientServiceImpl(clientRepository);
        this.accRepository=new AccountRepositoryMySQL(connection);
        this.accService=new AccountServiceImpl(accRepository);
        this.activityRepository=new ActivityRepositoryMySQL(connection);
        this.activityService=new ActivityServiceImpl(activityRepository);
        this.billRepository=new BillRepositoryMySQL(connection);
        this.billService=new BillServiceImpl(billRepository);
    }
    public ActivityService getActivityService() {
		return activityService;
	}

    public UserService getUserService() {
		return userService;
	}

	public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

	public ClientService getClientService() {
		return clientService;
	}

	public ClientRepository getClientRepository() {
		return clientRepository;
	}
	public AccountRepository getAccRepository() {
		return accRepository;
	}

	public AccountService getAccService() {
		return accService;
	}
	public BillService getBillService() {
		return billService;
	}

    
}
