package service.user;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.User;
import model.validation.Notification;

public interface UserService {
	
	List<User> findAll();

	boolean save(User user);

	void removeAll();

	boolean removeUser(Long id);

	Notification<Boolean> updateUser(Long id,String username,String password,String role);
	
	DefaultTableModel fillUserData();
	
	User findByUsername(String username);

}
