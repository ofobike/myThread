package com.nbcb.service.impl;

import com.nbcb.domain.ShopOrder;
import com.nbcb.domain.ShopSkill;
import com.nbcb.mapper.ShopOrderRepository;
import com.nbcb.mapper.ShopSkillRepository;
import com.nbcb.service.ShopSkillService;
import com.nbcb.util.OrderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ShopSkillServiceImpl implements ShopSkillService {
    private static Logger LOGGER = LoggerFactory.getLogger(ShopSkillServiceImpl.class);

    @Autowired
    private ShopSkillRepository shopSkillRepository;


    @Autowired
    private ShopOrderRepository shopOrderRepository;
    @Override
    public List<ShopSkill> finaAllShopSkill() {
        List<ShopSkill> shopSkills = shopSkillRepository.findAll();
        return shopSkills;
    }

    /**
     * 更新数据
     *
     * @param skillId
     * @return
     */
    @Transactional
    @Override
    public String updateSkillNum(String skillId) {
        //获取当前的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = sdf.format(new Date());
        LOGGER.info("秒杀时间--->{},秒杀商品的id-->{}", startTime, skillId);
        //查询是否存在数据
        ShopSkill shopSkill = shopSkillRepository.findById(skillId).orElse(null);
        if (null == shopSkill) {
            throw new RuntimeException("根据skillId-->[{}],秒杀商品信息不存在");
        }
        String productNum = shopSkill.getProductNum();
        int parseInt = Integer.parseInt(productNum);
        if (parseInt <=0){
            throw new RuntimeException("库存没有了!请稍后再来!");
        }
        int num = shopSkillRepository.updateSkillNum(startTime, skillId);
        if (num  !=1) {
            throw new RuntimeException("秒杀商品信息失败!");
        }
        String endTime = sdf.format(new Date());
        //更新数据
        shopSkillRepository.updateSkillEndTime(endTime,skillId);
        String orderIdByUUId = OrderUtils.getOrderIdByUUId();
        //秒杀成功插入数据
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setShopOrderId(orderIdByUUId);
        shopOrder.setCreateTime(endTime);
        String phoneNumber = OrderUtils.getTel();
        shopOrder.setPhoneNumber(phoneNumber);
        shopOrder.setSeckillid(skillId);
        shopOrder.setState(ShopOrder.OrderState.SUCCESS);
        shopOrderRepository.save(shopOrder);
        return "秒杀商品成功!";
    }
}
