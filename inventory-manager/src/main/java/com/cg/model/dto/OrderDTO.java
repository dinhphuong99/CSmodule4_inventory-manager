package com.cg.model.dto;

import com.cg.model.Order;
import com.cg.model.User;
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
public class OrderDTO {

    private Long id;
    private UserDTO user;
    private Integer status;
    private BigDecimal totalFee;
    private Date createAt;
    private String content;

    public OrderDTO(Long id, User user, Integer status,
                    BigDecimal totalFee, Date createAt,
                    String content) {
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
