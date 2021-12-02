package com.cg.controller.api;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserAPI {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<?>> getList() {

        List<UserDTO> users = userService.findAllUserDTO();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public UserDTO getByIdDTO(@PathVariable Long id) {

        UserDTO userDTO = userService.findUserDTOById(id);

        return userDTO;
    }

    @PutMapping("/update")
    public UserDTO edit(@RequestBody UserDTO userDTO) {

        User user = userDTO.toUser();
        userService.save(user);
        return getByIdDTO(user.getId());
    }


    @PostMapping("/create")
    public User create(@RequestBody UserDTO userDTO) {
        User user = userDTO.toUser();

        User userCreated = userService.save(user);

        return userCreated;
    }

    @PostMapping("/update")
    public User update(@RequestBody User user) {

        User userUpdated = userService.save(user);

        return userUpdated;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        userService.remove(id);

        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
}
