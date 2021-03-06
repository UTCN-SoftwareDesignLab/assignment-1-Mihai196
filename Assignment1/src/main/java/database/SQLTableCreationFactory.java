package database;

import static database.Constants.Tables.*;

/**
 * Created by Alex on 11/03/2017.
 */
public class SQLTableCreationFactory {

    public String getCreateSQLForTable(String table) {
        switch (table) {
            /*case BOOK:
                return "CREATE TABLE IF NOT EXISTS book (" +
                        "  id int(11) NOT NULL AUTO_INCREMENT," +
                        "  author varchar(500) NOT NULL," +
                        "  title varchar(500) NOT NULL," +
                        "  publishedDate datetime DEFAULT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE KEY id_UNIQUE (id)" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";*/

            case USER:
                return "CREATE TABLE IF NOT EXISTS user (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  username VARCHAR(200) NOT NULL," +
                        "  password VARCHAR(64) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  UNIQUE INDEX username_UNIQUE (username ASC));";

            case ROLE:
                return "  CREATE TABLE IF NOT EXISTS role (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  UNIQUE INDEX role_UNIQUE (role ASC));";

            case RIGHT:
                return "  CREATE TABLE IF NOT EXISTS `right` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `right` VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (`id`)," +
                        "  UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                        "  UNIQUE INDEX `right_UNIQUE` (`right` ASC));";

            case ROLE_RIGHT:
                return "  CREATE TABLE IF NOT EXISTS role_right (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role_id INT NOT NULL," +
                        "  right_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  INDEX right_id_idx (right_id ASC)," +
                        "  CONSTRAINT role_id" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT right_id" +
                        "    FOREIGN KEY (right_id)" +
                        "    REFERENCES `right` (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

            case USER_ROLE:
                return "\tCREATE TABLE IF NOT EXISTS user_role (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  user_id INT NOT NULL," +
                        "  role_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX user_id_idx (user_id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  CONSTRAINT user_fkid" +
                        "    FOREIGN KEY (user_id)" +
                        "    REFERENCES user (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT role_fkid" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";
            case CLIENT:
            	return "CREATE TABLE IF NOT EXISTS client (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  name VARCHAR(200) NOT NULL," +
                        "  idCardNR INT NOT NULL," +
                        "  persNrCode INT NOT NULL,"+
                        "  address VARCHAR(200) NOT NULL,"+
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC));";
            case ACCOUNT:
            	return "\tCREATE TABLE IF NOT EXISTS account (" +
                "  id INT NOT NULL AUTO_INCREMENT," +
                "  type VARCHAR(200) NOT NULL," +
                "  balance DOUBLE NOT NULL," +
                "  dateOfCreation datetime DEFAULT NULL,"+
                "  clientId INT NOT NULL,"+
                "  PRIMARY KEY (id)," +
                "  CONSTRAINT client_fkid" +
                "    FOREIGN KEY (clientId)" +
                "    REFERENCES client (id)" +
                "    ON DELETE CASCADE" +
                "    ON UPDATE CASCADE);";
            
            case BILL:
            	return "\tCREATE TABLE IF NOT EXISTS bill ("+
            	" id INT NOT NULL AUTO_INCREMENT," +
            	" sumToPay DOUBLE NOT NULL,"+
            	" company VARCHAR(200) NOT NULL,"+
            	" clientId INT NOT NULL,"+
            	" PRIMARY KEY (id),"+
            	" CONSTRAINT client_fkidd" +
            	"	FOREIGN KEY(clientId)"+
            	" 	REFERENCES client (id)"+
            	" 	ON DELETE CASCADE"+
            	" 	ON UPDATE CASCADE);";
            case ACTIVITY:
            	return "\tCREATE TABLE IF NOT EXISTS activity ("+
            	" id INT NOT NULL AUTO_INCREMENT," +
            	" type VARCHAR(200) NOT NULL,"+
            	" dateOfPerforming datetime DEFAULT NULL,"+
            	" userId INT NOT NULL,"+
            	" PRIMARY KEY (id),"+
            	" CONSTRAINT user_fkidd" +
            	"	FOREIGN KEY(userId)"+
            	" 	REFERENCES `user` (id)"+
            	" 	ON DELETE CASCADE"+
            	" 	ON UPDATE CASCADE);";

            default:
                return "";

        }
    }

}
