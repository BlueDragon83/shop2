package com.shop.repository;

import com.shop.domain.entity.item.CartItem;
import com.shop.dto.CartDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // 카트 아이디와 아이템 아이디를 이용하여 카트 아이템 레퍼지토리의 엔티티 조회
    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    // 장바구니 페이지에 전달할 CartDetailDto 를 쿼리로 조회해서 CartDetailDtoList 에 담아줌
    @Query("select new com.shop.dto.CartDetailDto(ci.id, i.itemName, i.price, ci.count, im.imgUrl) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.mainImgYn = 'Y' " + // 장바구니에 담겨있는 상품의 대표 이미지만 가져옴
            "order by ci.createdDate desc"
    )
    List<CartDetailDto> findCartDetailDtoList(Long cartId);

}
