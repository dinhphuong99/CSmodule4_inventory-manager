package com.cg.service.user;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserDTO> findAllUserDTO() {
        return userRepository.findAllUserDTO();
    }

    @Override
    public UserDTO findUserDTOById(Long id) {
        return userRepository.findUserDTOById(id);
    }

    @Override
    public Optional<User> findByPhoneAndIdIsNot(String phone, Long id) {
        return userRepository.findByPhoneAndIdIsNot(phone, id);
    }

    @Override
    public Optional<User> findByUsernameAndIdIsNot(String username, Long id) {
        return userRepository.findByUsernameAndIdIsNot(username, id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}
