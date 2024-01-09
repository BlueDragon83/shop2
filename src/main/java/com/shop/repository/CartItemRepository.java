package com.shop.repository;

import com.shop.domain.entity.item.CartItem;
import com.shop.dto.CartListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Query("select new com.shop.dto.CartListDto(ci.id, i.itemName, i.price, ci.count, im.filePath) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.mainImgYn = 'Y' " +
            "order by ci.createdDate desc")
    List<CartListDto> findCartListDto(Long cartId);


}

