package com.zx.emanage.loancheck.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCreditLineApplicant;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyCondition;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditLineCompanyConditionDao extends BaseDao<WmsCreCreditLineCompanyCondition>
{
    public WmsCreCreditLineCompanyCondition getByFK(Integer wms_cre_credit_head_id);

    List<WmsCreCreditLineCompanyCondition> searchByFK(Integer wms_cre_credit_head_id);

}