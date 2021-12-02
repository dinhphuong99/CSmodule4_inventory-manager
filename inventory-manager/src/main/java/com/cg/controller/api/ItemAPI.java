package com.cg.controller.api;

import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.item.ItemService;
import com.cg.service.order.OrderService;
import com.cg.service.product.ProductService;
import com.cg.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemAPI {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<?>> getList() {

        List<ItemDTO> items = itemService.findAllItemDTO();

        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(items, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ItemDTO getByIdDTO(@PathVariable Long id) {

        ItemDTO itemDTO = itemService.findItemDTOById(id);
        return itemDTO;
    }

    @PutMapping("/update")
    public ItemDTO edit(@RequestBody ItemDTO itemDTO) {
        Item item = itemDTO.toItem();

        ProductDTO productDTO = itemDTO.getProduct();
        Product productSaved = productService.save(productDTO.toProduct());
        item.setProduct(productSaved);

        UserDTO userDTO = itemDTO.getUser();
        User userSaved = userService.save(userDTO.toUser());
        item.setUser(userSaved);

        OrderDTO orderDTO = itemDTO.getOrder();
        Order orderSaved = orderService.save(orderDTO.toOrder());
        item.setOrder(orderSaved);

        itemService.save(item);
        return getByIdDTO(item.getId());
    }


    @PostMapping("/create")
    public Item create(@RequestBody ItemDTO itemDTO) {
        Item item = itemDTO.toItem();
        ProductDTO productDTO = itemDTO.getProduct();
        Product productSaved = productService.save(productDTO.toProduct());
        item.setProduct(productSaved);

        UserDTO userDTO = itemDTO.getUser();
        User userSaved = userService.save(userDTO.toUser());
        item.setUser(userSaved);

        OrderDTO orderDTO = itemDTO.getOrder();
        Order orderSaved = orderService.save(orderDTO.toOrder());
        item.setOrder(orderSaved);
        Item itemCreated = itemService.save(item);
        return itemCreated;
    }

    @PostMapping("/update")
    public Item update(@RequestBody Item item) {

        Item itemUpdated = itemService.save(item);

        return itemUpdated;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        itemService.remove(id);

        Optional<Item> item = itemService.findById(id);

        if (item.isPresent()) {
            return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
}

