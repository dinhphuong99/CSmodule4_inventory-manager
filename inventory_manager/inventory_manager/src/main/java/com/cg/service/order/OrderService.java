package com.cg.service.order;

import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService extends IGeneralService<Order> {

    List<OrderDTO> findAllOrderDTO();
    OrderDTO findOrderDTOById(Long id);
    BigDecimal sumFeeAllItemWithIdOrder(Long id);
}
