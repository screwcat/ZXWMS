package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreHousingCustomerHouse;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreHousingCustomerHouseDao
 * 模块名称：客户房产
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCreHousingCustomerHouseDao extends BaseDao<WmsCreHousingCustomerHouse>
{

    // 通过贷款表主键查询抵押房产信�?
    List<Map<String, Object>> searchHCHBYMccid(Map<String, Object> parameters);

    void deleteByMap(Map<String, Object> map);

    Map<String, Object> getwmscrehousingcustomerhouseinfo(
			Integer wms_cre_credit_head_id);

	WmsCreHousingCustomerHouse searchWmsCreCustomerChangeLineHouseinfoId(
			Integer wms_cre_credit_head_id);

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
    WmsCreHousingCustomerHouse getCopyInfo(Integer id);
}