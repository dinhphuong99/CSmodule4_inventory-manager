package com.cg.repository;

import com.cg.model.*;
import com.cg.model.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//    Long id, User user, Integer type,
//    Integer status, BigDecimal totalFee,
//    Date createAt, String content

    @Query("SELECT NEW com.cg.model.dto.OrderDTO(u.id, u.user, u.status, u.totalFee, u.createAt, u.content) FROM Order u")
    List<OrderDTO> findAllOrderDTO();

    @Query("SELECT NEW com.cg.model.dto.OrderDTO(u.id, u.user, u.status, u.totalFee, u.createAt, u.content) FROM Order u WHERE u.id = :id")
    OrderDTO findOrderDTOById(@Param("id") Long id);
}