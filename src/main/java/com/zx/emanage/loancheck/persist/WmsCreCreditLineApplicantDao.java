package com.zx.emanage.loancheck.persist;

import com.zx.emanage.util.gen.entity.WmsCreCreditLineApplicant;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditLineApplicantDao extends BaseDao<WmsCreCreditLineApplicant>
{
    /**
     * 根据贷款ID查询申请人信息
     * 
     * @param wms_cre_credit_head_id 贷款ID
     * @return 申请人信息对象
     */
    public WmsCreCreditLineApplicant getByFK(Integer wms_cre_credit_head_id);

}