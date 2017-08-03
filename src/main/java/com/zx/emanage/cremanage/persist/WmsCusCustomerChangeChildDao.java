package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCusCustomerChangeChild;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCusCustomerChangeChildDao
 * 模块名称：客户变更子女
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCusCustomerChangeChildDao extends BaseDao<WmsCusCustomerChangeChild>
{

    void deleteByMap(Map<String, Object> map);

    void deleteByKey(int wms_cus_customer_change_child_id);
    
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
    List<WmsCusCustomerChangeChild> getCopyInfo(Integer id);

    /**
     * @Title: saveBatch
     * @Description: TODO(批量保存) 
     * @author: baisong
     * @time:2016年12月26日 上午11:41:53
     * history:
     * 1、2016年12月26日 baisong 创建方法
    */
    void saveBatch(List<WmsCusCustomerChangeChild> list);
    
}

