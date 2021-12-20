package com.cg.controller.api;

import com.cg.exception.DataInputException;
import com.cg.exception.TitleExistsException;
import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.category.CategoryService;
import com.cg.service.product.ProductService;
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
@RequestMapping("/api/products")
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<List<?>> getList() {
        try {

            List<ProductDTO> products = productService.findAllProductDTO();

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getByIdDTO(@PathVariable Long id) {

        try {
            ProductDTO productDTO = productService.findProductDTOById(id);

            return new ResponseEntity<>(productDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        try {
            Optional<Product> product = productService.findByTitleAndIdIsNot(productDTO.getTitle(), productDTO.getId());

            if(product.isPresent())
                throw new TitleExistsException("Title already exists");
            Product product1 = productDTO.toProduct();
            Category category = productDTO.getCategoryDTO().toCategory();
            product1.setCategory(category);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DataInputException("Data invalid");
        }
    }


    @PutMapping("/update")
    public ResponseEntity<?> edit(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        try {
            Optional<Product> product = productService.findByTitleAndIdIsNot(productDTO.getTitle(), productDTO.getId());

            if(product.isPresent())
                throw new TitleExistsException("Title already exists");
            Product product1 = productDTO.toProduct();
            Category category = productDTO.getCategoryDTO().toCategory();
            product1.setCategory(category);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DataInputException("Data invalid");
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        try {
            productService.remove(id);
            Optional<Product> product = productService.findById(id);

            if (product.isPresent()) {
                return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
