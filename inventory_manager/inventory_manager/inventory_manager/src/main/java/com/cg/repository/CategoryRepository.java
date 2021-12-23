package com.cg.repository;

import com.cg.model.*;
import com.cg.model.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT NEW com.cg.model.dto.CategoryDTO(u.id, u.titleCategory) FROM Category u")
    List<CategoryDTO> findAllCategoryDTO();

    @Query("SELECT NEW com.cg.model.dto.CategoryDTO(u.id, u.titleCategory) FROM Category u WHERE u.id = :id")
    CategoryDTO findCategoryDTOById(@Param("id") Long id);

    @Query("select c from Category c where c.titleCategory = ?1 and c.id <> ?2")
    Optional<Category> findByTitleCategoryAndIdIsNot(String email, Long id);

    @Query("select MAX(c.id) from Category c")
    Long findMaxId();
}