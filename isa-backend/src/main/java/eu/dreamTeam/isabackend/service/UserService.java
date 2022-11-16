package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.model.User;
import eu.dreamTeam.isabackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByEmail(String email){
        var users = getAllUsers();

        User response = null;
        for(var user : users){
            if(user.getAccount().getEmail().equals(email)){
                response = user;
                break;
            }
        }
        return response;
    }

    public User getByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
