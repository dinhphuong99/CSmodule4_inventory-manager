package com.cg.model.dto;

import com.cg.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReportProblemDTO {
    private Long id;
    private Date createAt;
    private BigDecimal compensationFee;
    private String description;
    private ItemDTO item;
    private UserDTO user;

    public ReportProblem toReportProblem() {
        return new ReportProblem()
                .setId(id)
                .setCreateAt(createAt)
                .setCompensationFee(compensationFee)
                .setDescription(description)
                .setItem(item.toItem())
                .setUser(user.toUser());
    }
}
