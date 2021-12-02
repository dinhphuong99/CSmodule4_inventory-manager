package com.cg.service.user;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface UserService extends IGeneralService<User> {


    List<UserDTO> findAllUserDTO();
    UserDTO findUserDTOById(Long id);
}
