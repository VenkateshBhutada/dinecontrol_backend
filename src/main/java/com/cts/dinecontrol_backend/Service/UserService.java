package com.cts.dinecontrol_backend.Service;

import com.cts.dinecontrol_backend.dtolayer.LoginUserDTO;
import com.cts.dinecontrol_backend.dtolayer.UserResponseDTO;
import com.cts.dinecontrol_backend.models.User;

public interface UserService {
	
    User getUserByEmail(String email);
    void registerUser(User user);
    UserResponseDTO loginUser(LoginUserDTO loginUserDTO);
    int getLoggedInUserId();
}

