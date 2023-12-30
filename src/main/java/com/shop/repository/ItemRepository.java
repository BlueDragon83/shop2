package com.shop.repository;

import com.shop.domain.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long>, ItemRepositoryCustom {
}
