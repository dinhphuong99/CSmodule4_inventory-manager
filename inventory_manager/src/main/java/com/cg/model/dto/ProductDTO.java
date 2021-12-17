package com.cg.model.dto;

import com.cg.model.Category;
import com.cg.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)

public class ProductDTO {

    private Long id;

    @Size(min = 4, max = 25, message = "Title 4 to 25 characters ! ")
    @Column(unique = true)
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
