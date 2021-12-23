package com.cg.model.dto;

import com.cg.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO {

    private Long id;

    private Integer roleId;

    @Pattern(regexp = "(^([AÀẢÃÁẠĂẰẮẲẴẶÂẤẦẨẪẬBCDĐEÈÉẺẼẸÊỀẾỂỄỆFGHIÍÌỈĨỊJKLMNOÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢPQRSTUÙÚỦŨỤƯỪỨỬỮỰVWXYÝỲỶỸỴZ]+[aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*?[ ]?)+$)",
            message = "Name format not true, Ex example : Nguyễn Văn An ")
    @Size(min = 1, max = 45, message = "Full name description within 255 characters ! ")
    private String fullName;

    @Pattern(regexp = "(^$|[0][0-9]{9,10}$)", message = "Formatter not true, phone number is have 10-11 character ! ")
    @Column(unique = true)
    private String phone;

    @Pattern(regexp = "(^[a-z0-9_-]{6,14}$)", message = "Formatter not true, ex: java_2-novice")
    @Column(unique = true)
    private String username;

    @Pattern(regexp = "(^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$)",
            message = "Formatter not true. Must have at least one numeric character, one lowercase character, \n" +
                    "one uppercase character, one special symbol among @#$%\n" +
                    "Password length should be between 8 and 20")
    private String passwordHash;

    public User toUser() {
        return new User()
                .setId(id)
                .setRoleId(roleId)
                .setFullName(fullName)
                .setPhone(phone)
                .setUsername(username)
                .setPasswordHash(passwordHash);
    }
}