package com.cg.service.product;

import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ProductService extends IGeneralService<Product> {


    List<ProductDTO> findAllProductDTO();
    ProductDTO findProductDTOById(Long id);
    Optional<Product> findByTitleAndIdIsNot(String title, Long id);
}
