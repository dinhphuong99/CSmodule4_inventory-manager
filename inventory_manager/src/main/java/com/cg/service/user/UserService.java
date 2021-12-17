package com.cg.service.user;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface UserService extends IGeneralService<User> {

    List<UserDTO> findAllUserDTO();
    UserDTO findUserDTOById(Long id);
    Optional<User> findByPhoneAndIdIsNot(String phone, Long id);
    Optional<User> findByUsernameAndIdIsNot(String username, Long id);
}
