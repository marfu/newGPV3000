package ci.prosuma.prosumagpv.metier.dao.mysql.security;

import java.util.List;

import ci.prosuma.prosumagpv.entity.security.User;

public interface IMySQLUserDAO {
	
	public User createOrUpdateUser(User u);
	
	public User getUser(String userName);
	
	public boolean removeUser(User u);
	
	public List<User> getAllUsers();
	
	public User getUserByNameAndPassword(String userName,String password);

}
