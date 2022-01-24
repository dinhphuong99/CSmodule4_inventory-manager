package com.cg.controller.api;

import com.cg.exception.DataInputException;
import com.cg.exception.PhoneExistsException;
import com.cg.exception.UserNameExistsException;
import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.service.user.UserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<List<?>> getList() {

        try {
            List<UserDTO> users = userService.findAllUserDTO();

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getByIdDTO(@PathVariable Long id) {

        try {
            UserDTO userDTO = userService.findUserDTOById(id);

            return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> edit(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        try {
            Optional<User> user = userService.findByPhoneAndIdIsNot(userDTO.getPhone(), userDTO.getId());
            Optional<User> user1 = userService.findByUsernameAndIdIsNot(userDTO.getUsername(), userDTO.getId());

            if(user.isPresent())
                throw new PhoneExistsException("Phone already exists");

            if(user1.isPresent())
                throw new UserNameExistsException("User name already exists");
            return new ResponseEntity<>(userService.save(userDTO.toUser()), HttpStatus.CREATED);
        } catch (UserNameExistsException e) {
            throw new UserNameExistsException("User name already exists");
        } catch (Exception e) {
            throw new DataInputException("Data invalid");
        }
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        try {

            Optional<User> user = userService.findByPhoneAndIdIsNot(userDTO.getPhone(), userDTO.getId());
            Optional<User> user1 = userService.findByUsernameAndIdIsNot(userDTO.getUsername(), userDTO.getId());

            if(user.isPresent())
                throw new PhoneExistsException("Phone already exists");

            if(user1.isPresent())
                throw new UserNameExistsException("User name already exists");
            return new ResponseEntity<>(userService.save(userDTO.toUser()), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DataInputException("Data invalid");
        }
    }

    @PostMapping("/update")
    public User update(@RequestBody User user) {

        User userUpdated = userService.save(user);

        return userUpdated;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        try{
            userService.remove(id);

            Optional<User> user = userService.findById(id);

            if (user.isPresent()) {
                return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}