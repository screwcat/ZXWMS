package com.zx.emanage.loancheck.persist;

import com.zx.emanage.util.gen.entity.WmsCreCreditLineApplicant;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerInfo;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditLineCustomerInfoDao extends BaseDao<WmsCreCreditLineCustomerInfo>
{
    public WmsCreCreditLineCustomerInfo getByFK(Integer wms_cre_credit_head_id);

}