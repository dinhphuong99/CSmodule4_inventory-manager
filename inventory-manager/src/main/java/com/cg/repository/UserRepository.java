package com.cg.repository;


import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT NEW com.cg.model.dto.UserDTO(u.id, u.roleId, u.fullName, u.phone, u.username, u.passwordHash) FROM User u")
    List<UserDTO> findAllUserDTO();

    @Query("SELECT NEW com.cg.model.dto.UserDTO(u.id, u.roleId, u.fullName, u.phone, u.username, u.passwordHash) FROM User u WHERE u.id = :id")
    UserDTO findUserDTOById(@Param("id") Long id);
}