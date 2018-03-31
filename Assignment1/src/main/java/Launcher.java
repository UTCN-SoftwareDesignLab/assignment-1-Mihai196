import controller.AdministratorController;
import controller.LoginController;
import controller.RegularUserController;
import view.AdministratorView;
import view.LoginView;
import view.RegularUserView;

/**
 * Created by Alex on 18/03/2017.
 */
public class Launcher {

    public static void main(String[] args) {
        ComponentFactory componentFactory = ComponentFactory.instance(false);
        LoginView loginView=new LoginView();
        new LoginController(loginView, componentFactory.getAuthenticationService(),
        		new RegularUserController(componentFactory.getClientService(),componentFactory.getAccService(),new RegularUserView(),loginView,componentFactory.getActivityService(),componentFactory.getUserService(),componentFactory.getBillService()),
        		new AdministratorController(componentFactory.getAuthenticationService(),componentFactory.getUserService(),new AdministratorView(),loginView,componentFactory.getActivityService()));
    }

}
