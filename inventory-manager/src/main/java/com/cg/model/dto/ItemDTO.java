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

public class ItemDTO {
    private Long id;

    private ProductDTO product;
    private UserDTO user;
    private OrderDTO order;
    private Integer status;
    private String location;
    private BigDecimal price;
    private BigDecimal fee;
    private Double quantity;
    private Date startDate;
    private Date updateDate;

    public ItemDTO(Long id, Product product,
                   User user, Order order,
                   Integer status, String location,
                   BigDecimal price, BigDecimal fee,
                   Double quantity, Date startDate,
                   Date updateDate) {
        this.id = id;
        this.product = product.toProductDTO();
        this.user = user.toUserDTO();
        this.order = order.toOrderDTO();
        this.status = status;
        this.location = location;
        this.price = price;
        this.fee = fee;
        this.quantity = quantity;
        this.startDate = startDate;
        this.updateDate = updateDate;
    }

    public Item toItem() {
        return new Item()
                .setId(id)
                .setProduct(product.toProduct())
                .setUser(user.toUser())
                .setOrder(order.toOrder())
                .setStatus(status)
                .setLocation(location)
                .setFee(fee)
                .setQuantity(quantity)
                .setStartDate(startDate)
                .setUpdateDate(updateDate);
    }
}
