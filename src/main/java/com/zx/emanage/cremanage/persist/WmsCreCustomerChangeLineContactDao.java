package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreCustomerChangeLineContactDao
 * 模块名称：客户变更联系人信息
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCreCustomerChangeLineContactDao extends BaseDao<WmsCreCustomerChangeLineContact>
{

    int addNewRecord(WmsCreCustomerChangeLineContact wmsCreCustomerChangeLineContact);

    List<WmsCreCustomerChangeLineContact> searchByPhone(Map<String, Object> paramMap);

    List<WmsCreCustomerChangeLineContact> searchByPhone2(Map<String, Object> paramMap);

    // 合同自动带出�?根据id查询 多个保人
    List<Map<String, Object>> searchforborrow(Integer wms_cre_credit_head_id);

    List<WmsCreCustomerChangeLineContact> getListByEntity(WmsCreCustomerChangeLineContact queryInfo);

    void deleteByMap(Map<String, Object> map);

    int updateChangeContantBF(WmsCreCustomerChangeLineContact wmsCreCustomerChangeLineContact);
    
    /**
     * 删除联系人 亲属
     * @param list
     * @return
     */
    int deleteOut(Map<String, Object> paramMap);
    
    List<Map<String, Object>> searchList(Map<String, Object> paramMap);
    
    void deleteByPKs(Map<String, Object> paramMap);

	Integer deleteForHeadId(Map<String, Object> paramMap);
    
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
    List<WmsCreCustomerChangeLineContact> getCopyInfo(Integer id);
    
    /**
     * @Title: saveBatch
     * @Description: TODO(批量保存) 
     * @author: baisong
     * @time:2016年12月26日 上午11:41:53
     * history:
     * 1、2016年12月26日 baisong 创建方法
    */
    void saveBatch(List<WmsCreCustomerChangeLineContact> list);

    /**
     * @Title: getContactsInfoList
     * @Description: TODO(根据headid查询联系人信息)
     * @param map
     * @return 
     * @author: jiaodelong
     * @time:2017年2月17日 下午12:31:36
     * history:
     * 1、2017年2月17日 jiaodelong 创建方法
    */
    List<Map<String, Object>> getContactsInfoList(Map<String, Object> map);

    /**
     * @Title: getContactsInfoListCount
     * @Description: TODO(获取联系人条数)
     * @param map
     * @return 
     * @author: jiaodelong
     * @time:2017年2月17日 下午1:22:44
     * history:
     * 1、2017年2月17日 jiaodelong 创建方法
    */
    int getContactsInfoListCount(Map<String, Object> map);

    /**
     * @Title: deleteForHeadIdToFour
     * @Description: TODO(根据ID删除)
     * @param paramMap 
     * @author: jiaodelong
     * @time:2017年3月17日 下午5:05:07
     * history:
     * 1、2017年3月17日 jiaodelong 创建方法
    */
    Integer deleteForHeadIdToFour(Map<String, Object> paramMap);

}
