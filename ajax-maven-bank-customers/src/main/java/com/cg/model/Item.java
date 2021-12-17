package com.cg.model;

import com.cg.model.dto.ItemDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
@Accessors(chain = true)
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "update_by", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    private Set<ReportProblem> reportProblem;

    private Integer status;

    @Size(min = 4, max = 15, message = "Size location 4 to 15 characters ! ")
    @Column(unique = true)
    private String location;

    @NotEmpty(message = "Price not empty")
    @DecimalMin(value = "49", message = "Price must be greater than or equal to 50", inclusive = false)
    @DecimalMax(value = "10000001", message = "Price must be less than or equal to 10.000.000", inclusive = false)
    private BigDecimal price = BigDecimal.valueOf(0);

    @NotEmpty(message = "Fee not empty")
    @DecimalMin(value = "49", message = "Fee must be greater than or equal to 50", inclusive = false)
    @DecimalMax(value = "10000001", message = "Fee must be less than or equal to 10.000.000", inclusive = false)
    private BigDecimal fee = BigDecimal.valueOf(0);

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", location='" + location + '\'' +
                ", price='" + price + '\'' +
                ", fee='" + fee + '\'' +
                ", quantity='" + quantity + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", endDate=" + startDate +
                '}';
    }

    public ItemDTO toItemDTO() {
        return new ItemDTO()
                .setId(id)
                .setProduct(product.toProductDTO())
                .setUser(user.toUserDTO())
                .setOrder(order.toOrderDTO())
                .setStatus(status)
                .setLocation(location)
                .setFee(fee)
                .setQuantity(quantity)
                .setStartDate(startDate)
                .setUpdateDate(updateDate);
    }
}