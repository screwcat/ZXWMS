package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.emanage.util.gen.entity.WmsCreHousingAtt;
import com.zx.emanage.util.gen.entity.WmsPostLoanMigration;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreHousingAttDao extends BaseDao<WmsCreHousingAtt>
{
    /**
     * update:更新enable状�? <br/>
     * 
     * @author RY
     * @param paramMap
     * @return
     */
    int updateEnable(WmsCreHousingAtt wmsCreHousingAtt);

    /**
     * 根据wms_cre_credit_id 查询附件
     * 
     * @param wms_cre_credit_head_id
     * @return
     */
    WmsCreHousingAtt getInfoByFK(Integer wms_cre_credit_head_id);

    /**
     * 显示上传附件列表
     */
    List<WmsCreHousingAtt> getInfoList(Integer wms_cre_credit_head_id);

    /**
     * 查询合同附件信息
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> searchForHt(Map<String, Object> paramMap);

    /**
     * 根据�?��的条件删除附件记�?
     * 
     * @param paramsMap
     */
    void deleteRecords(Map<String, Object> paramsMap);

	void deleteByheadid(@Param("headId")Integer headId);
}