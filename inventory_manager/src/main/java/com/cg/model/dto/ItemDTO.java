package com.cg.model.dto;

import com.cg.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

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

    @Size(min = 4, max = 15, message = "Size location 4 to 15 characters ! ")
    @Column(unique = true)
    private String location;

    @NotEmpty(message = "Price not empty")
    @DecimalMin(value = "49", message = "Price must be greater than or equal to 50", inclusive = false)
    @DecimalMax(value = "10000001", message = "Price must be less than or equal to 10.000.000", inclusive = false)
    private BigDecimal price;

    @NotEmpty(message = "Fee not empty")
    @DecimalMin(value = "49", message = "Fee must be greater than or equal to 50", inclusive = false)
    @DecimalMax(value = "10000001", message = "Fee must be less than or equal to 10.000.000", inclusive = false)
    private BigDecimal fee;

    @NotEmpty(message = "Quantity not empty")
    @DecimalMin(value = "49", message = "Quantity must be greater than or equal to 50", inclusive = false)
    @DecimalMax(value = "10000001", message = "Quantity must be less than or equal to 10.000.000", inclusive = false)
    private Double quantity;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date startDate = new Date();

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date updateDate = new Date();

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
