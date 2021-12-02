package com.cg.controller.api;

import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryAPI {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<?>> getList() {

        List<CategoryDTO> categories = categoryService.findAllCategoryDTO();

        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(categories, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public CategoryDTO getByIdDTO(@PathVariable Long id) {

        CategoryDTO categoriesDTO = categoryService.findCategoryDTOById(id);
        return categoriesDTO;
    }

    @PutMapping("/update")
    public CategoryDTO edit(@RequestBody CategoryDTO categoryDTO) {

        Category category = categoryDTO.toCategory();
        categoryService.save(category);
        return getByIdDTO(category.getId());
    }


    @PostMapping("/create")
    public Category create(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryDTO.toCategory();
        Category categoryCreated = categoryService.save(category);
        return categoryCreated;
    }

    @PostMapping("/update")
    public Category update(@RequestBody Category category) {

        Category categoryUpdated = categoryService.save(category);

        return categoryUpdated;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        categoryService.remove(id);

        Optional<Category> category = categoryService.findById(id);

        if (category.isPresent()) {
            return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
}
