package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineCarpinfo;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreCustomerChangeLineCarpinfoDao
 * 模块名称：客户变更车产信息
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCreCustomerChangeLineCarpinfoDao extends BaseDao<WmsCreCustomerChangeLineCarpinfo> {
	/**
	 * @Title: addNewRecordReKey 
	 * @Description: 添加车产变更记录返回主键
	 * @param wmscrecustomerchangelinecarpinfo
	 * @return    
	 * @return int    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月28日 下午5:51:39
	 */
    int addNewRecordReKey(WmsCreCustomerChangeLineCarpinfo wmscrecustomerchangelinecarpinfo);
    /**
	 * @Title: addNewRecordReKey 
	 * @Description: 添加车产变更记录
	 * @param wmscrecustomerchangelinecarpinfo
	 * @return    
	 * @return int    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月28日 下午5:51:39
	 */
	void addNewRecord(WmsCreCustomerChangeLineCarpinfo carvo_p);
	
	void deleteByMap(Map<String, Object> map);

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
    List<WmsCreCustomerChangeLineCarpinfo> getCopyInfo(Integer id);

    /**
     * @Title: saveBatch
     * @Description: TODO(批量保存) 
     * @author: baisong
     * @time:2016年12月26日 上午11:41:53
     * history:
     * 1、2016年12月26日 baisong 创建方法
    */
    void saveBatch(List<WmsCreCustomerChangeLineCarpinfo> list);

}