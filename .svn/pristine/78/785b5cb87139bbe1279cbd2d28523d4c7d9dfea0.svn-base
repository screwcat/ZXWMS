package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineHouseinfo;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreCustomerChangeLineHouseinfoDao
 * 模块名称： 客户变更房产信息
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCreCustomerChangeLineHouseinfoDao extends BaseDao<WmsCreCustomerChangeLineHouseinfo>
{

    int addNewRecord(WmsCreCustomerChangeLineHouseinfo wmsCreCustomerChangeLineHouseinfo);

    // 添加记录 返回新增的主�?
    int addNewRecordReKey(WmsCreCustomerChangeLineHouseinfo wmsCreCustomerChangeLineHouseinfo);

    void deleteByMap(Map<String, Object> map);

    /**
     * 根据贷款表主键查�?合同中附件表中的房产信息
     * 
     * @param wms_cre_credit_head_id
     * @return
     */
    Map<String, Object> searchformortgage(Integer wms_cre_credit_head_id);
    // 通过贷款表主键查询抵押房产信�?
    List<Map<String, Object>> searchHCHBYMccid(Map<String, Object> parameters);
    
    /**
     * 移动端第二版申请补录修改
     * @param map
     * @return
     */
    int updateforBLTwo(Map<String, Object> map);

    /**
    * 
    * @Title: getCopyInfo
    * @Description: TODO(查询当前表的所有信息--用于复制)
    * @param id
    * @return 
    * @author: baisong
    * @time:2016年12月23日 下午5:18:59
    * history:
    * 1、2016年12月23日 baisong 创建方法
    */
    List<WmsCreCustomerChangeLineHouseinfo> getCopyInfo(Integer id);

    /**
     * @Title: saveBatch
     * @Description: TODO(批量保存) 
     * @author: baisong
     * @time:2016年12月26日 上午11:41:53
     * history:
     * 1、2016年12月26日 baisong 创建方法
    */
    void saveBatch(List<WmsCreCustomerChangeLineHouseinfo> list);

    /**
     * @Title: getInfoForCustomerId
     * @Description: TODO(根据客户变ID查询房产表信息)
     * @param wms_cre_credit_line_customer_change_head_id 
     * @author: jiaodelong
     * @return 
     * @time:2017年4月27日 下午3:00:30
     * history:
     * 1、2017年4月27日 jiaodelong 创建方法
    */
    WmsCreCustomerChangeLineHouseinfo getInfoForCustomerId(Integer wms_cre_credit_line_customer_change_head_id);
    
    /**
     * 
     * @Title: checkIsClaim
     * @Description: 查询是否已被认领
     * @param wms_cre_credit_head_id 
     * @author: ZhangWei
     * @time:2017年6月1日 下午4:44:52
     * history:
     * 1、2017年6月1日 ZhangWei 创建方法
     */
    Integer checkIsClaim(Integer wms_cre_credit_head_id);
    
    /**
     * 
     * @Title: ClaimOperUp
     * @Description: 发送房产核查领用状态
     * @param map 
     * @author: ZhangWei
     * @time:2017年6月1日 下午4:04:24
     * history:
     * 1、2017年6月1日 ZhangWei 创建方法
     */
    void ClaimOperUp(Map<String, Object> map);
    
}
