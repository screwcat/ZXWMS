package com.zx.emanage.loanreview.persist;

import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreHousingAssessmentDao
 * 模块名称：
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCreHousingAssessmentDao extends BaseDao<WmsCreHousingAssessment>
{
    WmsCreHousingAssessment getInfoByFK(Integer wms_cre_credit_head_id);

    void deleteForId(Integer wms_cre_credit_head_id);
    /**
     * update:更新传人的实体类. <br/>
     * 
     * @author Administrator
     * @param t
     * @since JDK 1.6
     */
    int updateformakeup(WmsCreHousingAssessment t);

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
    WmsCreHousingAssessment getCopyInfo(Integer id);
}