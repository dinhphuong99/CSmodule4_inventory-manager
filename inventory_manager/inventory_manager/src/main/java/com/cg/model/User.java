package com.cg.model;


import com.cg.model.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @Pattern(regexp = "(^([AÀẢÃÁẠĂẰẮẲẴẶÂẤẦẨẪẬBCDĐEÈÉẺẼẸÊỀẾỂỄỆFGHIÍÌỈĨỊJKLMNOÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢPQRSTUÙÚỦŨỤƯỪỨỬỮỰVWXYÝỲỶỸỴZ]+[aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*?[ ]?)+$)",
            message = "Name format not true, Ex example : Nguyễn Văn An ")
    @Size(min = 1, max = 45, message = "Full name description within 45 characters ! ")
    private String fullName;

    @Pattern(regexp = "(^$|[0][0-9]{9,10}$)", message = "Formatter not true, phone number is have 10-11 character ! ")
    @Column(unique = true)
    private String phone;

    @Size(min = 1, max = 45, message = "Username description within 45 characters ! ")
    @Column(unique = true)
    private String username;

    @Size(min = 8, max = 25, message = "Password 8 to 25 characters ! ")
    private String passwordHash;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Order> orders;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Item> item;

    @OneToMany(mappedBy = "createBy", fetch = FetchType.EAGER)
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
