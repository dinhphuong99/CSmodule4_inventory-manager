package com.cg.controller.api;

import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.exception.TitleCategoryExistsException;
import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.category.CategoryService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryAPI {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<List<?>> getList() {

        try {
            List<CategoryDTO> categories = categoryService.findAllCategoryDTO();

            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(categories, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getByIdDTO(@PathVariable Long id) {

        try {
            CategoryDTO categoriesDTO = categoryService.findCategoryDTOById(id);

            return new ResponseEntity<>(categoriesDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        try {
            Optional<Category> category = categoryService.findByTitleCategoryAndIdIsNot(categoryDTO.getTitleCategory(), 0L);

            if(category.isPresent())
                throw new DataInputException("Title category already exists");
            return new ResponseEntity<>(categoryService.save(categoryDTO.toCategory()), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DataInputException("Data invalid");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> edit(@Validated @RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        try {
            Optional<Category> category = categoryService.findByTitleCategoryAndIdIsNot(categoryDTO.getTitleCategory(), categoryDTO.getId());

            if(category.isPresent())
                throw new TitleCategoryExistsException("Title category already exists");
            return new ResponseEntity<>(categoryService.save(categoryDTO.toCategory()), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DataInputException("Data invalid");
        }
    }

    @PostMapping("/update")
    public Category update(@RequestBody Category category) {

        Category categoryUpdated = categoryService.save(category);

        return categoryUpdated;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        try {
            categoryService.remove(id);

            Optional<Category> category = categoryService.findById(id);

            if (category.isPresent()) {
                return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}