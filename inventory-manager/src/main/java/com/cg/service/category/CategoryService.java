package com.cg.service.category;

import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.IGeneralService;

import java.util.List;

public interface CategoryService extends IGeneralService<Category> {

    List<CategoryDTO> findAllCategoryDTO();
    CategoryDTO findCategoryDTOById(Long id);
}
