package com.cg.repository;

import com.cg.model.Category;
import com.cg.model.Item;
import com.cg.model.dto.ItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT NEW com.cg.model.dto.ItemDTO(u.id, u.product, u.user, u.order, u.status, u.location, u.price, u.fee, u.quantity, u.startDate, u.updateDate) FROM Item u")
    List<ItemDTO> findAllItemDTO();

    @Query("SELECT NEW com.cg.model.dto.ItemDTO(u.id, u.product, u.user, u.order,u.status, u.location, u.price, u.fee, u.quantity, u.startDate, u.updateDate) FROM Item u WHERE u.id = :id")
    ItemDTO findItemDTOById(@Param("id") Long id);

    @Query("select c from Item c where c.location = ?1 and c.id <> ?2")
    Optional<Item> findByLocationAndIdIsNot(String location, Long id);

}