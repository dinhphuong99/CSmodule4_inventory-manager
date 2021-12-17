package com.cg.model.dto;

import com.cg.model.*;
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
public class CategoryDTO {

    private Long id;
    @Size(min = 1, max = 45, message = "Title within 255 characters ! ")
    @Column(unique = true)
    private String titleCategory;
    public Category toCategory() {
        return new Category()
                .setId(id)
                .setTitleCategory(titleCategory);
    }
}

