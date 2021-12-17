package com.cg.controller.api;

import com.cg.exception.DataInputException;
import com.cg.exception.TitleExistsException;
import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.item.ItemService;
import com.cg.service.order.OrderService;
import com.cg.service.product.ProductService;
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
@RequestMapping("/api/items")
public class ItemAPI {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<?>> getList() {
        try {

            List<ItemDTO> items = itemService.findAllItemDTO();

            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(items, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getByIdDTO(@PathVariable Long id) {

        try {
            ItemDTO itemDTO = itemService.findItemDTOById(id);

            return new ResponseEntity<>(itemDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Validated @RequestBody ItemDTO itemDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        try {
            Optional<Item> item = itemService.findByLocationAndIdIsNot(itemDTO.getLocation(), itemDTO.getId());

            if(item.isPresent())
                throw new TitleExistsException("Location already exists");
            Item item1 = itemDTO.toItem();
            Order order = itemDTO.getOrder().toOrder();
            User user = itemDTO.getUser().toUser();
            Product product = itemDTO.getProduct().toProduct();
            item1.setProduct(product);
            item1.setUser(user);
            order.setTotalFee(orderService.sumFeeAllItemWithIdOrder(order.getId()));
            orderService.save(order);
            item1.setOrder(order);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DataInputException("Data invalid");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody ItemDTO itemDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        try {
            Optional<Item> item = itemService.findByLocationAndIdIsNot(itemDTO.getLocation(), itemDTO.getId());

            if(item.isPresent())
                throw new TitleExistsException("Location already exists");
            Item item1 = itemDTO.toItem();
            Order order = itemDTO.getOrder().toOrder();
            User user = itemDTO.getUser().toUser();
            Product product = itemDTO.getProduct().toProduct();
            item1.setProduct(product);
            item1.setUser(user);
            order.setTotalFee(orderService.sumFeeAllItemWithIdOrder(order.getId()));
            orderService.save(order);
            item1.setOrder(order);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DataInputException("Data invalid");
        }
    }

    @PostMapping("/update")
    public Item update(@RequestBody Item item) {

        Item itemUpdated = itemService.save(item);

        return itemUpdated;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        try {
            itemService.remove(id);

            Optional<Item> item = itemService.findById(id);

            if (item.isPresent()) {
                return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
