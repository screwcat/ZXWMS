package com.zx.emanage.loanappro.persist;

import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCreditFinalAppl;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditFinalApplDao extends BaseDao<WmsCreCreditFinalAppl> {
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
    WmsCreCreditFinalAppl getCopyInfo(Integer id);

    /**
     * @Title: updateApprolimit
     * @Description: 终审金额根据实际传递参数更改 传递值为null时 终审金额更改为null
     * @param map 
     * @author: baisong
     * @time:2017年5月18日 上午11:55:00
     * history:
     * 1、2017年5月18日 baisong 创建方法
    */
    void updateApprolimit(Map<String, Object> map);
}