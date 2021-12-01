package com.cg.model.dto;

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
public class CategoryDTO {

    private Long id;
    private String title;

    public Category toCategory() {
        return new Category()
                .setId(id)
                .setTitle(title);
    }
}

