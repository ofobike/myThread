package com.nbcb.mapper;

import com.nbcb.domain.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopOrderRepository extends JpaRepository<ShopOrder,String>{
}
