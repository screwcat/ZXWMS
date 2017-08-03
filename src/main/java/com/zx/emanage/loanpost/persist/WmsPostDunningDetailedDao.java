package com.zx.emanage.loanpost.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsPostDunningDetailed;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsPostDunningDetailedDao extends BaseDao<WmsPostDunningDetailed> {
    /**
     * 通过催缴单ID，获取明细表数据
     * @param wms_post_dunning_head_id
     * @return
     */
    List<WmsPostDunningDetailed> getWmsPostDunningDetailedInfoByPK(Integer wms_post_dunning_head_id);
    
    List<Map<String, Object>> getWmsPostDunningDetailedInfo(Integer wms_post_dunning_head_id);
	
}