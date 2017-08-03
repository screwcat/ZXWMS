package com.zx.emanage.loanappro.persist;

import com.zx.emanage.util.gen.entity.WmsCreCreditServiceType;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditServiceTypeDao extends BaseDao<WmsCreCreditServiceType> {

    /**
     * @Title: updateTht_Number
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param type 
     * @author: jiaodelong
     * @time:2017年2月28日 下午5:27:33
     * history:
     * 1、2017年2月28日 jiaodelong 创建方法
    */
    void updateTht_Number(WmsCreCreditServiceType type);

    /**
     * @Title: updateSyn
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wmsCreCreditServiceType 
     * @author: baisong
     * @time:2017年3月15日 上午9:15:35
     * history:
     * 1、2017年3月15日 baisong 创建方法
    */
    void updateSyn(WmsCreCreditServiceType wmsCreCreditServiceType);
	
}