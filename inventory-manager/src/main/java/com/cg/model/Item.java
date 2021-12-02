package com.cg.model;

import com.cg.model.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.sql.Date;
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
    private String location;

    @Digits(integer = 8, fraction = 2)
    private BigDecimal price = BigDecimal.valueOf(0);

    @Digits(integer = 8, fraction = 2)
    private BigDecimal fee = BigDecimal.valueOf(0);

    private Double quantity;
    private Date startDate;
    private Date updateDate;

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