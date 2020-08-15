package com.nbcb.service.impl;

import com.nbcb.domain.ShopOrder;
import com.nbcb.mapper.ShopOrderRepository;
import com.nbcb.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopOrderRepository shopOrderRepository;


    /**
     * 查询所有的数据
     * @return
     */
    @Override
    public List<ShopOrder> findAllShopOrder() {
        List<ShopOrder> orderList = shopOrderRepository.findAll();
        return orderList;
    }
}
