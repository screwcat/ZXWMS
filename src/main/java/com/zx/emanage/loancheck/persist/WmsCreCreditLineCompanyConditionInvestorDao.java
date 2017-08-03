package com.zx.emanage.loancheck.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCreditLineCompanyConditionInvestor;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditLineCompanyConditionInvestorDao extends BaseDao<WmsCreCreditLineCompanyConditionInvestor>
{
    public List<Map<String, Object>> searchByFK(Integer wms_cre_credit_head_id);

    public List<Map<String, Object>> searchInfoByFK(Integer wms_cre_credit_line_company_condition_id);
}