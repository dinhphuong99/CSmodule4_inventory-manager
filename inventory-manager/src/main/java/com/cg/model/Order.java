package com.cg.model;

import com.cg.model.dto.OrderDTO;
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
@Table(name = "orders")
@Accessors(chain = true)
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<Item> items;

    private Integer status;

    @Digits(integer = 8, fraction = 2)
    private BigDecimal totalFee = BigDecimal.valueOf(0);
    private Date createAt;
    private String content;


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", totalFee='" + totalFee + '\'' +
                ", createAt='" + createAt + '\'' +
                ", content=" + content +
                '}';
    }

    public OrderDTO toOrderDTO() {
        return new OrderDTO()
                .setId(id)
                .setContent(content)
                .setCreateAt(createAt)
                .setUser(user.toUserDTO())
                .setStatus(status)
                .setTotalFee(totalFee);
    }
}