package com.zx.emanage.loanreview.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsCreCarpHouseCheck;

@MyBatisRepository
public interface WmsCreCarpHouseCheckDao extends BaseDao<WmsCreCarpHouseCheck> {
	  /**
     * 根据贷款主表ID删除记录
     * 
     * @param wms_cre_credit_head_id 贷款主表ID
     */
    void deleteRecord(Integer wms_cre_credit_head_id);
	
	 /**
    * 车贷办件列表查询
    */
   List<Map<String, Object>> searchForCdCardForAdd(Map<String, Object> paramMap);

   /**
    * 车贷办件列表记录数查�?
    */
   int findCountForCdCardForAdd(Map<String, Object> paramMap);
}