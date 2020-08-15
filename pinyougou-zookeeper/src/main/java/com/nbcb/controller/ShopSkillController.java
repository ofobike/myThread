package com.nbcb.controller;

import com.alibaba.fastjson.JSONObject;
import com.nbcb.domain.LockLogic;
import com.nbcb.domain.ShopSkill;
import com.nbcb.service.ShopSkillService;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShopSkillController {





    @Autowired
    private ShopSkillService shopSkillService;

    @RequestMapping(value = "findAll",method = RequestMethod.GET,produces = "text/html;charset=utf-8")
    public String findAll(){
        List<ShopSkill> skillList = shopSkillService.finaAllShopSkill();
        return JSONObject.toJSONString(skillList);
    }


    /**
     * 秒杀商品信息
     * @param skillId
     * @return
     */
    @RequestMapping(value = "skillOrder",method = RequestMethod.GET,produces = "text/html;charset=utf-8")
    public String skillOrder(String skillId){
        String num = null;
        try {
            LockLogic lockLogic = new LockLogic("lock");
            lockLogic.getLock();
            num = shopSkillService.updateSkillNum(skillId);
            lockLogic.unLock();
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(num);
    }
}
