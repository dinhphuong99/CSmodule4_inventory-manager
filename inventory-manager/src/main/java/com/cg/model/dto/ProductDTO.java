package com.cg.model.dto;

import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)

public class ProductDTO {

    private Long id;
    private String title;
    private CategoryDTO categoryDTO;

    public ProductDTO(Long id, String title, Category category) {
        this.id = id;
        this.title = title;
        this.categoryDTO = category.toCategoryDTO();
    }

    public Product toProduct() {
        return new Product()
                .setId(id)
                .setTitle(title)
                .setCategory(categoryDTO.toCategory());
    }
}
