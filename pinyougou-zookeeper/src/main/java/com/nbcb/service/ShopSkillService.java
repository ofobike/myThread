package com.nbcb.service;

import com.nbcb.domain.ShopSkill;

import java.util.List;

public interface ShopSkillService {
    List<ShopSkill> finaAllShopSkill();

    String updateSkillNum(String skillId);
}
