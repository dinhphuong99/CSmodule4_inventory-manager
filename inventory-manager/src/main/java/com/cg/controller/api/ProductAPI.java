package com.cg.controller.api;

import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.category.CategoryService;
import com.cg.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<?>> getList() {

        List<ProductDTO> products = productService.findAllProductDTO();

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ProductDTO getByIdDTO(@PathVariable Long id) {

        ProductDTO productDTO = productService.findProductDTOById(id);
        return productDTO;
    }

    @PutMapping("/update")
    public ProductDTO edit(@RequestBody ProductDTO productDTO) {

        Product product = productDTO.toProduct();
        CategoryDTO categoryDTO = productDTO.getCategoryDTO();
        Category categorySaved = categoryService.save(categoryDTO.toCategory());
        product.setCategory(categorySaved);
        productService.save(product);
        return getByIdDTO(product.getId());
    }

    @PostMapping("/create")
    public Product create(@RequestBody ProductDTO productDTO) {
        Product product = productDTO.toProduct();
        CategoryDTO categoryDTO = productDTO.getCategoryDTO();
        Category categorySaved = categoryService.save(categoryDTO.toCategory());
        product.setCategory(categorySaved);
        Product productCreated = productService.save(product);
        return productCreated;
    }

    @PostMapping("/update")
    public Product update(@RequestBody Product product) {

        Product productUpdated = productService.save(product);

        return productUpdated;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        productService.remove(id);
        Optional<Product> product = productService.findById(id);

        if (product.isPresent()) {
            return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
}
