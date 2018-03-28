import controller.AdministratorController;
import controller.LoginController;
import controller.RegularUserController;
import view.LoginView;

/**
 * Created by Alex on 18/03/2017.
 */
public class Launcher {

    public static void main(String[] args) {
        ComponentFactory componentFactory = ComponentFactory.instance(false);
        new LoginController(new LoginView(), componentFactory.getAuthenticationService(),new RegularUserController(componentFactory.getClientService(),componentFactory.getAccService()),
        		new AdministratorController(componentFactory.getAuthenticationService()));
    }

}
