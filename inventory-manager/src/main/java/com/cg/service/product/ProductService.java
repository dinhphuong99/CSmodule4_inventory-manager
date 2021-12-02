package com.cg.service.product;

import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.IGeneralService;

import java.util.List;

public interface ProductService extends IGeneralService<Product> {


    List<ProductDTO> findAllProductDTO();
    ProductDTO findProductDTOById(Long id);
}
