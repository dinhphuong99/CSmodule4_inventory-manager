package com.cg.model;

import com.cg.model.dto.CategoryDTO;
import com.cg.model.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
@Accessors(chain = true)
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 45, message = "Title category within 45 characters ! ")
    @Column(unique = true)
    private String titleCategory;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Product> products;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title=" + titleCategory +
                '}';
    }

    public CategoryDTO toCategoryDTO() {
        return new CategoryDTO()
                .setId(id)
                .setTitleCategory(titleCategory);
    }
}
