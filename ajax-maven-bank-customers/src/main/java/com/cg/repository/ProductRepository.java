package com.cg.repository;

import com.cg.model.*;
import com.cg.model.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    private Long id;
//    private String title;

    @Query("SELECT NEW com.cg.model.dto.ProductDTO(u.id, u.title, u.category) FROM Product u")
    List<ProductDTO> findAllProductDTO();

    @Query("SELECT NEW com.cg.model.dto.ProductDTO(u.id, u.title, u.category) FROM Product u WHERE u.id = :id")
    ProductDTO findProductDTOById(@Param("id") Long id);

    @Query("select c from Product c where c.title = ?1 and c.id <> ?2")
    Optional<Product> findByTitleAndIdIsNot(String title, Long id);
}