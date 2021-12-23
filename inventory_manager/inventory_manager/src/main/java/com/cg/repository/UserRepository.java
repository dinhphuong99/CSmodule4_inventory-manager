package com.cg.repository;


import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT NEW com.cg.model.dto.UserDTO(u.id, u.roleId, u.fullName, u.phone, u.username, u.passwordHash) FROM User u")
    List<UserDTO> findAllUserDTO();

    @Query("SELECT NEW com.cg.model.dto.UserDTO(u.id, u.roleId, u.fullName, u.phone, u.username, u.passwordHash) FROM User u WHERE u.id = :id")
    UserDTO findUserDTOById(@Param("id") Long id);

    @Query("select c from User c where c.phone = ?1 and c.id <> ?2")
    Optional<User> findByPhoneAndIdIsNot(String phone, Long id);

    @Query("select c from User c where c.username = ?1 and c.id <> ?2")
    Optional<User> findByUsernameAndIdIsNot(String username, Long id);

    @Query("select MAX(c.id) from User c")
    Long findMaxId();
}