package service.user;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Account;
import model.User;
import model.builder.UserBuilder;
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
	public boolean removeUser(Long id) {
		// TODO Auto-generated method stub
		User u=new UserBuilder().setId(id).build();
		return userRepository.removeUser(u);
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
		tablemodel.addColumn("UserRole");
		List<User> users=new ArrayList<User>();
		users=userRepository.findAll();
		for (User u: users)
		{
			tablemodel.addRow(new Object[]{u.getId(),u.getUsername(),u.getPassword(),u.getRoles().get(0).getRole()});
		}
		return tablemodel;
	}
	
	
	

}
