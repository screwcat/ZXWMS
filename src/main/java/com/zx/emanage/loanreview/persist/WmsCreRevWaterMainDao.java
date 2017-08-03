package com.zx.emanage.loanreview.persist;

import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreRevWaterMain;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreRevWaterMainDao extends BaseDao<WmsCreRevWaterMain>
{
    /**
     * 根据贷款主表ID和贷款人ID获取贷款流水审核总体情况的记录
     * 
     * @param wms_cre_credit_head_id 贷款主表ID
     * @param wms_cre_credit_line_customer_change_head_id 贷款人ID
     * @return 贷款流水审核总体情况的记录
     */
    WmsCreRevWaterMain getByFK(Map<String, Object> paramMap);

    /**
     * 根据一定的条件删除记录
     * 
     * @param paramMap 条件Map
     */
    void deleteRecords(Map<String, Object> paramMap);

}