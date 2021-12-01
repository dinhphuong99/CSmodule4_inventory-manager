package com.cg.model;

import com.cg.model.dto.ReportProblemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report_problems")
@Accessors(chain = true)
public class ReportProblem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Date createAt;
    @Digits(integer = 8, fraction = 2)
    private BigDecimal compensationFee = BigDecimal.valueOf(0);
    private String description;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Override
    public String toString() {
        return "ReportProblem{" +
                "id=" + id +
                ", createAt='" + createAt + '\'' +
                ", compensationFee='" + compensationFee + '\'' +
                ", description=" + description +
                '}';
    }

    public ReportProblemDTO toReportProblemDTO() {
        return new ReportProblemDTO()
                .setId(id)
                .setCreateAt(createAt)
                .setCompensationFee(compensationFee)
                .setDescription(description)
                .setItem(item.toItemDTO())
                .setUser(user.toUserDTO());
    }
}
