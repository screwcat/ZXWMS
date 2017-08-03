package com.zx.emanage.cremanage.persist;

import org.apache.ibatis.annotations.Param;

import com.zx.emanage.util.gen.entity.WmsCreHousingFileInfo;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreHousingFileInfoDao
 * 模块名称：贷款上传信息表
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCreHousingFileInfoDao extends BaseDao<WmsCreHousingFileInfo> {
	
    /** 根据map删除数据(逻辑) **/
    void deleteByWmsCreCreditHeadId(@Param(value="_wms_cre_credit_head_id") Integer wms_cre_credit_head_id);
    
    
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
    WmsCreHousingFileInfo getCopyInfo(Integer id);
}