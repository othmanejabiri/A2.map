package ma.emsi.mapping.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.emsi.mapping.entities.Role;
import ma.emsi.mapping.entities.User;
import ma.emsi.mapping.repository.RoleRepository;
import ma.emsi.mapping.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUsername(String username) {


        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRolename(String rolename) {

        return roleRepository.findByRoleName(rolename);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = this.findUserByUsername(username);
        Role role = this.findRoleByRolename(roleName);
        if (user.getRoles() != null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }

    }

    @Override
    public User authenticate(String username, String password) {
        User user=this.userRepository.findByUsername(username);
        if(user==null) {throw new RuntimeException("User not found");}
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Invalid username or password");
    }
}
