package service.user;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Role;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import model.validation.UserValidator;
import repository.security.RightsRolesRepository;
import repository.user.UserRepository;

public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final RightsRolesRepository rightsRolesRepository;

	public UserServiceImpl(UserRepository userRepository,RightsRolesRepository rightsRolesRepository) {
		super();
		this.userRepository = userRepository;
		this.rightsRolesRepository=rightsRolesRepository;
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

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public Notification<Boolean> updateUser(Long id, String username, String password,String role) {
		// TODO Auto-generated method stub
		Role registeredRole = rightsRolesRepository.findRoleByTitle(role);
        User user = new UserBuilder()
        		.setId(id)
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(registeredRole))
                .build();
        System.out.println(user.toString());
        UserValidator userValidator = new UserValidator(user);
        boolean userValid = userValidator.validate();
        Notification<Boolean> userRegisterNotification = new Notification<>();

        if (!userValid) {
            userValidator.getErrors().forEach(userRegisterNotification::addError);
            userRegisterNotification.setResult(Boolean.FALSE);
        } else {
          	user.setPassword(encodePassword(password));
            userRegisterNotification.setResult(userRepository.updateUser(user));
        }
        return userRegisterNotification;
	}
	
	private String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
	
	
	

}
