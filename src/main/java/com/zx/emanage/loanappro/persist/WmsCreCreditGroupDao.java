package com.zx.emanage.loanappro.persist;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsCreCreditGroup;

@MyBatisRepository
public interface WmsCreCreditGroupDao extends BaseDao<WmsCreCreditGroup> {

    /**
     * @Title: getGroupIdForBillCode
     * @Description: TODO(根据组合贷编号获取组合贷ID)
     * @param bill_code_group
     * @return 
     * @author: jiaodelong
     * @time:2017年1月6日 下午3:43:39
     * history:
     * 1、2017年1月6日 jiaodelong 创建方法
    */
    Integer getGroupIdForBillCode(String bill_code_group);
	
}