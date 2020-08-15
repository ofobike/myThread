package com.nbcb.mapper;

import com.nbcb.domain.ShopSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ShopSkillRepository extends JpaRepository<ShopSkill,String> {


    /**
     * 更新数据
     * @param skillId
     */
    @Modifying
    @Query("update ShopSkill ss set ss.productNum = ss.productNum -1,ss.startTime = ?1  where  ss.seckillId =?2")
    int updateSkillNum(String createTime,String skillId);


    @Modifying
    @Query("update ShopSkill ss set ss.endTime = ?1,ss.version = ss.version + 1 where  ss.seckillId =?2")
    int updateSkillEndTime(String endTIme,String skillId);
}
