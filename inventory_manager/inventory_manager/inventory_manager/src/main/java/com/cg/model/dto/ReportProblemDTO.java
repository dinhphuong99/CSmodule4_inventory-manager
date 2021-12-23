package com.cg.model.dto;

import com.cg.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReportProblemDTO {
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date createAt = new Date();

    @NotEmpty(message = "Compensation fee not empty")
    @DecimalMin(value = "49", message = "Compensation fee must be greater than or equal to 50", inclusive = false)
    @DecimalMax(value = "10000001", message = "Compensation fee must be less than or equal to 10.000.000", inclusive = false)
    private BigDecimal compensationFee;

    @NotBlank(message = "Description not blank")
    private String description;
    private ItemDTO item;
    private UserDTO createBy;

    public ReportProblemDTO(Long id, Date createAt, BigDecimal compensationFee, String description, Item item, User createBy) {
        this.id = id;
        this.createAt = createAt;
        this.compensationFee = compensationFee;
        this.description = description;
        this.item = item.toItemDTO();
        this.createBy = createBy.toUserDTO();
    }

    public ReportProblem toReportProblem() {
        return new ReportProblem()
                .setId(id)
                .setCreateAt(createAt)
                .setCompensationFee(compensationFee)
                .setDescription(description)
                .setItem(item.toItem())
                .setCreateBy(createBy.toUser());
    }
}
