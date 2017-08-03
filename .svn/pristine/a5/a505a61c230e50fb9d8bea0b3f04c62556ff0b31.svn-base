package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.vo.WmsCreCreditLineCustomerChangeHeadVO;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreCreditLineCustomerChangeHeadDao
 * 模块名称：客户变更主表
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCreCreditLineCustomerChangeHeadDao extends BaseDao<WmsCreCreditLineCustomerChangeHead>
{

    int saveWithKey(WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead);

    List<WmsCreCreditLineCustomerChangeHead> searchByPhone(Map<String, Object> paramMap);

    List<Map<String, Object>> searchByTel(Map<String, Object> paramMap);

    // 合同自动带出�?根据id查询 多个共同债务�?
    List<Map<String, Object>> searchforborrow(Integer wms_cre_credit_head_id);

    List<Map<String, Object>> searchForDiff(Map<String, Object> paramMap);

    List<WmsCreCreditLineCustomerChangeHead> getWmsCreCreditLineCustomerChangeHeadListWithoutPaging(Integer wms_cre_credit_head_id);

    void deleteByMap(Map<String, Object> map);

    int updateIsMajor(Map<String, Object> map);

    public List<WmsCreCreditLineCustomerChangeHead> selectScoreInfo(Integer wms_cre_credit_head_id);

    public Integer getCount(Integer wms_cre_credit_head_id);

    /**
     * 根据贷款主表ID获取主贷�?
     * 
     * @param wms_cre_credit_head_id 贷款主表ID
     * @return
     */
    WmsCreCreditLineCustomerChangeHead getMainByFk(Integer wms_cre_credit_head_id);

    /**
     * 根据贷款主表ID获取非主贷人集合
     * 
     * @param wms_cre_credit_head_id 贷款主表ID
     * @return
     */
    List<WmsCreCreditLineCustomerChangeHead> getNotMainByFk(Integer wms_cre_credit_head_id);

	com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead getKeHuInfo(
			Integer wms_cre_credit_head_id);
    
    /**
     * 根据客户id删除所有客户信息(逻辑删除)
     * @author administrator
     */
    void wmsCustomerAllInfoDelete(Integer wms_cre_credit_line_customer_change_head_id);
    
    /**
     * 修改客户信息时同步更新所有客户信息(没签合同的单据对应的客户信息变更表的信息)
     * @param paramMap
     */
    void syncCustomerInfoForContract(Map<String, Object> paramMap);
    
    public WmsCreCreditLineCustomerChangeHeadVO getKHXX(Integer wms_cre_credit_head_id);
    /**
     * 更新客户变更主表
     * @param paramMap
     * @return
     */
    public int updateforBLTwo(WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead);
    
    int saveInfo(WmsCreCreditLineCustomerChangeHead wmscrecreditlinecustomerchangehead);

	WmsCreCreditLineCustomerChangeHead getChangeHeadId(
			Integer wms_cre_credit_head_id);

    /**
     * 
     * @Title: getCopyInfo
     * @Description: TODO(查询当前表的所有信息--用于复制)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: baisong
     * @time:2016年12月23日 下午5:18:59
     * history:
     * 1、2016年12月23日 baisong 创建方法
     */
    List<WmsCreCreditLineCustomerChangeHead> getCopyInfo(Integer wms_cre_credit_head_id);

    /**
     * 
     * @Title: getCopyInfo
     * @Description: TODO(查询表)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: baisong
     * @time:2016年12月23日 下午5:18:59
     * history:
     * 1、2016年12月23日 baisong 创建方法
     */
    List<WmsCreCreditLineCustomerChangeHead> getInfoByHeadId(Map<String, Object> map);

    /**
     * @Title: getChangeHeadIdForFour
     * @Description: TODO(查询表)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: jiaodelong
     * @time:2017年3月17日 下午5:03:17
     * history:
     * 1、2017年3月17日 jiaodelong 创建方法
    */
    WmsCreCreditLineCustomerChangeHead getChangeHeadIdForFour(Integer wms_cre_credit_head_id);

    /**
     * @Title: updateIsTelephone
     * @Description: TODO(根据headid修改客户电话)
     * @param changeHead 
     * @author: jiaodelong
     * @time:2017年3月29日 上午9:59:17
     * history:
     * 1、2017年3月29日 jiaodelong 创建方法
    */
    void updateIsTelephone(WmsCreCreditLineCustomerChangeHead changeHead);

    /**
     * @Title: getInfoForHeadId
     * @Description: TODO(根据headID查询数据)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: jiaodelong
     * @time:2017年4月27日 下午2:52:29
     * history:
     * 1、2017年4月27日 jiaodelong 创建方法
    */
    WmsCreCreditLineCustomerChangeHead getInfoForHeadId(Integer wms_cre_credit_head_id);
}
