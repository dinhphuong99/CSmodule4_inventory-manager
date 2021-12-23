package com.cg.model.dto;

import com.cg.model.Order;
import com.cg.model.User;
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
public class OrderDTO {

    private Long id;
    private UserDTO user;
    private Integer status;

    @NotEmpty(message = "Total fee not empty")
    @DecimalMin(value = "-1", message = "Total fee must be greater than or equal to 0", inclusive = false)
    @DecimalMax(value = "10000001", message = "Total fee must be less than or equal to 10.000.000", inclusive = false)
    private BigDecimal totalFee;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date createAt = new Date();

    @NotBlank(message = "Content not blank")
    private String content;

    public OrderDTO(Long id, User user, Integer status, BigDecimal totalFee, Date createAt, String content) {
        this.id = id;
        this.user = user.toUserDTO();
        this.status = status;
        this.totalFee = totalFee;
        this.createAt = createAt;
        this.content = content;
    }

    public Order toOrder() {
        return new Order()
                .setId(id)
                .setContent(content)
                .setCreateAt(createAt)
                .setUser(user.toUser())
                .setStatus(status)
                .setTotalFee(totalFee);
    }
}
