package com.studyCafeProject.Service;


import com.studyCafeProject.Exception.InvalidIdException;
import com.studyCafeProject.Exception.UserIsAdminException;
import com.studyCafeProject.Model.User;
import com.studyCafeProject.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    //private ArrayList<User> users =new ArrayList();

    private final UserRepository userRepository;

    public UserService(UserRepository usersRepository) {
        this.userRepository = usersRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }


    public int addNewUser(User user) {
        String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);

        if(existsByEmail(user.getEmail()))
        {
            return 2;
        }
        try
        {
            userRepository.save(user);
            return 1;
        }
        catch (Exception e) {
        }
        return 0;
    }

    private boolean existsByEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }


    //get user by id
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }


    //get user by username
    public User getUserByUsername(String  username) {
        return userRepository.findUserByUsername(username);
    }

    //check if user is valid
    public void checkUser(Integer id) {
        User myUser = userRepository.findById(id)
                .orElseThrow(
                        ()->new InvalidIdException("Invalid id"));

        if(myUser.getRole().equals("ADMIN")){
            throw new UserIsAdminException("User is admin !");
        }
    }

    public Optional<User> editUserById(Integer userId, User user) {
        if (userRepository.existsById(userId))
        {
            User users = userRepository.getReferenceById(userId);
            if(users != null)
            {
                users.setUsername(user.getUsername());
                users.setPassword(user.getPassword());
                users.setEmail(user.getEmail());
                users.setPhoneNo(user.getPhoneNo());
                users.setRole(user.getRole());

                return Optional.of(userRepository.save(users));
            }
        }
        return Optional.empty();
    }

    public boolean removeUser(Integer userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

}
