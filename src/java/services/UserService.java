
package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author Kanayo
 */
public class UserService {
    
    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }
    
    public User get (String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    } 
    
    public void insert(String email, boolean activity, String first_name, String last_name, String password, Role role) throws Exception{
        User user = new User(email, activity, first_name, last_name, password);
        RoleDB roledb = new RoleDB();
        Role newRole = roledb.getRole(role.getRoleId());
        user.setRole(newRole);
        UserDB userDB= new UserDB();
        userDB.insert(user);
    }
    
    public void update(String email,boolean activity, String first_name, String last_name, String password, Role role) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        RoleDB roledb = new RoleDB();
        Role newRole = roledb.getRole(role.getRoleId());
        user.setActive(activity);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setRole(newRole);
        userDB.update(user);
        
    }
    
    public void delete(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        userDB.delete(user);
    }
    
}
