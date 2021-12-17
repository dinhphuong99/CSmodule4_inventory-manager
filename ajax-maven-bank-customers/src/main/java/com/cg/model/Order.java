package com.cg.model;

import com.cg.model.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
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

    @NotEmpty(message = "TotalFee not empty")
    @DecimalMin(value = "-1", message = "Total fee must be greater than or equal to 0", inclusive = false)
    @DecimalMax(value = "10000001", message = "Total fee must be less than or equal to 10.000.000", inclusive = false)
    private BigDecimal totalFee = BigDecimal.valueOf(0);

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private java.util.Date createAt = new java.util.Date();

    @NotBlank(message = "Content not blank")
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