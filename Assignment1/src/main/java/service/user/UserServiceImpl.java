package service.user;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Account;
import model.User;
import repository.user.UserRepository;

public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public boolean save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		userRepository.removeAll();
		
	}

	@Override
	public boolean removeUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.removeUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.updateUser(user);
	}

	@Override
	public DefaultTableModel fillUserData() {
		// TODO Auto-generated method stub
		DefaultTableModel tablemodel=new DefaultTableModel();
		tablemodel.addColumn("Id");
		tablemodel.addColumn("Username");
		tablemodel.addColumn("Password");
		List<User> users=new ArrayList<User>();
		users=userRepository.findAll();
		for (User u: users)
		{
			tablemodel.addRow(new Object[]{u.getId(),u.getUsername(),u.getPassword()});
		}
		return tablemodel;
	}
	
	
	

}
