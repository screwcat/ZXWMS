package com.zx.emanage.loanreview.persist;

import com.zx.emanage.util.gen.entity.WmsCreRevCertificateModel;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreRevCertificateModelDao
 * 模块名称：
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCreRevCertificateModelDao extends BaseDao<WmsCreRevCertificateModel>
{
    void deleteForId(Integer wms_cre_credit_head_id);

    /**
     * 根据ID获取征信审核模型信息
     * 
     * @param id
     * @return
     */
    WmsCreRevCertificateModel getInfoByFK(Integer id);

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
    WmsCreRevCertificateModel getCopyInfo(Integer id);
}