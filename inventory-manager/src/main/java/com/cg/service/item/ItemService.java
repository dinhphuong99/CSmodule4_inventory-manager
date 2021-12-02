package com.cg.service.item;

import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.IGeneralService;

import java.util.List;

public interface ItemService extends IGeneralService<Item> {

    List<ItemDTO> findAllItemDTO();
    ItemDTO findItemDTOById(Long id);
}
