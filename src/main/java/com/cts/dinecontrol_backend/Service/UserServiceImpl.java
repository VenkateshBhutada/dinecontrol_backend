package com.cts.dinecontrol_backend.Service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cts.dinecontrol_backend.Repository.UserRepo;
import com.cts.dinecontrol_backend.dtolayer.LoginUserDTO;
import com.cts.dinecontrol_backend.dtolayer.UserResponseDTO;

import com.cts.dinecontrol_backend.models.User;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

  
    @Override
    public void registerUser(User user) {
        userRepo.save(user);
    }
    
    @Override
    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO) {
        Optional<User> userOptional = Optional.ofNullable(userRepo.findByEmail(loginUserDTO.getEmail()));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(loginUserDTO.getPassword())) {
                return new UserResponseDTO(HttpStatus.OK, user.getUserId(), "Successfully logged in");
            } else {
                return new UserResponseDTO(HttpStatus.UNAUTHORIZED, -1, "Password does not match");
            }
        } else {
            return new UserResponseDTO(HttpStatus.NOT_FOUND, -1, "User not found");
        }
    }

    @Override
    public int getLoggedInUserId() {
        // This method can be implemented based on your authentication mechanism
        
        return 0;
    }

	@Override
	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
}

