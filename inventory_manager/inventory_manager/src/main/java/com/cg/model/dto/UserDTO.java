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

    @Size(min = 1, max = 45, message = "Username description within 45 characters ! ")
    @Column(unique = true)
    private String username;

    @Size(min = 8, max = 25, message = "Password 8 to 25 characters ! ")
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