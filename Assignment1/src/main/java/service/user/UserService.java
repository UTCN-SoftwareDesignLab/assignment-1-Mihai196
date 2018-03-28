package service.user;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.User;

public interface UserService {
	
	List<User> findAll();

	boolean save(User user);

	void removeAll();

	boolean removeUser(User user);

	boolean updateUser(User user);
	
	DefaultTableModel fillUserData();

}
