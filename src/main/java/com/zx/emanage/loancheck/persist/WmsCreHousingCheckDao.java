package com.zx.emanage.loancheck.persist;

import com.zx.emanage.util.gen.entity.WmsCreHousingCheck;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreHousingCheckDao
 * 模块名称：房产核查
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCreHousingCheckDao extends BaseDao<WmsCreHousingCheck>
{
    /**
     * 根据贷款主表ID删除记录
     * 
     * @param wms_cre_credit_head_id 贷款主表ID
     */
    void deleteRecord(Integer wms_cre_credit_head_id);
    
    /***
     * 更新方法   Integer wms_cre_credit_head_id
     * @param t
     * @return
     */
    int updateformakeup(WmsCreHousingCheck t);

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
    WmsCreHousingCheck getCopyInfo(Integer id);
}