package com.zx.emanage.loanpost.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsPostLoanMigration;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsPostLoanMigrationDao extends BaseDao<WmsPostLoanMigration>
{

    /**
     * search:根据传人的条件动态生成sql语句，如需分页需要在sql中加入offset、pagesize变量 <br/>
     * 
     * @author RY
     * @param parameters
     * @return
     */
    List<Map<String, Object>> searchZJByQueryInfo(Map<String, Object> parameters);

    /**
     * findCount:根据查询条件返回记录总条数. <br/>
     * 
     * @author RY
     * @param paramMaps
     * @return
     */
    int findCountZJByQueryInfo(Map<String, Object> paramMaps);

    /**
     * findCount:根据查询条件返回转件审核、转件确认导出的数据. <br/>
     * 
     * @author RY
     * @param paramMaps
     * @return
     */
    List<Map<String, Object>> getZJListWithoutPaging(Map<String, Object> paramMaps);

    /**
     * findCount:根据查询条件返回转件申请导出的数据. <br/>
     * 
     * @author RY
     * @param paramMaps
     * @return
     */
    List<Map<String, Object>> getZJSQListWithoutPaging(Map<String, Object> paramMaps);

    /**
     * findCount:通过贷款表ID获得还款、合同信息. <br/>
     * 
     * @author RY
     * @param wms_cre_credit_head_id
     * @return
     */
    List<Map<String, Object>> getMigrationInfoByMCCHID(Map<String, Object> paramMaps);

    /**
     * findCount:保存附件信息. <br/>
     * 
     * @author RY
     * @param wmsPostLoanMigration
     * @return
     */
    int addNewRecord(WmsPostLoanMigration wmsPostLoanMigration);

    /**
     * search:查询转件申请分页数据 <br/>
     * 
     * @author RY
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> searchZJByQueryInfo1(Map<String, Object> paramMap);

    /**
     * findCount:查询转件申请分页数 <br/>
     * 
     * @author RY
     * @param paramMap
     * @return
     */
    int findCountZJByQueryInfo1(Map<String, Object> paramMap);

    /**
     * update:更新enable状态 <br/>
     * 
     * @author RY
     * @param paramMap
     * @return
     */
    int updateEnable(WmsPostLoanMigration wmsPostLoanMigration);
}