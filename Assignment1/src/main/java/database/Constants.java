package database;

import java.util.*;

import static database.Constants.Rights.*;
import static database.Constants.Roles.*;

/**
 * Created by Alex on 11/03/2017.
 */
public class Constants {

    public static class Schemas {
        public static final String TEST = "test_library";
        public static final String PRODUCTION = "library";

        public static final String[] SCHEMAS = new String[]{TEST, PRODUCTION};
    }

    public static class Tables {
        //public static final String BOOK = "book";
        public static final String ACCOUNT="account";
        public static final String CLIENT="client";
        public static final String BILL="bill";
        public static final String ACTIVITY="activity";
        public static final String USER = "user";
        public static final String ROLE = "role";
        public static final String RIGHT = "right";
        public static final String ROLE_RIGHT = "role_right";
        public static final String USER_ROLE = "user_role";

        public static final String[] ORDERED_TABLES_FOR_CREATION = new String[]{USER, ROLE, RIGHT, ROLE_RIGHT, USER_ROLE,CLIENT,ACCOUNT,BILL,ACTIVITY};
    }

    public static class Roles {
        public static final String ADMINISTRATOR = "administrator";
        public static final String EMPLOYEE = "employee";
        public static final String CUSTOMER = "customer";

        public static final String[] ROLES = new String[]{ADMINISTRATOR, EMPLOYEE};
    }
    public static class Activities
    {
    	public static final String ADDUSER="addedUser";
    	public static final String REMOVEUSER="removedUser";
    	public static final String UPDATEUSER="updatedUser";
    	public static final String ADDCLIENT="addedClient";
    	public static final String REMOVECLIENT="removedClient";
    	public static final String UPDATEDCLIENT="updatedClient";
    	public static final String BILLPAYMENT="billProcessed";
    	public static final String MONEYTRANSFER="moneyTransfer";
    	public static final String ADDACCOUNT="addedAccount";
    	public static final String REMOVEACCOUNT="removedAccount";
    	public static final String UPDATEDACCOUNT="updatedAccount";
    }

    public static class Rights {
        public static final String CREATE_USER = "create_user";
        public static final String DELETE_USER = "delete_user";
        public static final String UPDATE_USER = "update_user";
        
        public static final String CREATE_CLIENT="create_client";
        public static final String DELETE_CLIENT="delete_client";
        public static final String UPDATE_CLIENT="update_client";
        public static final String VIEW_CLIENT="view_client";
        
        public static final String CREATE_ACCOUNT="create_account";
        public static final String DELETE_ACCOUNT="delete_account";
        public static final String UPDATE_ACCOUNT="update_account";
        public static final String VIEW_ACCOUNT="view_account";
        
        public static final String PROCESS_BILL="process_bill";
        public static final String TRANSFER_MONEY="transfer_money";
        public static final String GEN_REPORT="gen_report";

        /*public static final String SELL_BOOK = "sell_book";
        public static final String BUY_BOOK = "buy_book";
        public static final String RETURN_BOOK = "return_book";*/
        
       /* public static final String CREATE_BOOK = "create_book";
        public static final String DELETE_BOOK = "delete_book";
        public static final String UPDATE_BOOK = "update_book";*/

        public static final String[] RIGHTS = new String[]{CREATE_USER, DELETE_USER, UPDATE_USER,CREATE_ACCOUNT,DELETE_ACCOUNT,UPDATE_ACCOUNT,VIEW_ACCOUNT,CREATE_CLIENT,DELETE_CLIENT,UPDATE_CLIENT,VIEW_CLIENT,PROCESS_BILL,TRANSFER_MONEY,GEN_REPORT};
    }

    public static Map<String, List<String>> getRolesRights() {
        Map<String, List<String>> ROLES_RIGHTS = new HashMap<>();
        for (String role : ROLES) {
            ROLES_RIGHTS.put(role, new ArrayList<>());
        }
        ROLES_RIGHTS.get(ADMINISTRATOR).addAll(Arrays.asList(CREATE_USER, DELETE_USER, UPDATE_USER,GEN_REPORT));

        ROLES_RIGHTS.get(EMPLOYEE).addAll(Arrays.asList(CREATE_CLIENT,DELETE_CLIENT,UPDATE_CLIENT,VIEW_CLIENT,CREATE_ACCOUNT,DELETE_ACCOUNT,UPDATE_ACCOUNT,VIEW_ACCOUNT,TRANSFER_MONEY,PROCESS_BILL));

        return ROLES_RIGHTS;
    }

}
