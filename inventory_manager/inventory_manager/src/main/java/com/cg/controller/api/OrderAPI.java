package com.cg.controller.api;

import com.cg.exception.DataInputException;
import com.cg.exception.TitleExistsException;
import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.order.*;
import com.cg.service.user.UserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderAPI {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<List<?>> getList() {
        try {
            List<OrderDTO> orders = orderService.findAllOrderDTO();

            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getByIdDTO(@PathVariable Long id) {

        try {
            OrderDTO orderDTO = orderService.findOrderDTOById(id);

            return new ResponseEntity<>(orderDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Validated @RequestBody OrderDTO orderDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        try {
            Order order = orderDTO.toOrder();
            User user = orderDTO.getUser().toUser();
            order.setUser(user);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DataInputException("Data invalid");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody OrderDTO orderDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        try {
            Order order = orderDTO.toOrder();
            User user = orderDTO.getUser().toUser();
            order.setUser(user);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DataInputException("Data invalid");
        }
    }

    @PostMapping("/update")
    public Order update(@RequestBody Order order) {

        Order orderUpdated = orderService.save(order);

        return orderUpdated;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        try {
            orderService.remove(id);

            Optional<Order> order = orderService.findById(id);

            if (order.isPresent()) {
                return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(true, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}