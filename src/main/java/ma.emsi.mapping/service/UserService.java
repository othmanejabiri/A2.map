package ma.emsi.mapping.service;

import ma.emsi.mapping.entities.Role;
import ma.emsi.mapping.entities.User;




public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUsername(String username);
    Role findRoleByRolename(String rolename);
    void addRoleToUser(String username, String roleName  );
    User authenticate(String username, String password);
}