package com.cg.model;

import com.cg.model.dto.ReportProblemDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
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

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private java.util.Date createAt = new java.util.Date();

    @DecimalMin(value = "49", message = "Compensation fee must be greater than or equal to 50", inclusive = false)
    @DecimalMax(value = "10000001", message = "Compensation fee must be less than or equal to 10.000.000", inclusive = false)
    private BigDecimal compensationFee = BigDecimal.valueOf(0);
    private String description;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "create_by", referencedColumnName = "id", nullable = false)
    private User createBy;

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
                .setCreateBy(createBy.toUserDTO());
    }
}
