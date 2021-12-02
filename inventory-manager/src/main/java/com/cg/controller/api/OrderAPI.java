package com.cg.controller.api;

import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.order.*;
import com.cg.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<?>> getList() {

        List<OrderDTO> orders = orderService.findAllOrderDTO();

        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public OrderDTO getByIdDTO(@PathVariable Long id) {

        OrderDTO orderDTO = orderService.findOrderDTOById(id);
        return orderDTO;
    }

    @PutMapping("/update")
    public OrderDTO edit(@RequestBody OrderDTO orderDTO) {

        Order order = orderDTO.toOrder();
        UserDTO userDTO = orderDTO.getUser();
        User userSaved = userService.save(userDTO.toUser());
        order.setUser(userSaved);
        orderService.save(order);
        return getByIdDTO(order.getId());
    }


    @PostMapping("/create")
    public Order create(@RequestBody OrderDTO orderDTO) {
        Order order = orderDTO.toOrder();
        UserDTO userDTO = orderDTO.getUser();
        User userSaved = userService.save(userDTO.toUser());
        order.setUser(userSaved);
        Order orderCreated = orderService.save(order);
        return orderCreated;
    }

    @PostMapping("/update")
    public Order update(@RequestBody Order order) {

        Order orderUpdated = orderService.save(order);

        return orderUpdated;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        orderService.remove(id);

        Optional<Order> order = orderService.findById(id);

        if (order.isPresent()) {
            return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
}