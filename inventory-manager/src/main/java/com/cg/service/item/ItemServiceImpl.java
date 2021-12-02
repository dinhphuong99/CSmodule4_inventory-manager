package com.cg.service.item;

import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public List<ItemDTO> findAllItemDTO() {
        return itemRepository.findAllItemDTO();
    }

    @Override
    public ItemDTO findItemDTOById(Long id) {
        return itemRepository.findItemDTOById(id);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void remove(Long id) {
        itemRepository.deleteById(id);
    }
}
