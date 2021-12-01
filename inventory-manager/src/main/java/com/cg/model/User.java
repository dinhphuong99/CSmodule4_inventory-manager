package com.cg.model;


import com.cg.model.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Integer roleId;
    private String fullName;
    private String phone;
    private String username;
    private String passwordHash;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Order> orders;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Item> item;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<ReportProblem> reportProblem;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roleId='" + roleId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", passwordHash=" + passwordHash +
                '}';
    }

    public UserDTO toUserDTO() {
        return new UserDTO()
                .setId(id)
                .setRoleId(roleId)
                .setFullName(fullName)
                .setPhone(phone)
                .setUsername(username)
                .setPasswordHash(passwordHash);
    }
}
